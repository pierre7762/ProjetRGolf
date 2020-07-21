package com.generateur_regles_locales.controllers;

import com.generateur_regles_locales.models.Role;
import com.generateur_regles_locales.models.RoleRepository;
import com.generateur_regles_locales.models.User;
import com.generateur_regles_locales.models.UserRepository;
import com.generateur_regles_locales.service.JpaUserService;
import com.generateur_regles_locales.service.VerificationService;
import org.jsoup.Jsoup;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RequestParam;

import java.util.List;


/**
 * <b>Ce contrôleur gère les fonctions exclusives de l'administrateur</b>
 * @author Aryles/Pierre
 * <br><br>
 * <b>Dans ce contrôleur toutes les URI prennent d'office /Admin</b>
 * */
@RequestMapping(value="/Admin")
@Controller

public class AdminController {

        @Autowired
        private UserRepository userRepository;
        @Autowired
        private RoleRepository roleRepository;
        @Autowired
        private VerificationService verificationService;

        //inscription
        /**
         *<b>Méthode get inscription </b>
         * <ul>
         *     <li>Permet l'accès à la page d'inscription des utilisateurs.</li>
         *     <li>On récupère les rôles.</li>
         * </ul>
         * @return Le template inscription
         * */
        @RequestMapping(value = "/inscription", method = RequestMethod.GET)
        public String inscription(Model model) {
            model.addAttribute("roles",roleRepository.findAll());//récupérer les roles
            return "inscription";
        }

        /**
         * <b>Méthode post inscription </b>
         * <ul>
         *     <li>Les paramètres sont ceux récupérés dans le formulaire d'inscription.</li>
         *     <li>Les informations sont traitées par @see JpaUserService.</li>
         *     <li>On vérifie l'absence de tentative de XSS.</li>
         * @return Le template catégorie.
         * </ul>
         * */
        @RequestMapping(value = "/inscription",method = RequestMethod.POST)
        public String inscription(Model model, @RequestParam("nom") String nom, @RequestParam ("mail") String mail, @RequestParam ("mdp") String mdp, @RequestParam ("idrole")Long idrole){

            Boolean verifok = verificationService.inputSafe(nom);
            if (verifok==false){
                System.out.println("merci de ne pas essayer d'entrer du code");
                return "redirect:/categories";//si tentative de code
            }

            Boolean verifok2 = verificationService.inputSafe(mail);
            if (verifok2==false){
                System.out.println("merci de ne pas essayer d'entrer du code");
                return "redirect:/categories";//si tentative de code
            }
            Boolean verifok3 = verificationService.inputSafe(mdp);
            if (verifok3==false){
                System.out.println("merci de ne pas essayer d'entrer du code");
                return "redirect:/categories";//si tentative de code
            }

            String usernom= Jsoup.parse(nom).text();
            String usermail=Jsoup.parse(mail).text();
            String usermdp=Jsoup.parse(mdp).text();

            if(usernom.isEmpty()==false && userRepository.findByName(usernom)==null) {
                User user = new User();
                user.setName(usernom);
                user.setMail(usermail);
                user.setPassword(usermdp);
                Role role=roleRepository.findById(idrole).get();
                if(role!=null) {
                    user.setRole(role);
                    JpaUserService jus = new JpaUserService();
                    jus.setbCryptPasswordEncoder(new BCryptPasswordEncoder());
                    jus.setUserDao(userRepository);
                    jus.save(user); //la méthode save le mdp
                }

                //on applique pas IOC (Inversion Of Control = gestion de l'app par le framework) ici

            }
            return "redirect:/categories";
        }
        //fin inscription

        //------------------------------------------------------------------------------------------------------------------

        //modification des utilisateurs
        /**
         * <b>Affichage de la liste des utilisateurs.</b>
         * @return Le template listeUtilisateurs
         * */
        @RequestMapping("/listeUtilisateur")
        public String getUserlist(Model model){
            List<User> user = (List<User>) userRepository.findAll();
            model.addAttribute("user",user);
            List<Role> userRole = roleRepository.findAll();
            model.addAttribute("role",userRole);
            return "listeUtilisateurs";
        }


        /**
         * <b>Affichage de la page de gestion des utilisateurs.</b>
         * @return Le template gestionUtilisateur
         * */
        @RequestMapping(value = "/gestionUtilisateur",method = RequestMethod.POST)
        public String getUser(Model model, @RequestParam("userId") Long userid){
            User user = userRepository.findById(userid).get();
            model.addAttribute("user",user);
            List<Role> userRole = roleRepository.findAll();
            model.addAttribute("role",userRole);
            return "gestionUtilisateurs";
        }

        /**
         *  <b>Modifie l'adresse e-mail.</b>
         *  @return Le template listeUtilisateur en redirection
         * */
        @RequestMapping(value = "/modifmail",method = RequestMethod.POST)
        public String modifmail(Model model, @RequestParam("userId") Long userid, @RequestParam("mail") String email){
            Boolean verifok = verificationService.inputSafe(email);
            System.out.println(verifok);

            if (verifok==false){
                System.out.println("merci de ne pas essayer d'entrer du code");
                return "redirect:/categories";//si tentative de code
            }

            User user = userRepository.findById(userid).get();
            user.setMail(email);
            userRepository.save(user);

            return "redirect:/Admin/listeUtilisateur";

        }

         /**
          *  <b>Modifie le mot de passe.</b>
          * @return Le template listeUtilisateur en redirection
          * */
        @RequestMapping(value = "/modifpwd", method = RequestMethod.POST)
        public String modifpwd(Model model, @RequestParam("userId") Long userid,@RequestParam("password") String mdp,
                               @RequestParam("passwordConf") String mdpConf){

            if (mdp.equals(mdpConf))
            {
                User user = userRepository.findById(userid).get();
                user.setPassword(mdp);
                JpaUserService jus = new JpaUserService();
                jus.setbCryptPasswordEncoder(new BCryptPasswordEncoder());
                jus.setUserDao(userRepository);
                jus.save(user); //la méthode save le mdp encodé

                return "redirect:/Admin/listeUtilisateur";
            }
            return "redirect:/categories";
        }

         /**
         *  <b>Modifie le rôle.</b>
         * @return Le template listeUtilisateur en redirection
          *  */
        @RequestMapping(value = "/modifrole",method = RequestMethod.POST)
        public String modifrole(Model model, @RequestParam("userId") Long userid, @RequestParam("idrole") Role role){
            User user = userRepository.findById(userid).get();
            user.setRole(role);
            userRepository.save(user);

            return "redirect:/Admin/listeUtilisateur";

        }



        //fin modification des utilisateurs

        //------------------------------------------------------------------------------------------------------------------


    }
