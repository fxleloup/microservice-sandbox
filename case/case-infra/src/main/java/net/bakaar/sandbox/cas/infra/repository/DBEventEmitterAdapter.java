package net.bakaar.sandbox.cas.infra.repository;

import com.fasterxml.jackson.core.JsonProcessingException;
import com.fasterxml.jackson.databind.ObjectMapper;
import net.bakaar.sandbox.event.common.Event;
import net.bakaar.sandbox.event.publisher.DomainEventPublisher;

public class DBEventEmitterAdapter implements DomainEventPublisher {

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
