package com.mesiproject.socialnetwork.dto;

import com.mesiproject.socialnetwork.model.ChatGroup;
import com.mesiproject.socialnetwork.model.Friends;

import java.util.List;
import java.util.Objects;

public class UserDto {
    private Long id;
    private String username; //username et password utilisé pour se connecter
    private String firstName;
    private String lastName;
    private String status; // online ou offline
    private String bio; //100 caractères de descriptions de profil
    private String relationship; //single, in couple, prefer not to say
    private List<ChatGroup> chatGroups; //2 ou plus
    private List<Friends> friends;


    public UserDto() {
    }

    public UserDto(Long id, String username, String firstName, String lastName, String status, String bio, String relationship, List<ChatGroup> chatGroups, List<Friends> friends) {
        this.id = id;
        this.username = username;
        this.firstName = firstName;
        this.lastName = lastName;
        this.status = status;
        this.bio = bio;
        this.relationship = relationship;
        this.chatGroups = chatGroups;
        this.friends = friends;
    }


    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public String getUsername() {
        return username;
    }

    public void setUsername(String username) {
        this.username = username;
    }

    public String getFirstName() {
        return firstName;
    }

    public void setFirstName(String firstName) {
        this.firstName = firstName;
    }

    public String getLastName() {
        return lastName;
    }

    public void setLastName(String lastName) {
        this.lastName = lastName;
    }

    public String getStatus() {
        return status;
    }

    public void setStatus(String status) {
        this.status = status;
    }

    public String getBio() {
        return bio;
    }

    public void setBio(String bio) {
        this.bio = bio;
    }

    public String getRelationship() {
        return relationship;
    }

    public void setRelationship(String relationship) {
        this.relationship = relationship;
    }

    public List<ChatGroup> getChatGroups() {
        return chatGroups;
    }

    public void setChatGroups(List<ChatGroup> chatGroups) {
        this.chatGroups = chatGroups;
    }


    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;
        UserDto userDto = (UserDto) o;
        return Objects.equals(id, userDto.id) && Objects.equals(username, userDto.username) && Objects.equals(firstName, userDto.firstName) && Objects.equals(lastName, userDto.lastName) && Objects.equals(status, userDto.status) && Objects.equals(bio, userDto.bio) && Objects.equals(relationship, userDto.relationship) && Objects.equals(chatGroups, userDto.chatGroups);
    }

    @Override
    public int hashCode() {
        return Objects.hash(id, username, firstName, lastName, status, bio, relationship, chatGroups);
    }

    @Override
    public String toString() {
        return "UserDto{" +
                "id=" + id +
                ", username='" + username + '\'' +
                ", firstName='" + firstName + '\'' +
                ", lastName='" + lastName + '\'' +
                ", status='" + status + '\'' +
                ", bio='" + bio + '\'' +
                ", relationship='" + relationship + '\'' +
                ", chatGroups=" + chatGroups +
                '}';
    }
}
