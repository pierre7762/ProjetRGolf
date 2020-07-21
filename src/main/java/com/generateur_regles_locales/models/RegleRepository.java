package com.generateur_regles_locales.models;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.List;

public interface RegleRepository extends JpaRepository<Regle, Long>{
    List<Regle> findAllBySouscategorie(SousCategorie sousCategorie);//dsl =se traduit par une requete sql

}


