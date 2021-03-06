package com.training.lprProject.error.handler;

import com.training.lprProject.error.custom.UserNotFoundException;
import org.springframework.dao.DataIntegrityViolationException;
import org.springframework.http.HttpHeaders;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.web.bind.annotation.ControllerAdvice;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.context.request.WebRequest;
import org.springframework.web.servlet.mvc.method.annotation.ResponseEntityExceptionHandler;

import javax.servlet.http.HttpServletRequest;
import java.util.Objects;

@ControllerAdvice
public class RestExceptionHandler extends ResponseEntityExceptionHandler {

    @ExceptionHandler(Exception.class)
    protected ResponseEntity<String> handleCommonException(Exception ex, HttpServletRequest request) {
        if (ex instanceof NullPointerException) {
            return new ResponseEntity<>("NPE when trying to use the following resource: " +
                    request.getRequestURI(),HttpStatus.BAD_REQUEST);
        }
        return new ResponseEntity<>("Internal Server Error, check your request URL: " + request.getRequestURI(),
                HttpStatus.INTERNAL_SERVER_ERROR);
    }

    @ExceptionHandler(value = {UserNotFoundException.class, UsernameNotFoundException.class})
    protected ResponseEntity<Object> handleNotFoundError(RuntimeException ex, WebRequest webRequest) {
        return handleExceptionInternal(ex, ex.getMessage(), new HttpHeaders(), HttpStatus.NOT_FOUND, webRequest);
    }

    @ExceptionHandler(value = org.h2.jdbc.JdbcSQLDataException.class)
    protected ResponseEntity<Object> handleDbError(RuntimeException ex, WebRequest webRequest) {
        String responseBody = ex.getMessage();
        if (ex instanceof DataIntegrityViolationException) {
            responseBody = Objects.requireNonNull(((DataIntegrityViolationException) ex).getRootCause()).toString();
        }
        return handleExceptionInternal(ex, responseBody, new HttpHeaders(), HttpStatus.BAD_REQUEST, webRequest);
    }
}
