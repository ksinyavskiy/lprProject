package com.training.lprProject.service;

import com.training.lprProject.entity.User;
import com.training.lprProject.projections.AdminView;
import com.training.lprProject.projections.StudentView;
import com.training.lprProject.projections.UserView;
import org.springframework.data.domain.Pageable;
import org.springframework.security.core.userdetails.UserDetailsService;

import java.util.List;

public interface UserService extends UserDetailsService {

    User addStudent(User student);

    void deleteStudent(Long studentId);

    boolean isUserExist(Long userId);

    User getUserById(Long studentId);

    AdminView getUserInAdminView(String username);

    StudentView getUserInStudentView(String username);

    UserView getProjectionView(String username, String role);

    List<User> getAllStudents();

    List<User> getSomeStudents(Pageable pageable);
}
