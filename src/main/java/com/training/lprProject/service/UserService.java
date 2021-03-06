package com.training.lprProject.service;

import com.training.lprProject.dto.UserInfo;
import com.training.lprProject.entity.User;
import org.springframework.data.domain.Pageable;
import org.springframework.security.core.userdetails.UserDetailsService;

import java.util.List;

public interface UserService extends UserDetailsService {

    User addStudent(User student);

    void deleteStudent(Long studentId);

    boolean isUserExist(Long userId);

    User getUserById(Long studentId);

    UserInfo getUserInfo(String email);

    List<User> getAllStudents();

    List<User> getSomeStudents(Pageable pageable);

    List<User> getUsersByEmailStartsWith(String email);
}
