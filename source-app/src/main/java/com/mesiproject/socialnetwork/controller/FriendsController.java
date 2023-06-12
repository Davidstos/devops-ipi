package com.mesiproject.socialnetwork.controller;

import com.mesiproject.socialnetwork.model.Friends;
import com.mesiproject.socialnetwork.model.User;
import com.mesiproject.socialnetwork.service.FriendsService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.servlet.ModelAndView;

import java.util.List;

public class FriendsController {

    @Autowired
    FriendsService friendsService;


    /*@RequestMapping(
            method = RequestMethod.GET,
            value = "friends/new"
    )
    public ModelAndView newFriend(){ // quand on va dans artists/new , ca nous redirige vers un détail d'artise vide. Ensuite le btn enregistrer utilise la fonction createArtist  (POST)
        ModelAndView model = new ModelAndView("newFriend");
        Friends friend  = new Friends();
        List<User> allUsers = friendsService.findAllUsers();
        model.addObject("allUsers", allUsers);
        return model;
    }*/
}
