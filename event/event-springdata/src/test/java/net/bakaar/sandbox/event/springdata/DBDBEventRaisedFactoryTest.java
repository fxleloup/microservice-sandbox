package net.bakaar.sandbox.event.springdata;

import com.fasterxml.jackson.core.JsonProcessingException;
import com.fasterxml.jackson.databind.ObjectMapper;
import net.bakaar.sandbox.event.common.DomainEvent;
import org.junit.Test;
import org.mockito.ArgumentMatchers;

import java.time.Instant;

import static org.assertj.core.api.Assertions.assertThat;
import static org.assertj.core.api.Assertions.catchThrowable;
import static org.mockito.BDDMockito.given;
import static org.mockito.Mockito.mock;

public class DBDBEventRaisedFactoryTest {

    private DBEventRaisedFactory factory;

    @Test
    public void fromEvent_should_serialze_event() {
        //Given
        DomainEvent event = mock(DomainEvent.class);
        ObjectMapper mapper = new ObjectMapper();
        factory = new DBEventRaisedFactory(mapper);
        Instant before = Instant.now();
        //When
        DBEventRaised dbEventRaised = factory.fromEvent(event);
        //Then
        Instant after = Instant.now();
        assertThat(dbEventRaised).isNotNull();
        assertThat(dbEventRaised.getEvent()).isNotBlank();
        assertThat(dbEventRaised.raiseAt()).isBetween(before, after);
        assertThat(dbEventRaised.getId()).isNull();
    }

    @Test
    public void fromEvent_should_throw_runtimeexception() throws Exception {
        //Given
        DomainEvent event = mock(DomainEvent.class);
        ObjectMapper mapper = mock(ObjectMapper.class);
        Throwable t = new SimpleJsonProcessingException("Error");
        given(mapper.writeValueAsString(ArgumentMatchers.any())).willThrow(t);
        factory = new DBEventRaisedFactory(mapper);
        //When
        Throwable thrown = catchThrowable(() -> factory.fromEvent(event));
        //Then
        assertThat(thrown.getCause()).isEqualTo(t);
    }

    private class SimpleJsonProcessingException extends JsonProcessingException {

        protected SimpleJsonProcessingException(String msg) {
            super(msg);
        }
    }
}