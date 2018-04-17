package net.bakaar.sandbox.cas.infra.messaging;

import net.bakaar.sandbox.cas.domain.event.CaseCreated;
import net.bakaar.sandbox.event.common.Event;
import net.bakaar.sandbox.messaging.common.Message;

public class SandboxMessageFactory implements MessageFactory {
    private static final String SENDER = "case-service";

    @Override
    public Message fromEvent(Event event) {
        Message message = null;
        if (event instanceof CaseCreated) {
            message = new SandboxMessage(((CaseCreated) event).getId(), ((CaseCreated) event).getPnummer(), SENDER);
        }
        return message;
    }
}
