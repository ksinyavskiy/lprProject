package com.training.lprProject.event;

import org.springframework.context.event.EventListener;
import org.springframework.stereotype.Component;


@Component
public class UserLifecycleEventListener {

    @EventListener
    public void listenUserLifecycleEvent(UserLifecycleEvent userLifecycleEvent) {
        System.out.println(userLifecycleEvent.getEventMessage());
    }
}
