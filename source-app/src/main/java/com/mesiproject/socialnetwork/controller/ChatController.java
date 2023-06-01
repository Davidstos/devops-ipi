package com.mesiproject.socialnetwork.controller;

import com.mesiproject.socialnetwork.model.*;
import com.mesiproject.socialnetwork.security.CustomUserDetails;
import com.mesiproject.socialnetwork.service.ChatGroupService;
import com.mesiproject.socialnetwork.service.ChatGroupUserService;
import com.mesiproject.socialnetwork.service.MessageService;
import com.mesiproject.socialnetwork.service.impl.UserServiceImpl;
import org.apache.el.stream.Optional;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.MediaType;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.servlet.ModelAndView;
import org.springframework.web.servlet.view.RedirectView;

import javax.persistence.EntityExistsException;
import javax.persistence.EntityNotFoundException;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

@RestController
@RequestMapping("/chats")
public class ChatController {

    @Autowired
    private ChatGroupService chatGroupService;

    @Autowired
    private MessageService messageService;

    @Autowired
    private ChatGroupUserService chatGroupUserService;

    @Autowired
    private UserServiceImpl userService;



/*    @RequestMapping(
            method = RequestMethod.GET,
            value ="/{id}"
    )
    public ModelAndView detailChat(@PathVariable Long id){
        ModelAndView model = new ModelAndView("messagerie");
        if(id != null){
            model.addObject("chatGroup",chatGroupService.findById(id));
            return model;
        }
        throw new EntityNotFoundException("La discussion d'id " + id + " n'existe pas !");
    }*/

    @RequestMapping(
            method = RequestMethod.GET,
            value =""
    )
    public ModelAndView getAllChats(){
        ModelAndView model = new ModelAndView("chatsList");
        CustomUserDetails userDetails =
                (CustomUserDetails) SecurityContextHolder
                        .getContext()
                        .getAuthentication()
                        .getPrincipal();


        //Méthode 1 pour récupérer les chat groups d'un user avec l'id de celui-ci
        //List<ChatGroup> allChats = chatGroupService.getChatGroupsOfUser(userDetails.getId());

        //Méthode 2 : Le User chargé de Spring security n'a pas toutes les sous List d' objets (chatgroups, friends), il faut donc faire une requête de BDD pour récupérer le User entier
        List<ChatGroup> allChats = userService.findById(userDetails.getId()).getChatGroups();
        model.addObject("allChats",allChats);
        return model;
    }

    @RequestMapping(
            method = RequestMethod.GET,
            value = "/newChat"
    )
    public ModelAndView newChat(){ // quand on va dans artists/new , ca nous redirige vers un détail d'artise vide. Ensuite le btn enregistrer utilise la fonction createArtist  (POST)
        ModelAndView model = new ModelAndView("newChat");
        CustomUserDetails userDetails =
                (CustomUserDetails) SecurityContextHolder
                        .getContext()
                        .getAuthentication()
                        .getPrincipal();
        ChatGroup chatGroup  = new ChatGroup();
        model.addObject("user", userDetails);
        model.addObject("chatGroup", chatGroup);
        return model;
    }

    @RequestMapping(
            method = RequestMethod.GET,
            value ="/addFriend/{chatGroupId}"
    )
    public ModelAndView allFriends(@PathVariable Long chatGroupId){
        ModelAndView model = new ModelAndView("friendListAddToChatGroup");
        CustomUserDetails userDetails =
                (CustomUserDetails) SecurityContextHolder
                        .getContext()
                        .getAuthentication()
                        .getPrincipal();
        model.addObject("chatGroupId", chatGroupId);
        model.addObject("friends",userService.findById(userDetails.getId()).getFriends());
        return model;
    }


