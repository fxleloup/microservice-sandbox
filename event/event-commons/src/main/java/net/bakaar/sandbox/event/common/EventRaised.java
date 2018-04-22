package net.bakaar.sandbox.event.common;

import java.time.Instant;

public interface EventRaised<T> {

    T getEvent();

    Instant raiseAt();
}
