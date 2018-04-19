package net.bakaar.sandbox.event.jpa.repository;

import net.bakaar.sandbox.event.common.DomainEvent;

import java.time.Instant;

public interface DomainEventRaised extends DomainEvent {
    DomainEventStatus getStatus();

    Instant raisedAt();
}
