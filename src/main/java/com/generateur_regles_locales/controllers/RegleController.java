package com.generateur_regles_locales.controllers;

import com.generateur_regles_locales.models.*;
import com.generateur_regles_locales.service.VerificationService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.*;

import java.util.ArrayList;
import java.util.List;

/**
 * @author Aryles/Pierre
 * <b>Gestion des règles.</b>
 * */
@Controller
public class RegleController {

    @Autowired
    private SousCategorieRepository sousCategorieRepository;
    @Autowired
    private RegleRepository regleRepository;
    @Autowired
    private VerificationService verificationService;


    /**
     *  <b>Affiche le formulaire pour modifier une règle.</b>
     * @return Le template changeregle
     * */
    @RequestMapping(value = "Editeur/changeregle/{id}", method = RequestMethod.GET)
    public String modifyRegles(Model model, @PathVariable("id") Long id) {
        Regle regle = regleRepository.findById(id).get();

        model.addAttribute("regle", regle);

        return "changeregle";
    }

    /**
     *  <b>Affiche le formulaire pour modifier une règle.</b>
     * @return Le template categories en redirection
     * */
    @RequestMapping(value = "Editeur/reglechange/{id}", method = RequestMethod.POST)
    public String modifyRegle(Model model, @PathVariable("id") Long id, @RequestParam("newregle") String corpus) {
        Regle regle = regleRepository.findById(id).get();
        Boolean verifok = verificationService.inputSafe(corpus);
        System.out.println(verifok);

        if (verifok==false){
            System.out.println("merci de ne pas essayer d'entrer du code");
            return "redirect:/categories";//si tentative de code
        }

        regle.setCorpus(corpus);
        regleRepository.save(regle);

        model.addAttribute("regle", regle);

        return "redirect:/categories";// on respect le prg ,
        // à chaque fois que l'on influe l'app ou bdd
        //on ne redirige jamais sur la même page


    }

    /**
     *  <b>Affiche le formulaire pour créer une règle.</b>
     * @return Le template newregle
     * */
    @RequestMapping(value = "Gestionnaire/newnextregle/{id}", method = RequestMethod.GET)
    public String nextRegles(Model model, @PathVariable("id") Long id) {
        SousCategorie souscateg = sousCategorieRepository.findById(id).get();
        SousCategorie listregle = sousCategorieRepository.findById(id).get();
        Integer attributnumordre = listregle.getRegles().size() + 1;
        model.addAttribute("souscategorie", souscateg);
        model.addAttribute("incrementnumordre", attributnumordre);

        return "newregle";
    }

    /**
     *  <b>Ajoute une règle.</b>
     * @return Le template categories en redirection
     * */
    @RequestMapping(value = "Gestionnaire/newregletexte/{id}", method = RequestMethod.POST)
    public String NewRegle(Model model, @PathVariable("id") Long id, @RequestParam("newnumordre") Integer newnumordre, @RequestParam("newcorpus") String newcorpus) {
        SousCategorie souscateg = sousCategorieRepository.findById(id).get();
        Regle regle = new Regle();
        Boolean verifok = verificationService.inputSafe(newcorpus);
        System.out.println(verifok);

        if (verifok==false){
            System.out.println("merci de ne pas essayer d'entrer du code");
            return "redirect:/categories";//si tentative de code
        }

        newcorpus = newcorpus.replaceAll("[<>@{}=;_?&/]*", "");

        regle.setNumordre(newnumordre);
        regle.setCorpus(newcorpus);
        regle.setSouscategorie(souscateg);
        sousCategorieRepository.save(souscateg);
        regleRepository.save(regle);

        return "redirect:/categories";// on respect le prg ,
        // a chaque fois quon influe lapp ou bdd
        //on redirige jamais sur la meme page
    }

    /**
     *  <b>Supprime une règle.</b>
     * @return Le template categories en redirection
     * */
    @RequestMapping(value = "Admin/regledelete/{id}", method = RequestMethod.GET)
    public String DeleteRegle(Model model, @PathVariable("id") Long id) {

        Regle regles = regleRepository.findById(id).get();
        regleRepository.delete(regles);

        return "redirect:/categories";
    }



    /**
     *  <b>Permet de sélectionner les règles que l'on veut extraire.</b>
     * @return Le template checkregles si liste remplie.
     * @return le template categorie si aucune règles sélectionnées.
     * */
    @RequestMapping(value = "/regleselect", method = RequestMethod.POST)
    public String SelectRegle(Model model, @RequestParam("idregles") List<Long> listidregle) {  //On recupère une Liste contenant les id des règles sélectionnées

        if (listidregle.size() > 0) {
            List<Regle> Listregleselect = new ArrayList<>();



            for (Long id : listidregle) {
                System.out.println("IdRegles: "+id);
                Listregleselect.add(regleRepository.findById(id).get());
            }
            for (Regle regle : Listregleselect) {
                System.out.println("===============================================" + regle.getCorpus());
                System.out.println(regle.getSouscategorie().getTitle());
            }


            model.addAttribute("listeregles", Listregleselect);

            return "checkregles";
        }

        return "redirect:/categories";
    }



}
