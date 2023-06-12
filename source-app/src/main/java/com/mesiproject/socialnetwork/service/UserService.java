package com.mesiproject.socialnetwork.service;

import com.mesiproject.socialnetwork.dto.UserDto;
import com.mesiproject.socialnetwork.model.Friends;
import com.mesiproject.socialnetwork.model.User;
import com.mesiproject.socialnetwork.repository.UserRepository;
import com.mesiproject.socialnetwork.security.CustomUserDetails;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import javax.persistence.EntityNotFoundException;
import java.util.Optional;

//@Service
public interface UserService {

    /*@Autowired
    private UserRepository userRepository;*/


    /*public User findById(Long id) {
        Optional<User> user = this.userRepository.findById(id);
        if(!user.isPresent()){
            throw new EntityNotFoundException("Impossible de trouver l'utilisateur d'identifiant " + id);
        }
        return user.get();
    }*/

    /*public User findByUsernameAndPassword(String username, String password){
        User user;
        try{
             user = userRepository.findUserByUsernameAndPassword(username,password);
        }
        catch (Exception e){
            throw e;
        }
        return user;
    }*/

    User findByUsernameOrEmail(String usernameOrEmail);

    /*public User createUser(User user) {
        return userRepository.save(user);
    }

    public User updateUser(User user) {
        return userRepository.save(user);
    }

    public void deleteUser(Long id) { //fonction pour admin ou quand user veut supprimer son compte
        userRepository.deleteById(id);
    }*/

}
