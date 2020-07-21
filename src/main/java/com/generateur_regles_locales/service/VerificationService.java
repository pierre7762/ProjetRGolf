package com.generateur_regles_locales.service;

import com.generateur_regles_locales.models.UserRepository;
import org.jsoup.Jsoup;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;


/**
 * <p>Ce service permet de gérer la sécurité des inputs afin d'éviter une attaque XSS.</p>
 *
 * @author Aryles/Pierre
 */
@Service
public class VerificationService {
    @Autowired
    UserRepository userRepository;

    /**
     *<b>La méthode inputSafe permet de filtrer les entrées utilisateurs.</b>
     * <ul>
     *     <li>On retire les espaces de la String entrante.</li>
     *     <li>On calcule sa taille.</li>
     *     <li>On parse la string que l'on save dans un objet verifparser.</li>
     *     <li>On calcule la taille de verifparser</li>
     *     <li>On compare les deux longueurs.</li>
     * </ul><br>
     * <b>Résulat :</b>
     * <ul>
     *     <li>Longueurs identiques : @return true</li>
     *     <li>Longeurs différentes : @return false </li>
     * </ul>
     * <b>Attention si false tentative XSS</b>
     */
    public boolean inputSafe(String averifier){

        averifier = averifier.replace(" ","");

        int tailleAverifer = averifier.length();

        String verifparser = Jsoup.parse(averifier).text();
        int tailleVerifParser = verifparser.length();

        if (tailleAverifer!=tailleVerifParser){
            return false;
        }
        else{
            return true;
        }
    }













    
}
