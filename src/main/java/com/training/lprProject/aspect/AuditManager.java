package com.training.lprProject.aspect;

import org.aspectj.lang.annotation.After;
import org.aspectj.lang.annotation.Aspect;
import org.aspectj.lang.annotation.Before;
import org.aspectj.lang.annotation.Pointcut;

@Aspect
public class AuditManager {

    @Pointcut("execution(* com.training.lprProject.service.UserService.getAllStudents())")
    public void logEvent() {

    }

    @Before("logEvent()")
    public void logMethodStart() {
        System.out.println("Start getting all students...");
    }

    @After("logEvent()")
    public void logMethodEnd() {
        System.out.println("All students are received!");
    }
}
