package com.generateur_regles_locales.models;

import javax.persistence.*;
import java.util.ArrayList;
import java.util.List;
/**
 * <b>Catégorie est le point de départ des règles</b>
 *<p>Pour afficher les règles nous avons :</p>
 * <ul>
 *     <li>des catégories</li>
 *     <li>des sous-catégories</li>
 *     <li>et des règles</li>
 * </ul>
 *
 * @author Aryles/Pierre
 */
@Entity
public class Categorie {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;
    /**
     * Titre de la catégorie
     * */
    private String title;
    /** Le code correspond à la lettre du sommaire, ex:
     * <ul>
     *     <li>|A.titre de la catégorie</li>
     *     <li>|B.titre de la catégorie</li>
     * </ul>
     * */
    private String code;

    public void setCode(String code) {
        this.code = code;
    }

    public String getCode() {
        return code;
    }

    public List<SousCategorie> getSousCategories() {
        return souscategories;
    }

    public void setSousCategories(List<SousCategorie> souscategories) {
        this.souscategories = souscategories;
    }

    /**
     * On récupère une liste de sous-catégories. Mapped a id de sous-catégories
     * */
    @OneToMany (mappedBy = "categorie")
    private List<SousCategorie> souscategories= new ArrayList<SousCategorie>();

    public Categorie() {

    }

    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public String getTitle() {
        return title;
    }

    public void setTitle(String title) {
        this.title = title;
    }

}
