package com.mesiproject.socialnetwork.model;

import com.fasterxml.jackson.annotation.JsonIgnoreProperties;
import org.springframework.format.annotation.DateTimeFormat;

import javax.persistence.*;
import java.io.Serializable;
import java.time.*;
import java.time.LocalDateTime;
import java.util.*;

@Entity
@Table(name = "user")
public class User{
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;
    private String username; //username et password utilisé pour se connecter
    private String password;
    private String mail; // utilisé si oublié mdp
    private String firstName;
    private String lastName;

    //Pour que le formulaire HTML puisse insérer une date String
    @DateTimeFormat(pattern = "dd/MM/yyyy")
    private LocalDate birthDate;

    private String zipCode;
    private String address;
    private String bio; //100 caractères de descriptions de profil
    private String relationship; //single, in couple, prefer not to say
    private LocalDateTime loginDate;
    private String status; // online ou offline

    @ManyToMany//(fetch = FetchType.EAGER, mappedBy = "chatGroups", cascade = CascadeType.ALL)
    @JoinTable(
            name = "ChatGroupUser",
            joinColumns = @JoinColumn(name = "UserId"),
            inverseJoinColumns = @JoinColumn(name = "ChatGroupId"))
    private List<ChatGroup> chatGroups; //2 ou plus
    @OneToOne
    private Role role;

    /*@OneToMany(mappedBy="chatGroup")
    private List<Message> messages = new ArrayList<>();*/


    /*@JsonIgnoreProperties("userMainId")
    @OneToMany(mappedBy = "userMainId")
    private List<User> friends = new ArrayList<>();*/

    @ManyToMany
    @JoinTable(name = "Friends",
            joinColumns = @JoinColumn(name = "userMain"),
            inverseJoinColumns = @JoinColumn(name = "userFriend")
    )
    private List<User> friends = new ArrayList<>();


    public User() {
    }


    public User(Long id, String username, String password, String mail, String firstName, String lastName, LocalDate birthDate, String address, String bio, String relationship,String zipCode,String status,Role role,List<ChatGroup> chatGroups,List<User> friends) {
        this.id = id;
        this.username = username;
        this.password = password;
        this.mail = mail;
        this.firstName = firstName;
        this.lastName = lastName;
        this.status = status;
        this.birthDate = birthDate;
        this.address = address;
        this.bio = bio;
        this.relationship = relationship;
        this.chatGroups = chatGroups;
        this.zipCode = zipCode;
        this.friends = friends;
        this.loginDate = LocalDateTime.now();
        this.role = role;
    }

    public User(User user){
        this.id = user.getId();
        this.username = user.getUsername();
        this.password = user.getPassword();
        this.mail = user.getMail();
        this.firstName = user.getFirstName();
        this.lastName = user.getLastName();
        this.status = user.getStatus();
        this.birthDate = user.getBirthDate();
        this.address = user.getAddress();
        this.bio = user.getBio();
        this.relationship = user.getRelationship();
        this.chatGroups = user.getChatGroups();
        this.zipCode = user.getZipCode();
        this.loginDate = user.getLoginDate();
        this.role = user.getRole();
        this.friends = user.getFriends();
    }


    public List<ChatGroup> getChatGroups() {
        return chatGroups;
    }

    public void setChatGroups(List<ChatGroup> chatGroups) {
        this.chatGroups = chatGroups;
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

    public String getPassword() {
        return password;
    }

    public void setPassword(String password) {
        this.password = password;
    }

    public String getMail() {
        return mail;
    }

    public void setMail(String mail) {
        this.mail = mail;
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

    public LocalDate getBirthDate() {
        return birthDate;
    }

    public void setBirthDate(LocalDate birthDate) {
        this.birthDate = birthDate;
    }


    public String getAddress() {
        return address;
    }

    public void setAddress(String address) {
        this.address = address;
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


    public String getZipCode() {
        return zipCode;
    }

    public void setZipCode(String zipCode) {
        this.zipCode = zipCode;
    }

    public Role getRole() {
        return role;
    }
    public List<User> getFriends() {
        return friends;
    }

    public void setFriends(List<User> friends) {
        this.friends = friends;
    }


    public LocalDateTime getLoginDate() {
        return loginDate;
    }

    public void setLoginDate(LocalDateTime loginDate) {
        this.loginDate = loginDate;
    }

    public void setRole(Role role) {
        this.role = role;
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;
        User user = (User) o;
        return Objects.equals(id, user.id) && Objects.equals(username, user.username) && Objects.equals(password, user.password) && Objects.equals(mail, user.mail) && Objects.equals(firstName, user.firstName) && Objects.equals(lastName, user.lastName) && Objects.equals(birthDate, user.birthDate) && Objects.equals(zipCode, user.zipCode) && Objects.equals(address, user.address) && Objects.equals(bio, user.bio) && Objects.equals(relationship, user.relationship) && Objects.equals(loginDate, user.loginDate) && Objects.equals(status, user.status) && Objects.equals(chatGroups, user.chatGroups) && Objects.equals(role, user.role) && Objects.equals(friends, user.friends);
    }

    @Override
    public int hashCode() {
        return Objects.hash(id, username, password, mail, firstName, lastName, birthDate, zipCode, address, bio, relationship, loginDate, status, chatGroups, role, friends);
    }

    @Override
    public String toString() {
        final StringBuilder sb = new StringBuilder("User{");
        sb.append("id=").append(id);
        sb.append(", username='").append(username).append('\'');
        sb.append(", password='").append(password).append('\'');
        sb.append(", mail='").append(mail).append('\'');
        sb.append(", firstName='").append(firstName).append('\'');
        sb.append(", lastName='").append(lastName).append('\'');
        sb.append(", birthDate=").append(birthDate);
        sb.append(", zipCode='").append(zipCode).append('\'');
        sb.append(", address='").append(address).append('\'');
        sb.append(", bio='").append(bio).append('\'');
        sb.append(", relationship='").append(relationship).append('\'');
        sb.append(", loginDate=").append(loginDate);
        sb.append(", status='").append(status).append('\'');
        sb.append(", chatGroups=").append(chatGroups);
        sb.append(", role=").append(role);
        sb.append(", friends=").append(friends);
        sb.append('}');
        return sb.toString();
    }
}
