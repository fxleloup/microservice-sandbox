package net.bakaar.sandbox.event.springdata;

import net.bakaar.sandbox.event.common.EventRaised;

import javax.persistence.Entity;

@Entity
public class DBEventRaised implements EventRaised<String> {
    private final String event;

    public DBEventRaised(String event) {
        this.event = event;
    }


    public String getEvent() {
        return event;
    }
}
