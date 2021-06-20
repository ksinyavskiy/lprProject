package com.training.lprProject.aspect;

import com.training.lprProject.entity.User;
import com.training.lprProject.event.UserLifecycleEvent;
import org.aspectj.lang.ProceedingJoinPoint;
import org.aspectj.lang.annotation.Around;
import org.aspectj.lang.annotation.Aspect;
import org.aspectj.lang.annotation.Pointcut;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.ApplicationEventPublisher;
import org.springframework.stereotype.Component;

import java.text.SimpleDateFormat;
import java.util.Date;

@Aspect
@Component
public class EventManager {

    private final ApplicationEventPublisher applicationEventPublisher;

    @Autowired
    public EventManager(ApplicationEventPublisher applicationEventPublisher) {
        this.applicationEventPublisher = applicationEventPublisher;
    }

    @Pointcut("execution(* com.training.lprProject.service.UserService" +
            ".addStudent(com.training.lprProject.entity.User)) && args(user)")
    public void trackAddingStudent(User user) {

    }

    @Around(value = "trackAddingStudent(user)", argNames = "proceedingJoinPoint, user")
    public Object trackUserLifecycleEvents(ProceedingJoinPoint proceedingJoinPoint, User user) throws Throwable {
        Object result;
        UserLifecycleEvent userBeforeCreationEvent =
                new UserLifecycleEvent("New user object is being prepared to creation! Date&time: " +
                        getFormattedDateAndTime());
        UserLifecycleEvent userAfterCreationEvent =
                new UserLifecycleEvent("User " + user.getUsername() + " has been successfully created! " +
                        "Date&time: " + getFormattedDateAndTime());
        try {
            applicationEventPublisher.publishEvent(userBeforeCreationEvent);
            result = proceedingJoinPoint.proceed();
            applicationEventPublisher.publishEvent(userAfterCreationEvent);
        } catch (Throwable throwable) {
            System.out.println("An exception occurred during the user adding process: "
                    + throwable.getMessage());
            throw throwable;
        }
        return result;
    }

    private String getFormattedDateAndTime() {
        SimpleDateFormat dateFormat = new SimpleDateFormat("dd-MM-yyyy HH:mm:ss");
        return dateFormat.format(new Date());
    }
}
