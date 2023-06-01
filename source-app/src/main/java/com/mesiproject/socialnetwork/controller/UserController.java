package com.mesiproject.socialnetwork.controller;

import com.mesiproject.socialnetwork.dto.UserDto;
import com.mesiproject.socialnetwork.model.ChatGroup;
import com.mesiproject.socialnetwork.model.Friends;
import com.mesiproject.socialnetwork.model.FriendsId;
import com.mesiproject.socialnetwork.model.User;
import com.mesiproject.socialnetwork.security.CustomUserDetails;
import com.mesiproject.socialnetwork.service.ChatGroupService;
import com.mesiproject.socialnetwork.service.FriendsService;
import com.mesiproject.socialnetwork.service.UserService;
import com.mesiproject.socialnetwork.service.impl.UserServiceImpl;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.MediaType;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.servlet.ModelAndView;
import org.springframework.web.servlet.view.RedirectView;

import javax.persistence.EntityNotFoundException;
import java.util.List;

@RestController
@RequestMapping("/user")
public class UserController {

    @Autowired
    private UserServiceImpl userService;

    @Autowired
    private FriendsService friendsService;

    @Autowired
    private ChatGroupService chatGroupService;


    @RequestMapping(
            method = RequestMethod.GET,
            value ="/{id}"
    )
    public ModelAndView detailUser(@PathVariable Long id){
        ModelAndView model = new ModelAndView("userDetails");
        if(id != null){
            model.addObject("user",userService.findById(id));
            return model;
        }
        throw new EntityNotFoundException("L'utilisateur d'id " + id + " n'existe pas !");
    }


    @RequestMapping(
            method = RequestMethod.GET,
            value = "/{id}/friends/newFriend"
    )
    public ModelAndView newFriend(){ // quand on va dans artists/new , ca nous redirige vers un détail d'artise vide. Ensuite le btn enregistrer utilise la fonction createArtist  (POST)
        ModelAndView model = new ModelAndView("newFriend");
        Friends friend  = new Friends();
        CustomUserDetails userDetails =
                (CustomUserDetails) SecurityContextHolder
                        .getContext()
                        .getAuthentication()
                        .getPrincipal();
        List<User> allOtherUsersUsers = userService.findAllByIdNot(userDetails.getId());
        for (User allOtherUsersUser : allOtherUsersUsers) {
            System.out.println(allOtherUsersUser.getUsername());
        }

        model.addObject("allUsers", allOtherUsersUsers);
        model.addObject("userLogged", userDetails);
        return model;
    }

    @RequestMapping(
            method = RequestMethod.GET,
            value = "friends/{id}/add"
    )
    public RedirectView addFriend(@PathVariable Long id){

        User userNewFriend = userService.findById(id);
        CustomUserDetails userDetails =
                (CustomUserDetails) SecurityContextHolder
                        .getContext()
                        .getAuthentication()
                        .getPrincipal();
        System.out.println("Le user connecté : " + userDetails.getFirstName() + " d'id : " + userDetails.getId());
        System.out.println("demande en ami le user : " + userNewFriend.getFirstName() + " d'id : " + id);
        System.out.println(id);
        Friends newRelation = new Friends(userDetails.getId(),id);
        friendsService.addFriend(newRelation);
        return new RedirectView("/user/" + userDetails.getId() + "/friends");
        //friendsService.addFriend(userNewFriend);
        /*if(user.getUsername().trim().length()>0){ //vérifie si le user n'a pas mis que des espaces
            try {
                if(user.getId() == null){
                    //Création
                    user = userService.createUser(user);
                }
                else {
                    //Modification
                    user = userService.updateUser(user);
                }
            }
            catch(Exception e){
                throw new IllegalArgumentException("Problème lors de la sauvegarde de la discussion");
            }
        }
        else{
            throw new IllegalArgumentException("Veuillez remplir le champ du nom de l'artiste");
        }
        return new RedirectView("/user/" + user.getId());*/
    }

    @RequestMapping(
            method = RequestMethod.GET,
            value = "friends/{id}/delete"
    )
    public RedirectView deleteFriend(@PathVariable Long id) {

        CustomUserDetails userDetails =
                (CustomUserDetails) SecurityContextHolder
                        .getContext()
                        .getAuthentication()
                        .getPrincipal();
        friendsService.deleteFriend(userDetails.getId(), id);
        return new RedirectView("/user/" + userDetails.getId() + "/friends");
    }


    @RequestMapping(
            method = RequestMethod.GET,
            value ="/{id}/friends"
    )
    public ModelAndView allFriends(@PathVariable Long id){
        ModelAndView model = new ModelAndView("friendList");
        if(id != null){
            model.addObject("friends",userService.findById(id).getFriends());
            return model;
        }
        throw new EntityNotFoundException("L'utilisateur d'id " + id + " n'existe pas !");
    }

    @RequestMapping(
            method = RequestMethod.GET,
            value = "/{id}/newUser"
    )
    public ModelAndView newUser(){ //  Pour Login création de compte
        ModelAndView model = new ModelAndView("userDetails");
        User user  = new User();
        model.addObject("user", user);
        return model;
    }


    @RequestMapping(
            method = RequestMethod.POST,
            value = "",
            consumes = MediaType.APPLICATION_FORM_URLENCODED_VALUE
    )
    public RedirectView createUser(User user){

        /*if(chatGroupService.checkExistsByName(chatGroup.getName())){
            throw new EntityExistsException("Il existe déjà une discussion identique en base");
        }*/

        if(user.getUsername().trim().length()>0){ //vérifie si le user n'a pas mis que des espaces
            try {
                if(user.getId() == null){
                    //Création
                    user = userService.createUser(user);
                }
                else {
                    //Modification
                    user = userService.updateUser(user);
                }
            }
            catch(Exception e){
                throw new IllegalArgumentException("Problème lors de la sauvegarde de la discussion");
            }
        }
        else{
            throw new IllegalArgumentException("Veuillez remplir le champ du nom de l'artiste");
        }
        return new RedirectView("/user/" + user.getId());
    }
}
