package com.mesiproject.socialnetwork.service.impl;

import com.mesiproject.socialnetwork.model.User;
import com.mesiproject.socialnetwork.repository.UserRepository;
import com.mesiproject.socialnetwork.service.UserService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import javax.persistence.EntityNotFoundException;
import java.util.List;
import java.util.Optional;

@Service("userService")
public class UserServiceImpl implements UserService {
    @Autowired
    private UserRepository userRepository;

    @Override
    public User findByUsernameOrEmail(String usernameOrEmail){
        User user;
        try{
            user = userRepository.findByUsernameOrEmail(usernameOrEmail);
        }
        catch (Exception e){
            throw e;
        }
        return user;
    }

    public User findById(Long id) {
        Optional<User> user = this.userRepository.findById(id);
        if(!user.isPresent()){
            throw new EntityNotFoundException("Impossible de trouver l'utilisateur d'identifiant " + id);
        }
        return user.get();
    }

    public User createUser(User user) {
        return userRepository.save(user);
    }

    public User updateUser(User user) {
        return userRepository.save(user);
    }

    public void deleteUser(Long id) { //fonction pour admin ou quand user veut supprimer son compte
        userRepository.deleteById(id);
    }

    public List<User> findAllUsers() {
        return userRepository.findAll();
    }

    public List<User> findAllByIdNot(Long userId) {
        return userRepository.findAllByIdNot(userId);
    }

}
