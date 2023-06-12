package com.mesiproject.socialnetwork.security;

import com.mesiproject.socialnetwork.model.Role;
import com.mesiproject.socialnetwork.model.User;
import com.mesiproject.socialnetwork.repository.UserRepository;
import com.mesiproject.socialnetwork.service.UserService;
import com.mesiproject.socialnetwork.service.impl.UserServiceImpl;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.core.GrantedAuthority;
import org.springframework.security.core.authority.SimpleGrantedAuthority;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.ArrayList;
import java.util.HashSet;
import java.util.List;
import java.util.Set;

@Service("customUserDetailsService")
public class CustomUserDetailsService implements UserDetailsService {

    private UserServiceImpl userService;

    @Autowired
    public CustomUserDetailsService(UserServiceImpl userService) {
        this.userService = userService;
    }

    @Override
    public UserDetails loadUserByUsername(String username) throws UsernameNotFoundException {
        User user = userService.findByUsernameOrEmail(username);
        if (null == user || ! user.getUsername().equals(username)) {
            throw new UsernameNotFoundException("No user present with username: " + username);
        } else {

            return new CustomUserDetails(user);
        }
    }

    //@Transactional(readOnly=true)
    //@Override
    /*public UserDetails loadUserByUsername(String username) throws UsernameNotFoundException {
        if(username.trim().isEmpty()){
            throw new UsernameNotFoundException("le nom d'utilisateur est vide");
        }
        User user = userService.findByUsernameOrEmail(username);
        List<GrantedAuthority> authorities = buildUserAuthority(user.getUserRole());
        if(user==null){
            throw new UsernameNotFoundException("L'utilisateur "+ username + " n'existe pas");
        }
//        return new org.springframework.security.core.userdetails.User(user.getUsername(),user.getPassword(),getGrantedAuthorities(user));
        return user;
    }*/

    /*public CustomUserDetails loadUserByUsername(final String username)
            throws UsernameNotFoundException {
//CUSTOM USER HERE vvv
        User user = userService.findByUsernameOrEmail(username);
        List<GrantedAuthority> authorities = buildUserAuthority((Set<Role>) user.getRole());
//if you're implementing UserDetails you wouldn't need to call this method and instead return the User as it is
        //return buildUserForAuthentication(user, authorities);
        return new org.springframework.security.core.userdetails.User(user.getUsername(),user.getPassword(),getGrantedAuthorities(user));
        //return user;
    }

    private List<GrantedAuthority> buildUserAuthority(Set<Role> userRoles) {

        Set<GrantedAuthority> setAuths = new HashSet<GrantedAuthority>();

        // add user's authorities
        for (Role userRole : userRoles) {
            setAuths.add(new SimpleGrantedAuthority(userRole.getName()));
        }

        List<GrantedAuthority> Result = new ArrayList<GrantedAuthority>(setAuths);

        return Result;
    }*/



    /*@Override
    public CustomUserDetails loadUserByUsername(String username) throws UsernameNotFoundException {
        if(username.trim().isEmpty()){
            throw new UsernameNotFoundException("le nom d'utilisateur est vide");
        }
        User user = userService.findByUsernameOrEmail(username);

        if(user==null){
            throw new UsernameNotFoundException("L'utilisateur "+ username + " n'existe pas");
        }
        CustomUserDetails customUserDetails = new CustomUserDetails(user.getUsername(),user.getPassword(),user.getMail(),user.getFirstName(),user.getLastName(),user.getStatusName(),user.getBirthDate(),user.getAddress(),user.getBio(),user.getRelationship(),user.getChatGroups(),user.getZipCode(),user.getRole());
//        CustomUserDetails customUserDetails = new CustomUserDetails(user.getUsername(),user.getPassword(),user.getMail(),user.getFirstName(),user.getLastName(),user.getStatusName(),user.getBirthDate(),user.getAddress(),user.getBio(),user.getRelationship(),user.getChatGroups(),user.getZipCode(),user.getLoginDate(),user.getRole())
        return customUserDetails;
       //return new org.springframework.security.core.userdetails.User(user.getUsername(),user.getPassword(),getGrantedAuthorities(user));
    }

    private List<GrantedAuthority> getGrantedAuthorities(User user) {
        List<GrantedAuthority> authorities = new ArrayList<GrantedAuthority>();
        Role role = user.getRole();
        authorities.add(new SimpleGrantedAuthority(role.getName()));
        return authorities;
    }*/
}
