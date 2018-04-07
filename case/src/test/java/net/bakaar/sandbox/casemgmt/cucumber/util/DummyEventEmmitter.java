package net.bakaar.sandbox.casemgmt.cucumber.util;

import net.bakaar.sandbox.casemgmt.event.Event;
import net.bakaar.sandbox.casemgmt.event.EventEmitter;

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
