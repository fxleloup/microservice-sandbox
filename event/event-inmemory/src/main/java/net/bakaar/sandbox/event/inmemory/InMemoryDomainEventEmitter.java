package net.bakaar.sandbox.event.inmemory;

import net.bakaar.sandbox.event.common.DomainEvent;
import net.bakaar.sandbox.event.common.DomainEventEmitter;
import net.bakaar.sandbox.event.common.EventRaised;

import java.time.Instant;
import java.util.ArrayList;
import java.util.List;

public class InMemoryDomainEventEmitter implements DomainEventEmitter {
    private List<EventRaised> allEvent = new ArrayList<>();

    @Override
    public void emit(DomainEvent event) {
        InMemoryEventRaised eventRaised = new InMemoryEventRaised(event).raisedAt(Instant.now());
        allEvent.add(eventRaised);
    }

    public List<EventRaised> getAllEvent() {
        return allEvent;
    }
}
