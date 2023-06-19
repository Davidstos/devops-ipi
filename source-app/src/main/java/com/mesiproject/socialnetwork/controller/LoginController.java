package com.mesiproject.socialnetwork.controller;

import org.springframework.http.MediaType;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.servlet.ModelAndView;

import javax.persistence.EntityNotFoundException;
import java.util.Optional;

@RestController
public class LoginController {


    /*@RequestMapping(
            method = RequestMethod.GET,
            value = "/" //URL
    )
    public ModelAndView accueil(){
        ModelAndView model = new ModelAndView("login");
        //model.addObject("user",userService.findById(id));
        return model;
    }*/

    @RequestMapping(
            method = RequestMethod.GET,
            value = "/" //URL
    )
    public ModelAndView login(){
        ModelAndView model = new ModelAndView("login");
        //model.addObject("user",userService.findById(id));
        return model;
    }

    /*@PostMapping(path="/login")
    public ResponseEntity<User> loginUser(@RequestParam("email") String email, @RequestParam("password") String password) {
        //your imp
    }*/
}