    @RequestMapping(
            method = RequestMethod.POST,
            value = "/add",
            consumes = MediaType.APPLICATION_FORM_URLENCODED_VALUE
    )
    public RedirectView createChatGroup(Long userId, String chatName){

        if(chatName.trim().length()>0){ //vérifie si le user n'a pas mis que des espaces
            ChatGroup chatGroup = new ChatGroup();
            try {
                chatGroup.setName(chatName);
                /*if(chatGroupService.checkExistsByName(chatGroup.getName())){
                    throw new EntityExistsException("Il existe déjà une discussion identique en base");
                }*/
                chatGroup = chatGroupService.createChatGroup(chatGroup);
                ChatGroupUser chatGroupUser = new ChatGroupUser(userId, chatGroup.getId());
                chatGroupUser = chatGroupService.createChatGroupUser(chatGroupUser);
            }
            catch(Exception e){
                throw new IllegalArgumentException("Problème lors de la création de la discussion");
            }
        }
        else{
            throw new IllegalArgumentException("Veuillez remplir le champ du nom de l'artiste");
        }
        return new RedirectView("/chats");
    }

    @RequestMapping(
            method = RequestMethod.GET,
            value = "/addUser/{userId}/{chatGroupId}"
    )
    public RedirectView addUserToChatGroup(@PathVariable Long userId, @PathVariable Long chatGroupId){
        try {
            chatGroupService.findById(chatGroupId);
            userService.findById(userId);
            ChatGroupUserId chatGroupUserId = new ChatGroupUserId(userId, chatGroupId);
            if (!chatGroupUserService.checkChatGroupUserLink(chatGroupUserId)){
                ChatGroupUser chatGroupUser = new ChatGroupUser(userId, chatGroupId);
                chatGroupService.createChatGroupUser(chatGroupUser);
            }
            return new RedirectView("/chats/" + chatGroupId);
        }
        catch(Exception e){
            System.out.println(e);
            throw new IllegalArgumentException("Problème lors de l'ajout de l'utilisateur");
        }

    }


   @RequestMapping(
            value = "/{groupChatId}",
            method = RequestMethod.GET
    )
    public ModelAndView findAllMessagesOfGroupChat(@PathVariable Long groupChatId){


       CustomUserDetails userDetails =
               (CustomUserDetails) SecurityContextHolder
                       .getContext()
                       .getAuthentication()
                       .getPrincipal();

       List<User> usersOfGroup = chatGroupService.findAllUsersOfChatGroup(groupChatId);
       ModelAndView model = new ModelAndView("chatGroup");
       model.addObject("allMessages",messageService.findAllMessagesOfGroupChat(groupChatId));
       model.addObject("allUsers",usersOfGroup);
       model.addObject("userLogged",userService.findById(userDetails.getId()));
       model.addObject("chatGroupId", groupChatId);
       return model;
    }


    @RequestMapping(
            method = RequestMethod.POST,
            value = "/{groupChatId}/{userId}/addMessage",
            consumes = MediaType.APPLICATION_FORM_URLENCODED_VALUE
    )
    public RedirectView addMessageToGroupChat(@PathVariable Long groupChatId, @PathVariable Long userId, String messageText){
        ChatGroup chatGroup =  chatGroupService.findById(groupChatId);
        if(messageText.trim().length()>0){ //vérifie si le user n'a pas mis que des espaces
            try {
                Message message = new Message(messageText,userId,chatGroup);
                messageService.createMessage(message);
            }
            catch(Exception e){
                throw new IllegalArgumentException("Problème lors de l'envoi du message");
            }
        }
        else{
            throw new IllegalArgumentException("Veuillez remplir le champ du texte du message");
        }
        return new RedirectView("/chats/" + chatGroup.getId());
/*        System.out.println(messageText);
        System.out.println(userId);
        System.out.println(groupChatId);*/
    }


    @RequestMapping(
            method = RequestMethod.GET,
            value = "/{id}/delete"
    )
    public RedirectView deleteUserFromChatGroup(@PathVariable Long id) {

        CustomUserDetails userDetails =
                (CustomUserDetails) SecurityContextHolder
                        .getContext()
                        .getAuthentication()
                        .getPrincipal();
        ChatGroupUserId chatGroupUserId = new ChatGroupUserId(userDetails.getId(),id);

        List<User> users= chatGroupService.findAllUsersOfChatGroup(id);
        if(users.size() == 1){
            chatGroupService.deleteChatGroup(id);
        }
        else{
            chatGroupUserService.deleteChatGroupUser(chatGroupUserId);
        }
        return new RedirectView("/chats");
    }

}
