package com.training.lprProject.dao;

import com.training.lprProject.entity.User;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.rest.core.annotation.RepositoryRestResource;

import java.util.List;
import java.util.Optional;

@RepositoryRestResource(collectionResourceRel = "users", path = "users")
public interface UserRepository extends JpaRepository<User, Long> {

    <T> Optional<T> getUserByUsername(String username, Class<T> type);

    Optional<User> getUserByUserId(Long userId);

    <T> Optional<T> getUserByEmail(String email, Class<T> type);

    List<User> getUsersByEmailStartsWith(String email);
}
