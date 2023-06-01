package com.mesiproject.socialnetwork.service;

import com.mesiproject.socialnetwork.model.ChatGroupUserId;
import com.mesiproject.socialnetwork.repository.ChatGroupRepository;
import com.mesiproject.socialnetwork.repository.ChatGroupUserRepository;
import com.mesiproject.socialnetwork.repository.UserRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

@Service
public class ChatGroupUserService {


    @Autowired
    private ChatGroupRepository chatGroupRepository;

    @Autowired
    private ChatGroupUserRepository chatGroupUserRepository;


    @Autowired
    private UserRepository userRepository;

    public void deleteChatGroupUser(ChatGroupUserId chatGroupUserId) {
        chatGroupUserRepository.deleteById(chatGroupUserId);
    }

    public Boolean checkChatGroupUserLink(ChatGroupUserId chatGroupUserId){
        return chatGroupUserRepository.existsById(chatGroupUserId);
    }


}
