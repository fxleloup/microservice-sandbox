package net.bakaar.sandbox.cas.domain.event;

public interface EventEmitter {

    void emit(Event event);
}
