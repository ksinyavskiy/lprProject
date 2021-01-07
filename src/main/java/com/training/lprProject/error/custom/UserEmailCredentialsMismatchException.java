package com.training.lprProject.error.custom;

public class UserEmailCredentialsMismatchException extends RuntimeException {

    public UserEmailCredentialsMismatchException(String message) {
        super(message);
    }
}
