package net.bakaar.sandbox.event.db;

import com.fasterxml.jackson.core.JsonProcessingException;
import com.fasterxml.jackson.databind.ObjectMapper;
import net.bakaar.sandbox.event.common.DomainEvent;

public class DBEventRaisedFactory {

    private final ObjectMapper mapper;

    public DBEventRaisedFactory(ObjectMapper mapper) {
        this.mapper = mapper;
    }

    public DBEventRaised fromEvent(DomainEvent event) {
        try {
            return new DBEventRaised(mapper.writeValueAsString(event));
        } catch (JsonProcessingException e) {
            throw new RuntimeException(e);
        }
    }
}
