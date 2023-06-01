package com.mesiproject.socialnetwork.security;

import com.mesiproject.socialnetwork.model.ChatGroup;
import com.mesiproject.socialnetwork.model.Friends;
import com.mesiproject.socialnetwork.model.Role;
import com.mesiproject.socialnetwork.model.User;
import org.springframework.context.annotation.Bean;
import org.springframework.security.core.GrantedAuthority;
import org.springframework.security.core.authority.AuthorityUtils;
import org.springframework.security.core.authority.SimpleGrantedAuthority;
import org.springframework.security.core.userdetails.UserDetails;

import java.sql.Date;
import java.time.LocalDate;
import java.time.LocalDateTime;
import java.util.*;

public class CustomUserDetails extends User implements UserDetails {
    //String ROLE_PREFIX = "ROLE_";

    private static final long serialVersionUID = 1L;
    private User user;

    public CustomUserDetails(User user){
        super(user);
        //this.id = id;
        this.user = user;
    }

    @Override
    public String getUsername() {
        return this.user.getUsername();
    }

    @Override
    public boolean isAccountNonExpired() {
        return true;
    }

    @Override
    public boolean isAccountNonLocked() {
        return true;
    }

    @Override
    public boolean isCredentialsNonExpired() {
        return true;
    }

    @Override
    public boolean isEnabled() {
        return true;
    }

    @Override
    public Collection<? extends GrantedAuthority> getAuthorities() {
        /*List<GrantedAuthority> list = new ArrayList<GrantedAuthority>();

        list.add(new SimpleGrantedAuthority(ROLE_PREFIX + role));
        return list;*/
        return AuthorityUtils.commaSeparatedStringToAuthorityList("ROLE_ADMIN");
    }

    @Override
    public String getPassword() {
        return this.user.getPassword();
    }

    public Long getId() {
        return this.user.getId();
    }

    public String getMail() {
        return this.user.getMail();
    }

    public String getFirstName() {
        return this.user.getFirstName();
    }

    public String getLastName() {
        return this.user.getLastName();
    }

    public String getStatusName() {
        return this.user.getStatus();
    }

    public LocalDate getBirthDate() {
        return this.user.getBirthDate();
    }

    public String getAddress() {
        return this.user.getAddress();
    }

    public String getBio() {
        return this.user.getBio();
    }

    public String getRelationship() {
        return this.user.getRelationship();
    }

    public String getZipCode() {
        return this.user.getZipCode();
    }

    public LocalDateTime getLoginDate() {
        return this.user.getLoginDate();
    }

    public List<ChatGroup> getChatGroups() {
        return this.user.getChatGroups();
    }

    public List<User> getFriends() {
        return this.user.getFriends();
    }

    public Role getRole() {
        return this.user.getRole();
    }
}
