package net.bakaar.sandbox.event.inmemory;

import net.bakaar.sandbox.event.common.DomainEvent;
import net.bakaar.sandbox.event.common.EventRaised;

public class InMemoryEventRaised implements EventRaised<DomainEvent> {
    private final DomainEvent event;

    public InMemoryEventRaised(DomainEvent event) {
        this.event = event;
    }

    @Override
    public DomainEvent getEvent() {
        return event;
    }
}
