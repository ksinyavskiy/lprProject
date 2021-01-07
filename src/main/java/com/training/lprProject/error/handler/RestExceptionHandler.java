package com.training.lprProject.error.handler;

import com.training.lprProject.error.custom.UserEmailCredentialsMismatchException;
import com.training.lprProject.error.custom.UserNotFoundException;
import org.springframework.http.HttpHeaders;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.security.access.AccessDeniedException;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.web.bind.annotation.ControllerAdvice;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.context.request.WebRequest;
import org.springframework.web.servlet.mvc.method.annotation.ResponseEntityExceptionHandler;

import java.util.Objects;

@ControllerAdvice
public class RestExceptionHandler extends ResponseEntityExceptionHandler {

    @ExceptionHandler(value = {UserNotFoundException.class, UsernameNotFoundException.class})
    protected ResponseEntity<Object> handleNotFoundError(RuntimeException ex, WebRequest webRequest) {
        return handleExceptionInternal(ex, ex.getMessage(), new HttpHeaders(), HttpStatus.NOT_FOUND, webRequest);
    }

    @ExceptionHandler(value = UserEmailCredentialsMismatchException.class)
    protected ResponseEntity<Object> handleMismatchError(RuntimeException ex, WebRequest webRequest) {
        return handleExceptionInternal(ex, ex.getMessage(), new HttpHeaders(), HttpStatus.CONFLICT, webRequest);
    }

    @ExceptionHandler(value = AccessDeniedException.class)
    protected ResponseEntity<Object> handleAccessDeniedError(RuntimeException ex, WebRequest webRequest) {
        String responseBody = String.format("User %s has no access to the requested resource: %s",
                Objects.requireNonNull(webRequest.getUserPrincipal()).getName(), ex.getMessage());
        return handleExceptionInternal(ex, responseBody, new HttpHeaders(), HttpStatus.FORBIDDEN, webRequest);
    }
}
