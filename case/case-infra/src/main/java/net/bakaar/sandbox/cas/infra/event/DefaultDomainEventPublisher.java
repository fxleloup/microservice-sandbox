package net.bakaar.sandbox.cas.infra.event;

import com.fasterxml.jackson.core.JsonProcessingException;
import com.fasterxml.jackson.databind.ObjectMapper;
import net.bakaar.sandbox.cas.infra.messaging.SandboxMessage;
import net.bakaar.sandbox.event.common.Event;
import net.bakaar.sandbox.event.publisher.DomainEventPublisher;
import net.bakaar.sandbox.messaging.producer.MessageProducer;

public class DefaultDomainEventPublisher implements DomainEventPublisher {

    private final MessageProducer messageProducer;
    private final ObjectMapper jsonMapper = new ObjectMapper();

    public DefaultDomainEventPublisher(MessageProducer messageProducer) {
        this.messageProducer = messageProducer;
    }

    @Override
    public void publish(Event event) {
        try {
            messageProducer.produce(new SandboxMessage(jsonMapper.writeValueAsString(event)));
        } catch (JsonProcessingException e) {
            throw new RuntimeException(e);
        }
    }
}
