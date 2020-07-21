package com.generateur_regles_locales.configuration;

import com.generateur_regles_locales.service.JpaUserDetailsService;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.security.config.annotation.authentication.builders.AuthenticationManagerBuilder;
import org.springframework.security.config.annotation.authentication.configurers.provisioning.InMemoryUserDetailsManagerConfigurer;
import org.springframework.security.config.annotation.web.builders.HttpSecurity;
import org.springframework.security.config.annotation.web.configuration.EnableWebSecurity;
import org.springframework.security.config.annotation.web.configuration.WebSecurityConfigurerAdapter;
import org.springframework.security.core.userdetails.User;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.security.provisioning.InMemoryUserDetailsManager;
/**
 * <b>Cette classe permet de paramétrer le service SpringSecurity</b>
 * @author Aryles/Pierre
 * */
@Configuration
@EnableWebSecurity
public class WebSecurityConfig extends WebSecurityConfigurerAdapter {
    private JpaUserDetailsService jpaUserDetailsService;
    private Logger log = LoggerFactory.getLogger(this.getClass());

    @Autowired
    public void setUserDetailsService(JpaUserDetailsService jpaUserDetailsService) {
        this.jpaUserDetailsService = jpaUserDetailsService;
    }

    /**
     * Permet de chiffrer les mots de passe
     * il hash le mot de passe et y ajoute un grain de sel.
     * */
    @Bean
    public BCryptPasswordEncoder bCryptPasswordEncoder(){
        return new BCryptPasswordEncoder();
    }
    //important , chiffrer le mot de passe dans la bdd obligatoire
    ///////////////////////////////////////////////////////
    //il est crypté dans un sens il hash le mp mais avec le hash on peut pas avoir le mp pour les hackers
    //mp= mp user +sel

    // prends mp user ce mp user on rajoute le sel associé au mp hashé on rehash
    // le sel + mp et on vérifie si le résultat = résultat dans le bdd hashé.

//AJOUTER JUSTE UN PACKAGE CONFIGURATION, LE RESTE LIBRE

/**
 * <b>Paramétrage de la sécurité des accès</b>
 *<br>
 * <b>antMatchers(/uri).permitAll()</b>
 * <ul>
 *     <li>Ici inscrire les URI que l'on souhaite laisser accessibles sans identification.</li>
 *     <li><b>Attention :</b> penser à donner l'accès au css</li>
 * </ul><br>
 * <b>antMatchers(/Admin/**).hasAuthority("ADMIN")</b>
 * <ul>
 *     <li>Dans la parenthèse du antMatchers, inscrire l'URI du rôle suivi de /** </li>
 *     <li>le /** indique que toutes les URI qui commencent par Admin seront concernées</li>
 * </ul><br>
 * <b>.hasAuthority("ADMIN")</b>
 * <ul>
 *     <li>Seul l'admin y à accès</li>
 * </ul><br>
 * <b>.hasAnyAuthority("ADMIN","Gestionnaire")</b>
 * <ul>
 *     <li>Pour en avoir plusieurs</li>
 * </ul><br>
 * <b>.loginPage("/connexion")</b>
 * <ul>
 *    <li>Permet d'affecter une page de login personnalisée.</li>
 *    <li>Dans les () mettre l'URI du template</li>
 *    <li>Penser à le faire suivre d'un .permitAll afin que tout le monde y ait accès</li>
 *
 * </ul><br>
 * <b>.defaultSuccessUrl("/categories",true)</b>
 * <ul>
 *     <li>Permet de définir la page à afficher quand le login est réussi</li>
 * </ul><br>
 * <b>.logoutUrl("/deconnexion")</b>
 * <ul>
 *     <li>Uri de déconnexion</li>
 * </ul><br>
 * <b>.logoutSuccessUrl("/categories")</b>
 * <ul>
 *     <li>Permet de définir la page à afficher quand la déconnexion est réussie</li>
 * </ul>
 *
 *
 * */
    @Override //ici on sécurise les routes
    protected void configure(HttpSecurity http) throws Exception {
        http
                //garder le disable en commentaire
                //.csrf().disable()
                .authorizeRequests()//on autorise certaines requêtes
                .antMatchers("/", "/categories","/home","/login","/regleselect","/css/**","/deconnexion","/listeVide").permitAll()//on autorise l'uri /home à tout le monde .permitAll()==permis à tous
                //.antMatchers("/admin/**").hasAuthority("ADMIN") //** = n'importe quel chemin à partir de /admin
                //quand on reload voir cela comme une exclusion d'uri, là on autorise tout à part /admin/**
                .antMatchers("/Admin/**").hasAuthority("ADMIN")
                .antMatchers("/Gestionnaire/**").hasAnyAuthority("GESTIONNAIRE","ADMIN")
                .antMatchers("/Editeur/**").hasAnyAuthority("EDITEUR","GESTIONNAIRE","ADMIN")


                //.antMatchers("/gestionnaire/**").hasAnyAuthority("ADMIN","GESTIONNAIRE")
                .anyRequest().permitAll()
                //.anyRequest().authenticated()//là on précise que, quelque soit la requête on doit obligatoirement être authentifié
                // au-dessus on spécifie des requêtes autorisées  .hasAuthority("ADMIN") et permitAll() à partir du moment où il y a /home
                .and()
                .formLogin()//le formulaire de login est disponible à l'uri /login qui correspond à l'action dans le formulaire login à l'intérieur du template
                .loginPage("/connexion")
                .loginProcessingUrl("/login")
                .defaultSuccessUrl("/categories",true)
                .permitAll()//permet à tout le monde de se log .permitAll()= full autorisation , permit à tous
                .and()
                .logout()
                .logoutUrl("/deconnexion")
                .logoutSuccessUrl("/categories")
                .permitAll();//permet à tous de se logout .
        //au moment de valider la connexion pour un user ===> en Php un attribut dans le contrôleur userdetails
    }




}

