package com.letsRoll.letsRoll.User.repository;

import com.letsRoll.letsRoll.User.entity.User;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.Optional;

@Repository
public interface UserRepository extends JpaRepository<User, Long> {
    Optional<User> findUserByUserName(String userName);

    Optional<User> findUserById(Long userId);

}
