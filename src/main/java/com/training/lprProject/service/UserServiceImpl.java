package com.training.lprProject.service;

import com.training.lprProject.auth.ApplicationUserDetails;
import com.training.lprProject.dao.UserRepository;
import com.training.lprProject.entity.User;
import com.training.lprProject.error.custom.UserEmailCredentialsMismatchException;
import com.training.lprProject.error.custom.UserNotFoundException;
import com.training.lprProject.projections.AdminView;
import com.training.lprProject.projections.StudentView;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Pageable;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Service;

import java.security.Principal;
import java.util.List;

@Service
public class UserServiceImpl implements UserService {

    private final UserRepository userRepository;
    private final PasswordEncoder passwordEncoder;

    @Autowired
    public UserServiceImpl(UserRepository userRepository, PasswordEncoder passwordEncoder) {
        this.userRepository = userRepository;
        this.passwordEncoder = passwordEncoder;
    }

    @Override
    public UserDetails loadUserByUsername(String username) throws UsernameNotFoundException {
        User user = userRepository.getUserByUsername(username, User.class)
                .orElseThrow(() -> new UsernameNotFoundException("User not found"));
        user.setPassword(passwordEncoder.encode(user.getPassword()));
        return new ApplicationUserDetails(user);
    }

    @Override
    public void addStudent(User student) {
        userRepository.save(student);
    }

    @Override
    public void deleteStudent(Long studentId) {
        userRepository.deleteById(studentId);
    }

    @Override
    public User getUserById(Long userId) {
        return userRepository.getUserByUserId(userId)
                .orElseThrow(() -> new UserNotFoundException("There is no user with such userId: " + userId));
    }

    @Override
    public User getStudentByEmail(Principal principal, String email) {
        return userRepository.getUserByEmail(principal.getName(), email)
                .orElseThrow(() -> new UserEmailCredentialsMismatchException("User with " + email +
                        " doesn't correspond to his/her credentials!"));
    }

    @Override
    public AdminView getUserInAdminView(String username) {
        return userRepository.getUserByUsername(username, AdminView.class)
                .orElseThrow(() -> new UserNotFoundException("User " + username + " doesn't exist"));
    }

    @Override
    public StudentView getUserInStudentView(String username) {
        return userRepository.getUserByUsername(username, StudentView.class)
                .orElseThrow(() -> new UserNotFoundException("User " + username + " doesn't exist"));
    }

    @Override
    public List<User> getAllStudents() {
        return userRepository.findAll();
    }

    @Override
    public List<User> getSomeStudents(Pageable pageable) {
        return userRepository.findAll(pageable).getContent();
    }
}
