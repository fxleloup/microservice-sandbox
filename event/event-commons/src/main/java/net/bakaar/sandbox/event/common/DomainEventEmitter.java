package net.bakaar.sandbox.event.common;

public interface DomainEventEmitter {

    void emit(DomainEvent event);
}
