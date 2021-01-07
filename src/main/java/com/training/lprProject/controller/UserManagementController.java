package com.training.lprProject.controller;

import com.training.lprProject.entity.User;
import com.training.lprProject.projections.AdminView;
import com.training.lprProject.projections.StudentView;
import com.training.lprProject.service.UserService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.PageRequest;
import org.springframework.security.access.prepost.PreAuthorize;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

import java.security.Principal;
import java.util.List;

@RestController
@RequestMapping(path = "management/api/v1/students")
public class UserManagementController {

    private final UserService userService;

    @Autowired
    public UserManagementController(UserService userService) {
        this.userService = userService;
    }

    @GetMapping
    @PreAuthorize("hasRole('ROLE_ADMIN')")
    public List<User> getAllStudents() {
        return userService.getAllStudents();
    }

    @GetMapping(path = "{userId}")
    @PreAuthorize("hasRole('ROLE_ADMIN')")
    public User getUserById(@PathVariable("userId") Long userId) {
        return userService.getUserById(userId);
    }

    @GetMapping(path = "/pagination")
    @PreAuthorize("hasRole('ROLE_ADMIN')")
    public List<User> getSomeStudents(@RequestParam(name = "pageNumber", defaultValue = "0") Integer pageNumber,
                                      @RequestParam(name = "amount", defaultValue = "2") Integer amount) {
        return userService.getSomeStudents(PageRequest.of(pageNumber, amount));
    }

    @GetMapping(path = "/getPersonalInfo")
    @PreAuthorize("hasAuthority('student:read')")
    public User getPersonalInfo(Principal principal, @RequestParam("email") String email) {
        return userService.getStudentByEmail(principal, email);
    }

    @GetMapping(path = "/getUserInAdminView")
    @PreAuthorize("hasAuthority('admin:read')")
    public AdminView getUserInAdminView(@RequestParam("username") String username) {
        return userService.getUserInAdminView(username);
    }

    @GetMapping(path = "/getUserInStudentView")
    @PreAuthorize("hasAuthority('admin:read')")
    public StudentView getUserInStudentView(@RequestParam("username") String username) {
        return userService.getUserInStudentView(username);
    }

    @PostMapping
    @PreAuthorize("hasAuthority('admin:write')")
    public void addStudent(@RequestBody User student) {
        userService.addStudent(student);
    }

    @DeleteMapping(path = "{studentId}")
    @PreAuthorize("hasAuthority('admin:write')")
    public void deleteUser(@PathVariable("studentId") Long studentId) {
        userService.deleteStudent(studentId);
    }
}
