package net.bakaar.sandbox.cas.domain.cucumber.repository;

import net.bakaar.sandbox.event.common.Event;
import net.bakaar.sandbox.event.publisher.DomainEventPublisher;

public class DummyEventEmmitter implements DomainEventPublisher {

    private Event event;

    @Override
    public void emit(Event event) {
        this.event = event;
    }

    public Event getEvent() {
        return event;
    }
}
