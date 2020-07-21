package com.generateur_regles_locales.service;


import com.generateur_regles_locales.models.UserRepository;
import com.generateur_regles_locales.models.User;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.stereotype.Service;
/**
 * @author Aryles/Pierre
 * <b>Permet l'encodage du mot de passe et la sauvegarde de l'utilisateur</b>
 * */
@Service
public class JpaUserService {
    private UserRepository userDao;
    private BCryptPasswordEncoder bCryptPasswordEncoder;

    @Autowired
    public void setUserDao(UserRepository userDao){
        this.userDao = userDao;
    }



    @Autowired
    public void setbCryptPasswordEncoder(BCryptPasswordEncoder bCryptPasswordEncoder){
        this.bCryptPasswordEncoder = bCryptPasswordEncoder;
    }

    public void save(User user){
        user.setPassword(bCryptPasswordEncoder.encode(user.getPassword()));
        userDao.save(user);
    }

    public User findByUserName(String userName){
        return userDao.findByName(userName);
    }
}


