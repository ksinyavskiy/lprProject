package com.training.lprProject.event;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.ApplicationEventPublisher;
import org.springframework.stereotype.Component;

@Component
public class UserLifecycleEventPublisher {
    private ApplicationEventPublisher applicationEventPublisher;

    @Autowired
    public UserLifecycleEventPublisher(ApplicationEventPublisher applicationEventPublisher) {
        this.applicationEventPublisher = applicationEventPublisher;
    }

    public void publishEvent(String message) {
        UserLifecycleEvent userLifecycleEvent = new UserLifecycleEvent(message);
        applicationEventPublisher.publishEvent(userLifecycleEvent);
    }
}
