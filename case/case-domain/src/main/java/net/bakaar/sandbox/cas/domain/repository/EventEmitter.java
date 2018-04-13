package net.bakaar.sandbox.cas.domain.repository;

import net.bakaar.sandbox.cas.domain.event.Event;

public interface EventEmitter {

    void emit(Event event) throws Exception;
}
