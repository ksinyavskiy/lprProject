package com.training.lprProject.controller;

import com.training.lprProject.dto.UserDto;
import com.training.lprProject.entity.User;
import com.training.lprProject.projections.AdminView;
import com.training.lprProject.projections.StudentView;
import com.training.lprProject.projections.UserView;
import com.training.lprProject.service.UserService;
import io.swagger.annotations.Api;
import io.swagger.annotations.ApiOperation;
import io.swagger.annotations.ApiParam;
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

import javax.websocket.server.PathParam;
import java.util.List;

@RestController
@RequestMapping(path = "management/api/v1/students")
@Api(value = "User Management API Controller", produces = "application/json")
public class UserManagementController {

    private final UserService userService;

    @Autowired
    public UserManagementController(UserService userService) {
        this.userService = userService;
    }

    @GetMapping(produces = "application/json; charset=UTF-8")
    @ApiOperation(value = "Get all users from the database.",
            notes = "The query result includes both students and admins. This is a feature for admins.",
            response = User.class,
            responseContainer = "List")
    public ResponseEntity<List<User>> getAllStudents() {
        return ResponseEntity.ok(userService.getAllStudents());
    }

    @GetMapping(path = "/{userId}", produces = "application/json; charset=UTF-8")
    @ApiOperation(value = "Get specified user.",
            notes = "Get specified user by his ID value.",
            response = User.class)
    public ResponseEntity<User> getUserById(
            @ApiParam(value = "The ID value by which the user will be searched in the database.", required = true)
            @PathVariable(name = "userId") String userId) {
        return new ResponseEntity<>(userService.getUserById(Long.valueOf(userId)), HttpStatus.FOUND);
    }

    @GetMapping(path = "/getUserInfo", produces = "application/json; charset=UTF-8")
    @ApiOperation(value = "Get user info.",
            notes = "Get user info using his email. Username, email and roleName are come back.",
            response = UserDto.class)
    public ResponseEntity<UserDto> getUserInfo(@PathParam("email") String email) {
        return new ResponseEntity<>(userService.getUserInfo(email), HttpStatus.PARTIAL_CONTENT);
    }

    @GetMapping(path = "/pagination", produces = "application/json; charset=UTF-8")
    public ResponseEntity<List<User>> getSomeStudents(@PageableDefault(size = 2) Pageable pageable) {
        return ResponseEntity.ok(userService.getSomeStudents(pageable));
    }

    @GetMapping(path = "/getAdminView", produces = "application/json; charset=UTF-8")
    public ResponseEntity<AdminView> getAdminView(@RequestParam(name = "username") String username) {
        return new ResponseEntity<>(userService.getUserInAdminView(username), HttpStatus.PARTIAL_CONTENT);
    }

    @GetMapping(path = "/getStudentView", produces = "application/json; charset=UTF-8")
    public ResponseEntity<StudentView> getStudentView(@RequestParam("username") String username) {
        return new ResponseEntity<>(userService.getUserInStudentView(username), HttpStatus.PARTIAL_CONTENT);
    }

    @GetMapping(path = "/getProjectionView", produces = "application/json; charset=UTF-8")
    @ApiOperation(value = "Get user-specific projection.",
            notes = "Get a projection for a specific user using the username and role.",
            response = UserView.class)
    public ResponseEntity<UserView> getProjectionView(
            @ApiParam(value = "Helps to choose what projection should be used to display. Ignore case.",
            allowableValues = "admin, student.")
            @RequestParam("role") String role,
            @ApiParam(value = "User's login")
            @RequestParam("username") String username) {
        return new ResponseEntity<>(userService.getProjectionView(username, role.toUpperCase()),
                HttpStatus.PARTIAL_CONTENT);
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
