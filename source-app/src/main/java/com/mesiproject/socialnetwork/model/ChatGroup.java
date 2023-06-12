package com.mesiproject.socialnetwork.model;

import javax.persistence.*;
import java.util.List;
import java.util.Objects;
import java.util.Set;

@Entity
//@Table(name = "chatgroup")
public class ChatGroup {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;
    private String name;
    //@ManyToMany(fetch = FetchType.EAGER, mappedBy = "userId", cascade = CascadeType.ALL)
    @ManyToMany(mappedBy = "chatGroups")//(fetch = FetchType.EAGER, mappedBy = "userId", cascade = CascadeType.ALL)
    private List<User> users; //2 ou plus
    @OneToMany(mappedBy="chatGroup")
    private List<Message> messages;

    public ChatGroup(Long id, List<User> users, List<Message> messages,String name) {
        this.id = id;
        this.users = users;
        this.messages = messages;
        this.name = name;
    }

    public ChatGroup() {
    }

    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }



    public List<User> getUsers() {
        return users;
    }

    public void setUsers(List<User> users) {
        this.users = users;
    }

    public List<Message> getMessages() {
        return messages;
    }

    public void setMessages(List<Message> messages) {
        this.messages = messages;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }


    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;
        ChatGroup chatGroup = (ChatGroup) o;
        return Objects.equals(id, chatGroup.id) && Objects.equals(name, chatGroup.name) && Objects.equals(users, chatGroup.users) && Objects.equals(messages, chatGroup.messages);
    }

    @Override
    public int hashCode() {
        return Objects.hash(id, name, users, messages);
    }

    @Override
    public String toString() {
        return "ChatGroup{" +
                "id=" + id +
                ", name='" + name + '\'' +
                ", users=" + users +
                ", messages=" + messages +
                '}';
    }
}
