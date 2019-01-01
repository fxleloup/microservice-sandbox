package net.bakaar.sandbox.event.domain.springdata;

import net.bakaar.sandbox.event.domain.Event;
import net.bakaar.sandbox.event.domain.EventStore;

public class DBEventStore implements EventStore {

    private final EventRaisedRepository repository;
    private DBEventRaisedFactory factory;

    public DBEventStore(EventRaisedRepository repository, DBEventRaisedFactory factory) {
        this.repository = repository;
        this.factory = factory;
    }

    @Override
    public void store(Event event) {

        repository.save(factory.fromEvent(event));
    }
}
