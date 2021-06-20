package com.training.lprProject.event;


public class UserLifecycleEvent {
    private final String eventMessage;

    public UserLifecycleEvent(String eventMessage) {
        this.eventMessage = eventMessage;
    }

    public String getEventMessage() {
        return eventMessage;
    }
}
