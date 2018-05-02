package net.bakaar.sandbox.event.springdata;

import net.bakaar.sandbox.event.common.EventRaised;

import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import java.time.Instant;

@Entity
public class DBEventRaised implements EventRaised<String> {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;
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

    public Long getId() {
        return id;
    }
}
