package com.training.lprProject.dao;

import com.training.lprProject.entity.User;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;

import java.util.Optional;

public interface UserRepository extends JpaRepository<User, Long> {

    <T> Optional<T> getUserByUsername(String username, Class<T> type);

    Optional<User> getUserByUserId(Long userId);

    @Query("SELECT u FROM User u WHERE u.username = :username AND u.email = :email")
    Optional<User> getUserByEmail(String username, String email);
}
