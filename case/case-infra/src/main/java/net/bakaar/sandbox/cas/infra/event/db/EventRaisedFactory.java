package net.bakaar.sandbox.cas.infra.event.db;

import com.fasterxml.jackson.core.JsonProcessingException;
import com.fasterxml.jackson.databind.ObjectMapper;
import net.bakaar.sandbox.event.common.DomainEvent;

public class EventRaisedFactory {

    private final ObjectMapper mapper;

    public EventRaisedFactory(ObjectMapper mapper) {
        this.mapper = mapper;
    }

    public EventRaised fromEvent(DomainEvent event) {
        try {
            return new EventRaised(mapper.writeValueAsString(event));
        } catch (JsonProcessingException e) {
            throw new RuntimeException(e);
        }
    }
}
