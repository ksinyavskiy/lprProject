package com.training.lprProject.controller;

import com.training.lprProject.entity.User;
import com.training.lprProject.projections.AdminView;
import com.training.lprProject.projections.StudentView;
import com.training.lprProject.service.UserService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Pageable;
import org.springframework.data.web.PageableDefault;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

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
    public ResponseEntity<List<User>> getAllStudents() {
        return ResponseEntity.ok(userService.getAllStudents());
    }

    @GetMapping(path = "/{userId}")
    public ResponseEntity<User> getUserById(@PathVariable(name = "userId") String userId) {
        return new ResponseEntity<>(userService.getUserById(Long.valueOf(userId)), HttpStatus.FOUND);
    }

    @GetMapping(path = "/pagination")
    public ResponseEntity<List<User>> getSomeStudents(@PageableDefault(size = 2) Pageable pageable) {
        return ResponseEntity.ok(userService.getSomeStudents(pageable));
    }

    @GetMapping(path = "/getAdminView")
    public ResponseEntity<AdminView> getAdminView(@RequestParam(name = "username") String username) {
        return new ResponseEntity<>(userService.getUserInAdminView(username), HttpStatus.PARTIAL_CONTENT);
    }

    @GetMapping(path = "/getStudentView")
    public ResponseEntity<StudentView> getStudentView(@RequestParam("username") String username) {
        return new ResponseEntity<>(userService.getUserInStudentView(username), HttpStatus.PARTIAL_CONTENT);
    }

    @PostMapping
    public ResponseEntity<String> addStudent(@RequestBody User student) {
        User savedUserEntity = userService.addStudent(student);

        if (savedUserEntity.getUserId() != null) {
            return ResponseEntity.ok("User " + student.getUsername() + " has been successfully added");
        }
        return new ResponseEntity<>("User " + student.getUsername() + " was not added", HttpStatus.BAD_REQUEST);
    }

    @DeleteMapping(path = "{studentId}")
    public ResponseEntity<String> deleteUser(@PathVariable("studentId") Long studentId) {
        userService.deleteStudent(studentId);
        if (userService.isUserExist(studentId)) {
            return new ResponseEntity<>("User with " + studentId + " id still exists", HttpStatus.BAD_REQUEST);
        }
        return ResponseEntity.ok("User with id = " + studentId + " has been successfully deleted!");
    }
}
