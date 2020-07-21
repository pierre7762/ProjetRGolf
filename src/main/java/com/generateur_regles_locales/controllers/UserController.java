package com.generateur_regles_locales.controllers;

import com.generateur_regles_locales.models.Role;
import com.generateur_regles_locales.models.User;
import com.generateur_regles_locales.models.UserRepository;
import com.generateur_regles_locales.service.VerificationService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;

import java.util.List;

/**
 * <b>Ce contrôleur gère les fonctions de gestion de compte utilisateur.</b>
 * @author Aryles/Pierre
 * <br><br>
 * */
@Controller
public class UserController {
    @Autowired
    private UserRepository userRepository;



//TODO permettre aux utilisateurs de gérer leur compte
  /*  @RequestMapping("Editeur/gererMonCompte")
    public String editgestcompte(Model model, @RequestParam("userId") Long userid) {
        User user = userRepository.findById(userid).get();
        model.addAttribute("user", user);

        return "gererMonCompte";
    }*/

}
