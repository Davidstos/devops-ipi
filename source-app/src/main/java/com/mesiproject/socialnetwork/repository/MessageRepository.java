package com.mesiproject.socialnetwork.repository;

import com.mesiproject.socialnetwork.model.ChatGroup;
import com.mesiproject.socialnetwork.model.Message;
import com.mesiproject.socialnetwork.model.User;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.stereotype.Repository;

import java.util.List;


@Repository
public interface MessageRepository extends JpaRepository<Message, Long> {

    //Trouve tout les commentaires de l'utilisateur dans le chatGroup
    //@Query("SELECT u FROM Message u WHERE u.userSender=:userSender AND u.chatGroup=:chatGroup")
    public List<Message> findAllByUserSenderAndChatGroup(Long userSender, Long chatGroup);

    @Query("SELECT u FROM Message u WHERE u.chatGroup=:chatGroup")
    public List<Message> findAllByChatGroup(Long chatGroup);

    //List<Message> findAllByUserSenderAndChatGroup(User user, ChatGroup chatGroup);
}
