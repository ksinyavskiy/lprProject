package com.training.lprProject.aspect;

import com.training.lprProject.entity.User;
import org.aspectj.lang.ProceedingJoinPoint;
import org.aspectj.lang.annotation.After;
import org.aspectj.lang.annotation.Around;
import org.aspectj.lang.annotation.Aspect;
import org.aspectj.lang.annotation.Before;
import org.aspectj.lang.annotation.Pointcut;

import java.util.Arrays;

@Aspect
public class AuditManager {

    @Pointcut("execution(* com.training.lprProject.service.UserService.getAllStudents())")
    public void logGetAllStudents() {

    }

    @Pointcut("execution(* com.training.lprProject.service.UserService" +
            ".addStudent(com.training.lprProject.entity.User)) && args(user)")
    public void logAddingStudent(User user) {

    }

    @Before("logGetAllStudents()")
    public void logMethodStart() {
        System.out.println("Start getting all students...");
    }

    @After("logGetAllStudents()")
    public void logMethodEnd() {
        System.out.println("All students are received!");
    }

    @Around(value = "logAddingStudent(user)", argNames = "proceedingJoinPoint, user")
    public Object logBothMethodStartEnd(ProceedingJoinPoint proceedingJoinPoint, User user) throws Throwable {
        Object result;
        try {
            System.out.println("The service are ready to add a new student with id " + user.getUserId());
            result = proceedingJoinPoint.proceed();
            System.out.println("The user adding process is finished successfully!");
        } catch (Throwable throwable) {
            System.out.println("An exception occurred during the user adding process: "
                    + Arrays.toString(throwable.getStackTrace()));
            throw throwable;
        }
        return result;
    }
}
