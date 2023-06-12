package com.mesiproject.socialnetwork.repository;

import com.mesiproject.socialnetwork.model.ChatGroup;
import com.mesiproject.socialnetwork.model.User;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.Optional;

@Repository
public interface ChatGroupRepository  extends JpaRepository<ChatGroup, Long> {

    Optional<ChatGroup> findById(Long id);
}
