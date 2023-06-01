package com.mesiproject.socialnetwork.model;

import java.io.Serializable;
import java.util.Objects;

public class FriendsId implements Serializable {
    private Long userMain;
    private Long userFriend;

    public FriendsId() {
    }
    public FriendsId(Long userMain, Long userFriend) {
        this.userMain = userMain;
        this.userFriend = userFriend;
    }

    public Long getUserMain() {
        return userMain;
    }

    public void setUserMain(Long userMainId) {
        this.userMain = userMainId;
    }

    public Long getUserFriend() {
        return userFriend;
    }

    public void setUserFriend(Long userFriendId) {
        this.userFriend = userFriendId;
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (!(o instanceof FriendsId)) return false;
        FriendsId friendsId = (FriendsId) o;
        return Objects.equals(userMain, friendsId.userMain) && Objects.equals(userFriend, friendsId.userFriend);
    }

    @Override
    public int hashCode() {
        return Objects.hash(userMain, userFriend);
    }
}
