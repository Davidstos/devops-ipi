package com.mesiproject.socialnetwork.service;

import com.mesiproject.socialnetwork.model.ChatGroup;
import com.mesiproject.socialnetwork.model.ChatGroupUser;
import com.mesiproject.socialnetwork.model.Message;
import com.mesiproject.socialnetwork.model.User;
import com.mesiproject.socialnetwork.repository.ChatGroupRepository;
import com.mesiproject.socialnetwork.repository.ChatGroupUserRepository;
import com.mesiproject.socialnetwork.repository.MessageRepository;
import com.mesiproject.socialnetwork.repository.UserRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import javax.persistence.EntityNotFoundException;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Optional;

@Service
public class MessageService {

    @Autowired
    private MessageRepository messageRepository;

    @Autowired
    private ChatGroupRepository chatGroupRepository;

    @Autowired
    private ChatGroupUserRepository chatGroupUserRepository;

    @Autowired
    private UserRepository userRepository;


    public Message findById(Long id) {
        Optional<Message> message = this.messageRepository.findById(id);
        if(message.isEmpty()){
            throw new EntityNotFoundException("Impossible de trouver le message d'identifiant " + id);
        }
        return message.get();
    }

    public Message createMessage(Message message) {
        return messageRepository.save(message);
    }

    public Message updateMessage(Message message) {
        return messageRepository.save(message);
    }

    public void deleteMessage(Long id) {
        messageRepository.deleteById(id);
    }

    public List<Message> findAllMessagesOfGroupChat(Long groupChatId) {
        //On veut trouver tout les messages du group, order by date (ou id ?)
        Optional<ChatGroup> chatGroup = chatGroupRepository.findById(groupChatId);
        if(chatGroup.isEmpty()){
            throw new EntityNotFoundException("Impossible de trouver le chatGroup d'identifiant " + groupChatId);
        }
        return chatGroup.get().getMessages();
    }

/*    public Message saveMessage(){

    }*/
}
