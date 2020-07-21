package com.generateur_regles_locales.models;

import javax.persistence.*;
import java.util.Objects;
import java.util.Set;
/**
 * <b>Il existe 3 types de rôles :</b>
 * <ul>
 *     <li>Admin : a tous les pouvoirs sur l'application. Il est le seul à pouvoir gérer les utilisateurs.</li>
 *     <li>Gestionnaire : peut créer des catégories, sous-catégories, règles et les modifier.</li>
 *     <li>Editeur : peut réaliser des modifications de règles.</li>
 * </ul>
 *
 *  @author Aryles/Pierre
 * */
@Entity
@Table(name= "Role")
public class Role {
    @Id
    @GeneratedValue(strategy = GenerationType.AUTO)
    @Column(name = "id", nullable = false)
    private long id;
    /**
     * Le name correspond au nom du rôle afin de ne pas être contraint de l'afficher en majuscule.
     * */
    private String name;
    /**
     * Le rôle correspond au nom écrit dans la base de données.
     * */
    private String role;

    public long getId() {
        return id;
    }

    public void setId(long id) {
        this.id = id;
    }



    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }



    public String getRole() {
        return role;
    }

    public void setRole(String role) {
        this.role = role;
    }

}

