package net.bakaar.sandbox.cas.infra.event.inmemory;

import net.bakaar.sandbox.cas.infra.event.common.EventRaised;
import net.bakaar.sandbox.event.common.DomainEvent;
import net.bakaar.sandbox.event.common.DomainEventEmitter;

import java.util.ArrayList;
import java.util.List;

public class InMemoryDomainEventEmitter implements DomainEventEmitter {
    private List<EventRaised> allEvent = new ArrayList<>();

    @Override
    public void emit(DomainEvent event) {
        InMemoryEventRaised eventRaised = new InMemoryEventRaised(event);
        allEvent.add(eventRaised);
    }

    public List<EventRaised> getAllEvent() {
        return allEvent;
    }
}
