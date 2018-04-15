package net.bakaar.sandbox.event.publisher;

import net.bakaar.sandbox.event.common.Event;

public interface DomainEventPublisher {

    void emit(Event event) throws Exception;
}
