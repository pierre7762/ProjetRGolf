package com.generateur_regles_locales.models;

import javax.persistence.*;
import java.util.ArrayList;
import java.util.Date;
import java.util.List;
/**
 * @author Aryles/Pierre
 * */
@Entity
public class SousCategorie {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;
    private Integer numordre;
    /**
     *Taille du texte limitée à 20 000 caractères.
     * */
    private String title;
    /**
     *Pour l'instant non-utilisé à l'affichage.
     * */
    @Column(length = 20000)
    private String objet;

    /**
     *Lien avec Catégorie.
     * */
    @ManyToOne
    private Categorie categorie;
    @OneToMany (mappedBy = "souscategorie")                     //voir correspondance clef étrangère clef primaire
    private List<Regle> regles = new ArrayList<Regle>();
    public SousCategorie() {


    }
    public List<Regle> getRegles() {
        return regles;
    }
    public void setRegles(List<Regle> regles) {
        this.regles = regles;
    }

    public Long getId() {
        return id;
    }
    public void setId(Long id) {
        this.id = id;
    }

    public Integer getNumordre() {
        return numordre;
    }
    public void setNumordre(Integer ordre) {
        this.numordre = ordre;
    }

    public String getTitle() {
        return title;
    }
    public void setTitle(String title) {
        this.title = title;
    }

    public String getObjet() {
        return objet;
    }
    public void setObjet(String objet) {
        this.objet = objet;
    }

    public Categorie getCategorie() {
        return categorie;
    }
    public void setCategorie(Categorie categorie) {
        this.categorie = categorie;
    }
}
