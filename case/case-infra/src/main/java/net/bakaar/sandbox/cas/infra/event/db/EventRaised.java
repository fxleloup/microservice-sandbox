package net.bakaar.sandbox.cas.infra.event.db;

import javax.persistence.Entity;

@Entity
public class EventRaised {
    private final String event;

    public EventRaised(String event) {
        this.event = event;
    }


    public String getEvent() {
        return event;
    }
}
