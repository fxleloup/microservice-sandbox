package net.bakaar.sandbox.event.db;

import net.bakaar.sandbox.event.common.DomainEvent;
import net.bakaar.sandbox.event.common.DomainEventEmitter;

public class DBDomainEventEmitter implements DomainEventEmitter {

    private final EventRaisedRepository repository;
    private DBEventRaisedFactory factory;

    public DBDomainEventEmitter(EventRaisedRepository repository, DBEventRaisedFactory factory) {
        this.repository = repository;
        this.factory = factory;
    }

    @Override
    public void emit(DomainEvent event) {

        repository.save(factory.fromEvent(event));
    }
}
