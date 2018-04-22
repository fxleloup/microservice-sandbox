package net.bakaar.sandbox.event.inmemory;

import net.bakaar.sandbox.event.common.DomainEvent;
import org.junit.Test;

import static org.assertj.core.api.Assertions.assertThat;
import static org.mockito.Mockito.mock;

public class InMemoryDomainEventEmitterTest {

    private InMemoryDomainEventEmitter emitter = new InMemoryDomainEventEmitter();

    @Test
    public void emit_should_store_event_localy() {
        //Given
        DomainEvent event = mock(DomainEvent.class);
        //When
        emitter.emit(event);
        //Then
        assertThat(emitter.getAllEvent().get(0).getEvent()).isEqualTo(event);
    }
}