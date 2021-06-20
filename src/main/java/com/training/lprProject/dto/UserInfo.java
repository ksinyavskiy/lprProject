package com.training.lprProject.dto;

import com.training.lprProject.entity.Role;

import java.util.Objects;

public class UserInfo {
    private String firstName;
    private String email;
    private Role role;

    public UserInfo(String firstName, String email, Role role) {
        this.firstName = firstName;
        this.email = email;
        this.role = role;
    }

    public String getFirstName() {
        return firstName;
    }

    public void setFirstName(String firstName) {
        this.firstName = firstName;
    }

    public String getEmail() {
        return email;
    }

    public void setEmail(String email) {
        this.email = email;
    }

    public Role getRole() {
        return role;
    }

    public void setRole(Role role) {
        this.role = role;
    }

    @Override
    public boolean equals(Object obj) {
        if (obj == null) {
            return false;
        }

        if (this == obj) {
            return true;
        }

        if (this.getClass() != obj.getClass()) {
            return false;
        }

        UserInfo userInfo = (UserInfo) obj;

        return Objects.equals(firstName, userInfo.getFirstName()) &&
                Objects.equals(email, userInfo.getEmail()) &&
                Objects.equals(role.getRoleId(), userInfo.getRole().getRoleId());
    }

    @Override
    public int hashCode() {
        return Objects.hash(firstName, email, role.getRoleId());
    }
}
