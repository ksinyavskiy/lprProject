package com.training.lprProject.entity;

import io.swagger.annotations.ApiModel;
import io.swagger.annotations.ApiModelProperty;

import javax.persistence.Entity;
import javax.persistence.FetchType;
import javax.persistence.JoinColumn;
import javax.persistence.ManyToOne;
import javax.persistence.OneToOne;
import javax.persistence.Table;
import javax.persistence.Id;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Column;
import java.io.Serializable;
import java.util.Objects;

@Entity
@Table(name = "USER")
@ApiModel(value = "User entity", description = "This is the user entity to represent the user data from the database.")
public class User implements Serializable {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "USER_ID", unique = true, nullable = false)
    private Long userId;
    @OneToOne
    @JoinColumn(name = "ROLE_ID")
    @ApiModelProperty(value = "Describes the role for a particular user", allowableValues = "ADMIN, STUDENT")
    private Role role;
    @Column(name = "FIRST_NAME", length = 20, nullable = false)
    private String firstName;
    @Column(name = "LAST_NAME", length = 20, nullable = false)
    private String lastName;
    @Column(name = "LOGIN", length = 20, unique = true, nullable = false)
    private String username;
    @Column(name = "PASSWORD", length = 20, unique = true, nullable = false)
    private String password;
    @Column(name = "EMAIL", length = 30, unique = true, nullable = false)
    private String email;
    @ManyToOne(fetch = FetchType.LAZY)
    @JoinColumn(name = "SCHOOL_ID")
    private School school;

    public User() {

    }

    public Long getUserId() {
        return userId;
    }

    public String getFirstName() {
        return firstName;
    }

    public void setRole(Role role) {
        this.role = role;
    }

    public Role getRole() {
        return role;
    }

    public void setFirstName(String firstName) {
        this.firstName = firstName;
    }

    public String getLastName() {
        return lastName;
    }

    public void setLastName(String lastName) {
        this.lastName = lastName;
    }

    public String getUsername() {
        return username;
    }

    public void setUsername(String username) {
        this.username = username;
    }

    public String getPassword() {
        return password;
    }

    public void setPassword(String password) {
        this.password = password;
    }

    public String getEmail() {
        return email;
    }

    public void setEmail(String email) {
        this.email = email;
    }

    public School getSchool() {
        return school;
    }

    public void setSchool(School school) {
        this.school = school;
    }

    @Override
    public boolean equals(Object object) {
        if (object == null) {
            return false;
        }
        if (this == object) {
            return true;
        }
        if (this.getClass() != object.getClass()) {
            return false;
        }
        User user = (User) object;

        return Objects.equals(userId, user.getUserId()) &&
                Objects.equals(firstName, user.getFirstName()) &&
                Objects.equals(lastName, user.getLastName()) &&
                Objects.equals(username, user.getUsername()) &&
                Objects.equals(password, user.getPassword()) &&
                Objects.equals(email, user.getEmail()) &&
                Objects.equals(school.getSchoolId(), user.getSchool().getSchoolId());
    }

    @Override
    public int hashCode() {
        return Objects.hash(userId, firstName, lastName, username, password, email, school.getSchoolId());
    }

    @Override
    public String toString() {
        return String.format("User: userId = %d, login = %s", userId, username);
    }
}
