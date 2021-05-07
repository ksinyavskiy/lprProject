package com.training.lprProject.dao;

import com.training.lprProject.entity.User;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;

import java.util.Optional;

public interface UserRepository extends JpaRepository<User, Long> {

    <T> Optional<T> getUserByUsername(String username, Class<T> type);

    Optional<User> getUserByUserId(Long userId);

    @Query("SELECT u FROM User u WHERE u.role.name = :role AND u.username = :username")
    <T> T getProjectionByRoleName(String username, String role, Class<T> projectionEntity);

    User getUserByEmail(String email);
}
