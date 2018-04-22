package net.bakaar.sandbox.event.springdata;

import net.bakaar.sandbox.event.common.EventRaised;

import javax.persistence.Entity;
import java.time.Instant;

@Entity
public class DBEventRaised implements EventRaised<String> {
    private final String event;
    private Instant raised;

    DBEventRaised(String event) {
        this.event = event;
    }

    DBEventRaised raisedAt(Instant raised) {
        this.raised = raised;
        return this;
    }


    public String getEvent() {
        return event;
    }

    @Override
    public Instant raiseAt() {
        return raised;
    }
}
