package net.bakaar.sandbox.cas.infra.event;

import net.bakaar.sandbox.cas.domain.event.CaseCreated;
import net.bakaar.sandbox.event.common.Event;
import net.bakaar.sandbox.messaging.common.Message;
import net.bakaar.sandbox.messaging.producer.MessageProducer;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.mockito.ArgumentCaptor;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.junit.MockitoJUnitRunner;

import static org.assertj.core.api.Assertions.assertThat;
import static org.mockito.Mockito.verify;


@RunWith(MockitoJUnitRunner.class)
public class DefaultDomainEventPublisherTest {
    @Mock
    private MessageProducer producer;
    @InjectMocks
    private DefaultDomainEventPublisher publisher;


    @Test
    public void emit_should_save_event_in_DB_in_JSON() {
        //Given
        Event event = new CaseCreated("123", "PNummer");
        //When
        publisher.publish(event);
        //Then
        ArgumentCaptor<Message> entityArgumentCaptor = ArgumentCaptor.forClass(Message.class);
        verify(producer).produce(entityArgumentCaptor.capture());
        Message capturedEntity = entityArgumentCaptor.getValue();
        assertThat(capturedEntity).isNotNull();
        assertThat(capturedEntity.getPayload()).contains(((CaseCreated) event).getId(), ((CaseCreated) event).getPnummer());
    }

}
