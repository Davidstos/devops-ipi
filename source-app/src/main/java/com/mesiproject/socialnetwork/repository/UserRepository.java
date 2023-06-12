package com.mesiproject.socialnetwork.repository;

import com.mesiproject.socialnetwork.model.User;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.stereotype.Repository;

import java.util.List;
import java.util.Optional;

@Repository
public interface UserRepository extends JpaRepository<User, Long> {
    @Query("SELECT u FROM User u WHERE u.username=:usernameOrEmail OR u.mail=:usernameOrEmail")
    User findByUsernameOrEmail(String usernameOrEmail);

    /*@Query("SELECT u FROM User u WHERE u.id != :userId")
    List<User> findAllOtherUsers(Long userId);*/

    List<User> findAllByIdNot(Long userId);

    Optional<User> findById(Long id);

    //User findUserByUsernameAndPassword(String username, String password);




}
