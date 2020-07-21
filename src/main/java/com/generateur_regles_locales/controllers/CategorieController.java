package com.generateur_regles_locales.controllers;

import com.generateur_regles_locales.models.*;
import com.generateur_regles_locales.models.*;
import com.generateur_regles_locales.service.JpaUserService;
import com.generateur_regles_locales.service.VerificationService;
import org.jsoup.Jsoup;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.*;

import java.util.ArrayList;
import java.util.List;
/**
 * @author Aryles/Pierre
 * <b>Ce contrôleur gère les actions liées aux catégories</b>
 * */
@Controller
public class CategorieController {
    @Autowired
    private CategorieRepository categorieRepository;
    @Autowired
    private SousCategorieRepository sousCategorieRepository;
    @Autowired
    private RegleRepository regleRepository;
    @Autowired
    private VerificationService verificationService;



    /**
     *  <b>Affiche la page contenant la liste des catégories/sous-catégories/règles.</b>
     * @return Le template catégorie
     * */
    @RequestMapping("/categories")
    public String categories(Model model) {
        List<Categorie> categories = categorieRepository.orderListcodeCategorie();
        model.addAttribute("categories", categories);
        return "categorie";
    }

    /**
     *  <b>Affiche le formulaire pour modifier une catégorie.</b>
     * @return Le template changecategorie
     * */
    @RequestMapping(value = "/Editeur/changecateg/{id}", method = RequestMethod.GET)
    public String modifCategories(Model model, @PathVariable("id") Long id) {
        Categorie categ = categorieRepository.findById(id).get();
        model.addAttribute("categorie", categ);
        return "changecategorie";
    }

    /**
     *  <b>Modifie la catégorie.</b>
     * @return Le template categories en redirection
     * */
    @RequestMapping(value = "/Editeur/changecategorie/{id}", method = RequestMethod.POST)
    public String modifyCategories(Model model, @PathVariable("id") Long id, @RequestParam("newtitre") String name) {
        Categorie categ = categorieRepository.findById(id).get();

        Boolean verifok = verificationService.inputSafe(name);
        System.out.println(verifok);

        if (verifok==false){
            System.out.println("merci de ne pas essayer d'entrer du code");
            return "redirect:/categories";//si tentative de code
        }

        categ.setTitle(name);
        categorieRepository.save(categ);

        return "redirect:/categories";// on respecte le prg(Post redirect get) ,
        // à chaque fois que l'on influe sur l'app ou la bdd
        //on ne redirige jamais sur la même page

    }


    /**
     *  <b>Affiche le formulaire pour créer une catégorie.</b>
     * @return Le template newcategorie
     * */
    @RequestMapping(value = "Gestionnaire/newcateg/{id}", method = RequestMethod.GET)
    public String nextCategories(Model model, @PathVariable("id") Long id) {
        Categorie categ = categorieRepository.findById(id).get();

        model.addAttribute("categorie", categ);


        return "newcategorie";
    }

    /**
     *  <b>Ajoute une catégorie.</b>
     * @return Le template categories en redirection
     * */
    @RequestMapping(value = "Gestionnaire/newcategorie", method = RequestMethod.POST)
    public String NewCategorie(@RequestParam("NewNameTitle") String newt, @RequestParam("NewNameCode") String newc) {

        Categorie categ = new Categorie();

        Boolean verifok = verificationService.inputSafe(newt);
        System.out.println(verifok);

        if (verifok==false){
            System.out.println("merci de ne pas essayer d'entrer du code");
            return "redirect:/categories";//si tentative de code
        }
        Boolean verifok2 = verificationService.inputSafe(newc);
        System.out.println(verifok);

        if (verifok2==false){
            System.out.println("merci de ne pas essayer d'entrer du code");
            return "redirect:/categories";//si tentative de code
        }

        categ.setTitle(newt);
        categ.setCode(newc);
        categorieRepository.save(categ);
        //TODO Ajouter if(code exist ==> "Lettre deja existante ")

        return "redirect:/categories";
    }



//////////////////////////////////////////////supp

    /**
     *  <b>Supprime une catégorie.</b>
     * @return Le template categories en redirection
     * */
    @RequestMapping(value = "Admin/categoriedelete/{id}", method = RequestMethod.GET)
    public String DeleteCategorie(Model model, @PathVariable("id") Long id) {
        Categorie categ = categorieRepository.findById(id).get();
        List<SousCategorie> souscatego = sousCategorieRepository.findAllByCategorie(categ);

        for (SousCategorie sous : souscatego) {
            List<Regle> regle = regleRepository.findAllBySouscategorie(sous);
            for (Regle reg : regle) {
                regleRepository.delete(reg);
            }
            sousCategorieRepository.delete(sous);
        }

        categorieRepository.deleteById(id);

        return "redirect:/categories";
    }


}

