package com.generateur_regles_locales.controllers;


import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;

/**
 * @author Aryles/Pierre
 * <br>
 * <b>Affichage de la page d'accueil.</b>
 * */
@Controller
public class HomeController {
    /**
     *  <b>Page d'accueil.</b>
     * @return Le template index
     * */
    @RequestMapping("/home")
    public String index(){

        return "index";
    }

    /**
     *  <b>Page de connexion.</b>
     * @return Le template connexion
     * */
    @RequestMapping(value = "/connexion", method = RequestMethod.GET)
    public String seconnecter(Model model) {
        return "connexion";
    }


}

