package com.training.lprProject.service;

import com.training.lprProject.entity.User;
import com.training.lprProject.projections.AdminView;
import com.training.lprProject.projections.StudentView;
import org.springframework.data.domain.Pageable;
import org.springframework.security.core.userdetails.UserDetailsService;

import java.security.Principal;
import java.util.List;

public interface UserService extends UserDetailsService {

    void addStudent(User student);

    void deleteStudent(Long studentId);

    User getUserById(Long studentId);

    User getStudentByEmail(Principal principal, String email);

    AdminView getUserInAdminView(String username);

    StudentView getUserInStudentView(String username);

    List<User> getAllStudents();

    List<User> getSomeStudents(Pageable pageable);
}
