package net.bakaar.sandbox.event.inmemory;

import net.bakaar.sandbox.event.domain.Event;

import java.time.Instant;

public class InMemoryEventRaised {
    private final Event event;
    private Instant raised;

    InMemoryEventRaised(Event event) {
        this.event = event;
    }

    InMemoryEventRaised raisedAt(Instant raised) {
        this.raised = raised;
        return this;
    }

    public Event getEvent() {
        return event;
    }

    public Instant raiseAt() {
        return raised;
    }
}
