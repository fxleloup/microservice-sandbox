package net.bakaar.sandbox.event.springdata;

import com.fasterxml.jackson.core.JsonProcessingException;
import com.fasterxml.jackson.databind.ObjectMapper;
import net.bakaar.sandbox.event.common.DomainEvent;
import org.junit.Test;
import org.mockito.ArgumentMatchers;

import static org.assertj.core.api.Assertions.assertThat;
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
        //When
        DBEventRaised DBEventRaised = factory.fromEvent(event);
        //Then
        assertThat(DBEventRaised).isNotNull();
        assertThat(DBEventRaised.getEvent()).isNotBlank();
    }

    @Test(expected = RuntimeException.class)
    public void fromEvent_should_throw_runtimeexception() throws Exception {
        //Given
        DomainEvent event = mock(DomainEvent.class);
        ObjectMapper mapper = mock(ObjectMapper.class);
        given(mapper.writeValueAsString(ArgumentMatchers.any())).willThrow(new SimpleJsonProcessingException("Error"));
        factory = new DBEventRaisedFactory(mapper);
        //When
        factory.fromEvent(event);
        //Then
    }

    private class SimpleJsonProcessingException extends JsonProcessingException {

        protected SimpleJsonProcessingException(String msg) {
            super(msg);
        }
    }
}