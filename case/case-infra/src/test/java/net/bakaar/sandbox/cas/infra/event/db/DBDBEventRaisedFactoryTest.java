package net.bakaar.sandbox.cas.infra.event.db;

import com.fasterxml.jackson.core.JsonProcessingException;
import com.fasterxml.jackson.databind.ObjectMapper;
import net.bakaar.sandbox.cas.domain.event.CaseCreated;
import org.junit.Test;
import org.mockito.ArgumentMatchers;

import java.util.UUID;

import static org.assertj.core.api.Assertions.assertThat;
import static org.mockito.BDDMockito.given;
import static org.mockito.Mockito.mock;

public class DBDBEventRaisedFactoryTest {

    private DBEventRaisedFactory factory;

    @Test
    public void fromEvent_should_serialze_event() {
        //Given
        String pnummer = "pNummer";
        String id = UUID.randomUUID().toString();
        CaseCreated event = new CaseCreated(id, pnummer);
        ObjectMapper mapper = new ObjectMapper();
        factory = new DBEventRaisedFactory(mapper);
        //When
        DBEventRaised DBEventRaised = factory.fromEvent(event);
        //Then
        assertThat(DBEventRaised).isNotNull();
        assertThat(DBEventRaised.getEvent()).contains(pnummer, id);
    }

    @Test(expected = RuntimeException.class)
    public void name() throws Exception {
        //Given
        String pnummer = "pNummer";
        String id = UUID.randomUUID().toString();
        CaseCreated event = new CaseCreated(id, pnummer);
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