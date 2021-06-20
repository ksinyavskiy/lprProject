package com.training.lprProject.service;

import com.training.lprProject.dao.UserRepository;
import com.training.lprProject.dto.UserInfo;
import com.training.lprProject.entity.Role;
import com.training.lprProject.entity.User;
import com.training.lprProject.error.custom.UserNotFoundException;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Pageable;
import org.springframework.security.core.GrantedAuthority;
import org.springframework.security.core.authority.SimpleGrantedAuthority;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Set;
import java.util.stream.Collectors;

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
        return buildSpringUserFromJpaUserEntity(user);
    }

    @Override
    public User addStudent(User student) {
        return userRepository.save(student);
    }

    @Override
    public void deleteStudent(Long studentId) {
        userRepository.deleteById(studentId);
    }

    @Override
    public boolean isUserExist(Long userId) {
        return userRepository.existsById(userId);
    }

    @Override
    public User getUserById(Long userId) {
        return userRepository.getUserByUserId(userId)
                .orElseThrow(() -> new UserNotFoundException("There is no user with " + userId + " userId."));
    }

    @Override
    public UserInfo getUserInfo(String email) {
        return userRepository.getUserByEmail(email, UserInfo.class)
                .orElseThrow(() -> new UserNotFoundException("There is no user with " + email + " email."));
    }

    @Override
    public List<User> getAllStudents() {
        return userRepository.findAll();
    }

    @Override
    public List<User> getSomeStudents(Pageable pageable) {
        return userRepository.findAll(pageable).getContent();
    }

    @Override
    public List<User> getUsersByEmailStartsWith(String email) {
        return userRepository.getUsersByEmailStartsWith(email);
    }

    private org.springframework.security.core.userdetails.User buildSpringUserFromJpaUserEntity(User user) {
        String username = String.valueOf(user.getUserId());
        String password = passwordEncoder.encode(user.getPassword());
        Role role = user.getRole();

        Set<GrantedAuthority> grantedAuthorities = role.getPermissions().stream()
                .map(permission -> new SimpleGrantedAuthority(permission.getName()))
                .collect(Collectors.toSet());
        grantedAuthorities.add(new SimpleGrantedAuthority("ROLE_" + role.getName()));

        return new org.springframework.security.core.userdetails.User(
                username,
                password,
                true,
                true,
                true,
                true,
                grantedAuthorities
        );
    }
}
