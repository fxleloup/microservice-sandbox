package net.bakaar.sandbox.cas.infra.messaging;

import net.bakaar.sandbox.event.common.Event;
import net.bakaar.sandbox.messaging.common.Message;

public interface MessageFactory {

    Message fromEvent(Event event);
}
