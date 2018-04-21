package net.bakaar.sandbox.cas.infra.event.db;

import net.bakaar.sandbox.cas.infra.event.common.EventRaised;

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
