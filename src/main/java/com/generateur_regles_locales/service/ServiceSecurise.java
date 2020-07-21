package com.generateur_regles_locales.service;

import com.generateur_regles_locales.models.User;
import com.generateur_regles_locales.models.UserRepository;
import org.jsoup.Jsoup;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.access.prepost.PostAuthorize;
import org.springframework.security.access.prepost.PreAuthorize;
import org.springframework.stereotype.Service;

@Service
public class ServiceSecurise {
    @Autowired
    UserRepository userRepository;

    @PreAuthorize("hasAuthority('ADMIN')")
    @PostAuthorize("hasAuthority('ROOT')")
    public void machin(){
        System.out.println("traitement machin");
    }





}

