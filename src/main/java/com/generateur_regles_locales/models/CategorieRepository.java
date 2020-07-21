package com.generateur_regles_locales.models;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;

import java.util.List;

public interface CategorieRepository extends JpaRepository<Categorie, Long>{

    @Query(value = "select distinct * from CATEGORIE order by CODE ",nativeQuery = true)
    List<Categorie> orderListcodeCategorie();

}
