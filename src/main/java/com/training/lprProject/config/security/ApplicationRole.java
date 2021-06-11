package com.training.lprProject.config.security;

public enum ApplicationRole {
    ADMIN("ADMIN");

    String roleName;

    ApplicationRole(String roleName) {
        this.roleName = roleName;
    }
}
