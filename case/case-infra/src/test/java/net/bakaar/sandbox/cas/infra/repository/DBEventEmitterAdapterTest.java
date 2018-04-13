package net.bakaar.sandbox.cas.infra.repository;

import com.fasterxml.jackson.databind.ObjectMapper;
import net.bakaar.sandbox.cas.domain.event.CaseCreated;
import net.bakaar.sandbox.cas.domain.event.Event;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.mockito.ArgumentCaptor;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.junit.MockitoJUnitRunner;

import static org.assertj.core.api.Assertions.assertThat;
import static org.mockito.ArgumentMatchers.any;
import static org.mockito.BDDMockito.given;
import static org.mockito.Mockito.verify;


@RunWith(MockitoJUnitRunner.class)
public class DBEventEmitterAdapterTest {
    @Mock
    private EventRepository repository;
    @Mock
    private ObjectMapper mapper;
    @InjectMocks
    private DBEventEmitterAdapter emitter;


    @Test
    public void emit_should_save_event_in_DB_in_JSON() throws Exception {
        //Given
        Event event = new CaseCreated("123", "PNummer");
        given(mapper.writeValueAsString(event)).willReturn(String.format("{\"id\\”:%s,\"pnummer\":%s}", ((CaseCreated) event).getId(), ((CaseCreated) event).getPnummer()));
        //When
        emitter.emit(event);
        //Then
        ArgumentCaptor<EventEntity> entityArgumentCaptor = ArgumentCaptor.forClass(EventEntity.class);
        verify(repository).save(entityArgumentCaptor.capture());
        verify(mapper).writeValueAsString(any(Event.class));
        EventEntity capturedEntity = entityArgumentCaptor.getValue();
        assertThat(capturedEntity).isNotNull();
        assertThat(capturedEntity.getPayload()).contains(((CaseCreated) event).getId(), ((CaseCreated) event).getPnummer());
    }

}
