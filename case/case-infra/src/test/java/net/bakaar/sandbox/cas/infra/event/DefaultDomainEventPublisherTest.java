package net.bakaar.sandbox.cas.infra.event;

import net.bakaar.sandbox.cas.domain.event.CaseCreated;
import net.bakaar.sandbox.cas.infra.messaging.MessageFactory;
import net.bakaar.sandbox.event.common.Event;
import net.bakaar.sandbox.messaging.common.Message;
import net.bakaar.sandbox.messaging.producer.MessageProducer;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.mockito.ArgumentCaptor;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.junit.MockitoJUnitRunner;

import static org.mockito.Mockito.verify;


@RunWith(MockitoJUnitRunner.class)
public class DefaultDomainEventPublisherTest {
    @Mock
    private MessageProducer producer;
    @Mock
    private MessageFactory factory;
    @InjectMocks
    private DefaultDomainEventPublisher publisher;


    @Test
    public void publish_should_tranform_event_to_message_and_send_it_to_producer() {
        //Given
        Event event = new CaseCreated("123", "PNummer");
        //When
        publisher.publish(event);
        //Then
        ArgumentCaptor<Message> entityArgumentCaptor = ArgumentCaptor.forClass(Message.class);
        verify(producer).produce(entityArgumentCaptor.capture());
        verify(factory).fromEvent(event);
    }

}
