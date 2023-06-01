package com.mesiproject.socialnetwork.model;

import javax.persistence.*;
import java.time.LocalDateTime;
import java.util.Date;
import java.util.Objects;

@Entity
@Table(name = "message")
public class Message {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;
    private String text;

    private LocalDateTime messageDate;

    /*@ManyToOne
    @JoinColumn(name="UserSender_id", nullable=false)*/
    @Column(name = "UserSender_id")
    private Long userSender; // mettre plus tard une list de User destinataires ?

    @ManyToOne
    @JoinColumn(name="ChatGroup_id", nullable=false)
    private ChatGroup chatGroup;

    public Message(String text, Long userSender, ChatGroup chatGroup) {
        this.text = text;
        this.messageDate = LocalDateTime.now();
        this.userSender = userSender;
        this.chatGroup = chatGroup;
    }

    public Message() {
    }

    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public String getText() {
        return text;
    }

    public void setText(String text) {
        this.text = text;
    }


    public Long getSender() {
        return userSender;
    }

    public void setSender(Long userSender) {
        this.userSender = userSender;
    }

    public ChatGroup getChatGroup() {
        return chatGroup;
    }

    public void setChatGroup(ChatGroup chatGroup) {
        this.chatGroup = chatGroup;
    }



    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;
        Message message = (Message) o;
        return Objects.equals(id, message.id) && Objects.equals(text, message.text) && Objects.equals(messageDate, message.messageDate) && Objects.equals(userSender, message.userSender) && Objects.equals(chatGroup, message.chatGroup);
    }

    @Override
    public int hashCode() {
        return Objects.hash(id, text, messageDate, userSender, chatGroup);
    }

    @Override
    public String toString() {
        return "Message{" +
                "id=" + id +
                ", text='" + text + '\'' +
                ", messageDate=" + messageDate +
                ", userSender=" + userSender +
                ", chatGroup=" + chatGroup +
                '}';
    }
}
