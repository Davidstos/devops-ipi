package com.mesiproject.socialnetwork.model;

import javax.persistence.*;
import java.util.Objects;

@Entity
@Table(name = "friends")
@IdClass(FriendsId.class)
public class Friends {
    @Id
    private Long userMain;
    @Id
    private Long userFriend;

    private String name;
//    HashMap<User, List<User>> friendsByUserMap = new HashMap<User, List<User> >();


    public Friends(Long userMain, Long userFriend) {
        this.userMain = userMain;
        this.userFriend = userFriend;
        this.name = null;
    }

    public Friends(Long userMain, Long userFriend, String name) {
        this.userMain = userMain;
        this.userFriend = userFriend;
        this.name = name;
    }

    public Friends() {
    }

    public Long getUserMain() {
        return userMain;
    }

    public void setUserMain(Long userMain) {
        this.userMain = userMain;
    }

    public Long getUserFriend() {
        return userFriend;
    }

    public void setUserFriend(Long userFriend) {
        this.userFriend = userFriend;
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
        Friends friends = (Friends) o;
        return Objects.equals(userMain, friends.userMain) && Objects.equals(userFriend, friends.userFriend) && Objects.equals(name, friends.name);
    }

    @Override
    public int hashCode() {
        return Objects.hash(userMain, userFriend, name);
    }

    @Override
    public String toString() {
        return "Friends{" +
                ", userMain=" + userMain +
                ", userFriend=" + userFriend +
                ", name='" + name + '\'' +
                '}';
    }
}
