package com.mesiproject.socialnetwork.service;

import com.mesiproject.socialnetwork.model.*;
import com.mesiproject.socialnetwork.repository.FriendsRepository;
import com.mesiproject.socialnetwork.repository.UserRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import javax.persistence.EntityNotFoundException;
import java.util.List;
import java.util.Optional;

@Service
public class FriendsService {
    @Autowired
    FriendsRepository friendsRepository;

    @Autowired
    UserRepository userRepository;

    public Friends findById(Long userMainId, Long userFriendId) {
        Optional<Friends> friend = this.friendsRepository.findById(new FriendsId(userMainId, userFriendId));
        if(!friend.isPresent()){
            throw new EntityNotFoundException("Impossible de trouver l'ami");
        }
        return friend.get();
    }

    public Friends addFriend(Friends friend) {
        return friendsRepository.save(friend);
    }

    public Friends updateFriend(Friends friend) {
        return friendsRepository.save(friend);
    }

    public void deleteFriend(Long userMainId, Long userFriendId) {
        friendsRepository.deleteById(new FriendsId(userMainId, userFriendId));
    }

    public List<User> findAllUsers(){
        return userRepository.findAll();
    }

}
