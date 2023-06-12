package com.mesiproject.socialnetwork.model;

import javax.persistence.Column;
import java.io.Serializable;
import java.util.Objects;

public class ChatGroupUserId implements Serializable {
    @Column(name = "userId")
    private Long userId;
    @Column(name = "chatGroupId")
    private Long chatGroupId;

    public ChatGroupUserId(Long userId, Long chatGroupId) {
        this.userId = userId;
        this.chatGroupId = chatGroupId;
    }

    public ChatGroupUserId() {
    }

    public Long getUserId() {
        return userId;
    }

    public void setUserId(Long userId) {
        this.userId = userId;
    }

    public Long getChatGroupId() {
        return chatGroupId;
    }

    public void setChatGroupId(Long chatGroupId) {
        this.chatGroupId = chatGroupId;
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (!(o instanceof ChatGroupUserId)) return false;
        ChatGroupUserId that = (ChatGroupUserId) o;
        return Objects.equals(userId, that.userId) && Objects.equals(chatGroupId, that.chatGroupId);
    }

    @Override
    public int hashCode() {
        return Objects.hash(userId, chatGroupId);
    }
}
