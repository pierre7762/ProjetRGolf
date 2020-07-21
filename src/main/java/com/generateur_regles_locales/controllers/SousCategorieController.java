package com.generateur_regles_locales.controllers;


import com.generateur_regles_locales.models.*;
import com.generateur_regles_locales.service.VerificationService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RequestParam;

import java.util.List;


@Controller
public class SousCategorieController {

    @Autowired
    private CategorieRepository categorieRepository;
    @Autowired
    private SousCategorieRepository sousCategorieRepository;
    @Autowired
    private  RegleRepository regleRepository;
    @Autowired
    private VerificationService verificationService;

    /**
     *  <b>Affiche le formulaire de modification d'une sous-catégorie.</b>
     * @return Le template changesouscategorie
     * */
    @RequestMapping(value = "Editeur/changesouscateg/{id}", method = RequestMethod.GET)
    public String modifSousCategories(Model model, @PathVariable("id") Long id) {
        SousCategorie souscateg = sousCategorieRepository.findById(id).get();
        model.addAttribute("souscategorie", souscateg);
        return "changesouscategorie";
    }

    /**
     *  <b>Modifie la sous-catégorie.</b>
     * @return Le template categories en redirection
     * */
    @RequestMapping(value = "Editeur/changesouscategorie/{id}", method = RequestMethod.POST)
    public String modifySousCategories(Model model, @PathVariable("id") Long id, @RequestParam("newtitre") String name) {
        SousCategorie souscateg = sousCategorieRepository.findById(id).get();

        Boolean verifok = verificationService.inputSafe(name);
        System.out.println(verifok);

        if (verifok==false){
            System.out.println("merci de ne pas essayer d'entrer du code");
            return "redirect:/categories";//si tentative de code
        }

        souscateg.setTitle(name);
        sousCategorieRepository.save(souscateg);

        model.addAttribute("souscategories", souscateg);

        return "redirect:/categories";// on respect le prg ,
        // a chaque fois quon influe lapp ou bdd
        //on redirige jamais sur la meme page


    }

    /**
     *  <b>Affiche le formulaire de modification de l'objet d'une sous-catégorie.</b>
     * @return Le template changesouscategorieobjet
     * */
    @RequestMapping(value = "Editeur/changesouscategobjet/{id}", method = RequestMethod.GET)
    public String modifSousCategoriesObject(Model model, @PathVariable("id") Long id) {
        SousCategorie souscateg = sousCategorieRepository.findById(id).get();
        model.addAttribute("souscategorie", souscateg);
        return "changesouscategorieobjet";
    }

    /**
     *  <b>Modifie l'objet de la sous-catégorie.</b>
     * @return Le template categories en redirection
     * */
    @RequestMapping(value = "Editeur/changesouscategorieobjet/{id}", method = RequestMethod.POST)
    public String modifySousCategoriesObject(Model model, @PathVariable("id") Long id, @RequestParam("newobjet") String objet) {
        SousCategorie souscateg = sousCategorieRepository.findById(id).get();

        Boolean verifok = verificationService.inputSafe(objet);
        System.out.println(verifok);

        if (verifok==false){
            System.out.println("merci de ne pas essayer d'entrer du code");
            return "redirect:/categories";//si tentative de code
        }

        souscateg.setObjet(objet);
        sousCategorieRepository.save(souscateg);

        model.addAttribute("souscategories", souscateg);

        return "redirect:/categories";// on respect le prg ,
        // a chaque fois quon influe lapp ou bdd
        //on redirige jamais sur la meme page


    }

    /**
     *  <b>Affiche le formulaire de création d'une sous-catégorie.</b>
     * @return Le template newsouscategorie
     * */
    @RequestMapping(value = "Gestionnaire/newsouscateg/{id}", method = RequestMethod.GET)
    public String nextNewCategories(Model model, @PathVariable("id") Long id) {
        Categorie categ = categorieRepository.findById(id).get();
        Categorie listsouscategorie = categorieRepository.findById(id).get();
        Integer attributnumordre = listsouscategorie.getSousCategories().size() + 1;
        model.addAttribute("categorie", categ);
        model.addAttribute("incrementnumordre", attributnumordre);


        return "newsouscategorie";
    }

    /**
     *  <b>Ajoute une sous-catégorie.</b>
     * @return Le template categories en redirection
     * */
    @RequestMapping(value = "Gestionnaire/newsouscategorie/{id}", method = RequestMethod.POST)
    public String NewSousCategorie(@RequestParam("newnumordre") Integer newnumordre, @PathVariable("id") Long id, @RequestParam("NewTitle") String newtitle, @RequestParam("newobjet") String newobjet) {
        Categorie categ = categorieRepository.findById(id).get();
        SousCategorie souscateg = new SousCategorie();//new souscateg sur lid de la categorie


        Boolean verifok = verificationService.inputSafe(newtitle);
        System.out.println(verifok);

        if (verifok==false){
            System.out.println("merci de ne pas essayer d'entrer du code");
            return "redirect:/categories";//si tentative de code
        }
        Boolean verifok2 = verificationService.inputSafe(newobjet);
        System.out.println(verifok);

        if (verifok2==false){
            System.out.println("merci de ne pas essayer d'entrer du code");
            return "redirect:/categories";//si tentative de code
        }

        //on set tous les attributs récupérer et nécessaire à l'affichage du template categorie
        souscateg.setObjet(newobjet);
        souscateg.setTitle(newtitle);
        souscateg.setNumordre(newnumordre);
        souscateg.setCategorie(categ);//on set la souscateg a la nouvelle categorie
        categ.getSousCategories().add(souscateg);//on ajoute a la categorie la nouvelle sous categ
        categorieRepository.save(categ);//on save
        sousCategorieRepository.save(souscateg);

        return "redirect:/categories";// on respect le prg ,
        // a chaque fois quon influe lapp ou bdd
        //on redirige jamais sur la meme page
    }

    /**
     *  <b>Supprime une sous-catégorie.</b>
     * @return Le template categories en redirection
     * */
    @RequestMapping(value = "Admin/souscategoriedelete/{id}", method = RequestMethod.GET)
    public String DeleteSousCategorie(Model model, @PathVariable("id") Long id) {
        SousCategorie souscateg = sousCategorieRepository.findById(id).get();
        List<Regle> regle = regleRepository.findAllBySouscategorie(souscateg);

        for (Regle reg : regle) {
            regleRepository.delete(reg);
        }
        sousCategorieRepository.delete(souscateg);

        return "redirect:/categories";
    }


}
