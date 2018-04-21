package net.bakaar.sandbox.cas.infra.event.db;

import com.fasterxml.jackson.core.JsonProcessingException;
import com.fasterxml.jackson.databind.ObjectMapper;
import net.bakaar.sandbox.cas.domain.event.CaseCreated;
import org.junit.Test;

import java.util.UUID;

import static org.assertj.core.api.Assertions.assertThat;
import static org.mockito.ArgumentMatchers.any;
import static org.mockito.BDDMockito.given;
import static org.mockito.Mockito.mock;

public class EventRaisedFactoryTest {

    private EventRaisedFactory factory;


    @Test
    public void fromEvent_should_serialze_event() {
        //Given
        String pnummer = "pNummer";
        String id = UUID.randomUUID().toString();
        CaseCreated event = new CaseCreated(id, pnummer);
        ObjectMapper mapper = new ObjectMapper();
        factory = new EventRaisedFactory(mapper);
        //When
        EventRaised eventRaised = factory.fromEvent(event);
        //Then
        assertThat(eventRaised).isNotNull();
        assertThat(eventRaised.getEvent()).contains(pnummer, id);
    }

    @Test(expected = RuntimeException.class)
    public void name() throws Exception {
        //Given
        String pnummer = "pNummer";
        String id = UUID.randomUUID().toString();
        CaseCreated event = new CaseCreated(id, pnummer);
        ObjectMapper mapper = mock(ObjectMapper.class);
        given(mapper.writeValueAsString(any())).willThrow(new SimpleJsonProcessingException("Error"));
        factory = new EventRaisedFactory(mapper);
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