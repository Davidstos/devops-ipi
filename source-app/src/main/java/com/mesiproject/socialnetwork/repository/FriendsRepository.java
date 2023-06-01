package com.mesiproject.socialnetwork.repository;

import com.mesiproject.socialnetwork.model.Friends;
import com.mesiproject.socialnetwork.model.FriendsId;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.repository.CrudRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface FriendsRepository extends CrudRepository<Friends, FriendsId> {
}
