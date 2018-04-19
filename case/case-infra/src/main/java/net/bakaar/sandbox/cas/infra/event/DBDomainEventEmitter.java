package net.bakaar.sandbox.cas.infra.event;

import net.bakaar.sandbox.event.common.DomainEvent;
import net.bakaar.sandbox.event.common.DomainEventEmitter;

public class DBDomainEventEmitter implements DomainEventEmitter {
    @Override
    public void emit(DomainEvent event) {
        System.out.println(event);
    }
}
