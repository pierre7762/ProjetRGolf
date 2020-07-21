package com.generateur_regles_locales.models;

import javax.persistence.*;
import java.util.Objects;
import java.util.Set;

/**
 * <p>La plus grande partie des personnes qui vont utiliser l'application seront des arbitres.</p>
 * <p>Ils viendront sélectionner des règles, les extraire mais ils ne se connecteront pas.</p>
 *
 * <p>Dans l'application : les utilisateurs sont les personnes connectées qui ont le droit d'effectuer des modifications.</p>
 *
 * <b>Permet de définir ce qui compose un utilisateur.</b>
 * <p>Modifier ici pour ajouter ou retirer des informations sur les utilisateurs.</p>
 * @author Aryles/Pierre
 */
@Entity
public class User {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "ID", nullable = false)
    private long id;
    private String name;
    /**
     * Les passwords sont encodés avec bCryptPasswordEncoder.
     * */
    private String password;
    /**
     * Non-utilisé pour l'instant
     * */
    private boolean active;
    private String mail;


    @ManyToOne
    private Role role;

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


    public String getPassword() {
        return password;
    }

    public void setPassword(String password) {
        this.password = password;
    }


    public boolean isActive() {
        return active;
    }

    public void setActive(boolean active) {
        this.active = active;
    }


    public String getMail() {
        return mail;
    }

    public void setMail(String mail) {
        this.mail = mail;
    }





    public Role getRole() {
        return role;
    }

    public void setRole(Role role) {
        this.role = role;
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;
        User user = (User) o;
        return id == user.id &&
                active == user.active &&
                Objects.equals(name, user.name) &&
                Objects.equals(password, user.password) &&
                Objects.equals(mail, user.mail);
    }

    @Override
    public int hashCode() {
        return Objects.hash(id, name, password, active, mail);
    }

}

