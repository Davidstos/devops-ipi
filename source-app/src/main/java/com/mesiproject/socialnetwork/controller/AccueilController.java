package com.mesiproject.socialnetwork.controller;

import com.mesiproject.socialnetwork.model.ChatGroup;
import com.mesiproject.socialnetwork.model.Friends;
import com.mesiproject.socialnetwork.model.User;
import com.mesiproject.socialnetwork.security.CustomUserDetails;
import com.mesiproject.socialnetwork.service.ChatGroupService;
import com.mesiproject.socialnetwork.service.FriendsService;
import com.mesiproject.socialnetwork.service.UserService;
import com.mesiproject.socialnetwork.service.impl.UserServiceImpl;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.MediaType;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.servlet.ModelAndView;
import org.springframework.web.servlet.view.RedirectView;

import javax.persistence.EntityExistsException;
import javax.persistence.EntityNotFoundException;

@RestController
public class AccueilController {

    @Autowired
    private UserServiceImpl userService;

    @Autowired
    private ChatGroupService chatGroupService;

    @Autowired
    private FriendsService friendsService;

    @RequestMapping(
            method = RequestMethod.GET,
            value = "/welcome" //URL
    )

    /*public ModelAndView accueil(@RequestParam(value = "username", required = false)String username){
        ModelAndView model = new ModelAndView("welcome");
        //model.addObject("user",user);
        return model;
    }*/

    public ModelAndView accueil(Authentication authentication){
        ModelAndView model = new ModelAndView("welcome");
        CustomUserDetails userDetails =
                (CustomUserDetails) SecurityContextHolder
                        .getContext()
                        .getAuthentication()
                        .getPrincipal();
        model.addObject("user",userDetails);
        return model;
    }

}
