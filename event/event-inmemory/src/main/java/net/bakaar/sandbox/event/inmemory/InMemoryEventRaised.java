package net.bakaar.sandbox.event.inmemory;

import net.bakaar.sandbox.event.common.DomainEvent;
import net.bakaar.sandbox.event.common.EventRaised;

import java.time.Instant;

public class InMemoryEventRaised implements EventRaised<DomainEvent> {
    private final DomainEvent event;
    private Instant raised;

    InMemoryEventRaised(DomainEvent event) {
        this.event = event;
    }

    InMemoryEventRaised raisedAt(Instant raised) {
        this.raised = raised;
        return this;
    }

    @Override
    public DomainEvent getEvent() {
        return event;
    }

    @Override
    public Instant raiseAt() {
        return raised;
    }
}
