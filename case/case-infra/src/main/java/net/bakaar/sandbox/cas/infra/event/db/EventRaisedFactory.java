package net.bakaar.sandbox.cas.infra.event.db;

import com.fasterxml.jackson.core.JsonProcessingException;
import com.fasterxml.jackson.databind.ObjectMapper;
import net.bakaar.sandbox.event.common.DomainEvent;

public class EventRaisedFactory {

    private static final ObjectMapper MAPPER = new ObjectMapper();

    public static EventRaised fromEvent(DomainEvent event) {
        try {
            return new EventRaised(MAPPER.writeValueAsString(event));
        } catch (JsonProcessingException e) {
            throw new RuntimeException(e);
        }
    }
}
