package net.bakaar.sandbox.cas.domain.cucumber.repository;

import net.bakaar.sandbox.cas.domain.event.Event;
import net.bakaar.sandbox.cas.domain.repository.EventEmitter;

public class DummyEventEmmitter implements EventEmitter {

    private Event event;

    @Override
    public void emit(Event event) {
        this.event = event;
    }

    public Event getEvent() {
        return event;
    }
}
