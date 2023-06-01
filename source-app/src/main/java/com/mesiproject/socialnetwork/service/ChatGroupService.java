package com.mesiproject.socialnetwork.service;

import com.mesiproject.socialnetwork.model.*;
import com.mesiproject.socialnetwork.repository.ChatGroupRepository;
import com.mesiproject.socialnetwork.repository.ChatGroupUserRepository;
import com.mesiproject.socialnetwork.repository.UserRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import javax.persistence.EntityNotFoundException;
import java.util.*;

@Service
public class ChatGroupService {

    @Autowired
    private ChatGroupRepository chatGroupRepository;

    @Autowired
    private ChatGroupUserRepository chatGroupUserRepository;


    @Autowired
    private UserRepository userRepository;


    public ChatGroup findById(Long id) {
        Optional<ChatGroup> chatGroup = this.chatGroupRepository.findById(id);
        if(!chatGroup.isPresent()){
            throw new EntityNotFoundException("Impossible de trouver la discussion d'identifiant " + id);
        }
        return chatGroup.get();
    }

    public ChatGroup createChatGroup(ChatGroup chatGroup) {
        return chatGroupRepository.save(chatGroup);
    }


    public ChatGroup updateChatGroup(ChatGroup chatGroup) {
        return chatGroupRepository.save(chatGroup);
    }

    public void deleteChatGroup(Long id) {
        chatGroupRepository.deleteById(id);
    }

    public ChatGroupUser createChatGroupUser(ChatGroupUser chatGroupUser){
        return chatGroupUserRepository.save(chatGroupUser);
    }

/*    public List<ChatGroup> getChatGroupsOfUser(Long id){
        List<ChatGroupUser> chatGroupUsers = chatGroupUserRepository.findAllByUserId(id);
        List<ChatGroup> chatGroups = new ArrayList<>();
        for (ChatGroupUser chatGroupUser : chatGroupUsers ){
            Long chatGroupId = chatGroupUser.getChatGroupId();
            Optional<ChatGroup> chatGroup = chatGroupRepository.findById(chatGroupId);
            chatGroups.add(chatGroup.get());
        }
        return chatGroups;
    }*/

    public List<User> findAllUsersOfChatGroup(Long id){
        List<ChatGroupUser> chatGroupUsers = chatGroupUserRepository.findAllByChatGroupId(id);
        List<User> usersOfGroup = new ArrayList<>();
        for (ChatGroupUser chatGroupUser : chatGroupUsers) {
            Optional<User> user = userRepository.findById(chatGroupUser.getUserId());
            if(user.isEmpty()){
                throw new EntityNotFoundException("Impossible de trouver le user d'identifiant " + chatGroupUser.getUserId());
            }
            usersOfGroup.add(user.get());
        }
        return usersOfGroup;
    }


/*    public ChatGroup findById(Long userId, Long chatGroupId) {
        Optional<ChatGroup> ChatGroup = this.chatGroupRepository.findById(new ChatGroupUserId(userId, chatGroupId));
        if(!friend.isPresent()){
            throw new EntityNotFoundException("Impossible de trouver l'ami");
        }
        return friend.get();
    }*/

}
