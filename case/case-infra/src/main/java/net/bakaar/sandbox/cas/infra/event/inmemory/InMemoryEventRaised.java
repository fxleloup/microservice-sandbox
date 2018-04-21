package net.bakaar.sandbox.cas.infra.event.inmemory;

import net.bakaar.sandbox.cas.infra.event.common.EventRaised;
import net.bakaar.sandbox.event.common.DomainEvent;

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
