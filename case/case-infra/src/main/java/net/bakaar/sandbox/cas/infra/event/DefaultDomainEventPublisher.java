package net.bakaar.sandbox.cas.infra.event;

import net.bakaar.sandbox.cas.infra.messaging.MessageFactory;
import net.bakaar.sandbox.event.common.Event;
import net.bakaar.sandbox.event.publisher.DomainEventPublisher;
import net.bakaar.sandbox.messaging.producer.MessageProducer;

public class DefaultDomainEventPublisher implements DomainEventPublisher {

    private final MessageProducer messageProducer;
    private final MessageFactory messageFactory;

    public DefaultDomainEventPublisher(MessageProducer messageProducer, MessageFactory messageFactory) {
        this.messageProducer = messageProducer;
        this.messageFactory = messageFactory;
    }

    @Override
    public void publish(Event event) {
        messageProducer.produce(messageFactory.fromEvent(event));
    }
}
