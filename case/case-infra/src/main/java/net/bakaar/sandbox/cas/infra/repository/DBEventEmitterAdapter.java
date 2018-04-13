package net.bakaar.sandbox.cas.infra.repository;

import com.fasterxml.jackson.core.JsonProcessingException;
import com.fasterxml.jackson.databind.ObjectMapper;
import net.bakaar.sandbox.cas.domain.event.Event;
import net.bakaar.sandbox.cas.domain.repository.EventEmitter;

public class DBEventEmitterAdapter implements EventEmitter {

    private final ObjectMapper mapper;
    private final EventRepository repository;

    public DBEventEmitterAdapter(ObjectMapper mapper, EventRepository repository) {
        this.mapper = mapper;
        this.repository = repository;
    }

    @Override
    public void emit(Event event) throws JsonProcessingException {
        repository.save(new EventEntity().setPayload(mapper.writeValueAsString(event)));
    }
}
