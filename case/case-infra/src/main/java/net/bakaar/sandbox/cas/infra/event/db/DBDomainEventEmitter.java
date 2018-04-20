package net.bakaar.sandbox.cas.infra.event.db;

import net.bakaar.sandbox.event.common.DomainEvent;
import net.bakaar.sandbox.event.common.DomainEventEmitter;

public class DBDomainEventEmitter implements DomainEventEmitter {

    private final EventRaisedRepository repository;

    public DBDomainEventEmitter(EventRaisedRepository repository) {
        this.repository = repository;
    }

    @Override
    public void emit(DomainEvent event) {

        repository.save(EventRaisedFactory.fromEvent(event));
    }
}
