package net.bakaar.sandbox.cas.infra.event;

import net.bakaar.sandbox.cas.domain.event.CaseCreated;
import net.bakaar.sandbox.cas.infra.messaging.MessageFactory;
import net.bakaar.sandbox.cas.infra.messaging.SandBoxMessageProducer;
import net.bakaar.sandbox.cas.infra.messaging.SandboxMessageFactory;
import net.bakaar.sandbox.messaging.common.Message;
import net.bakaar.sandbox.messaging.producer.MessageProducer;
import org.junit.Test;
import org.mockito.ArgumentCaptor;

import static org.assertj.core.api.Assertions.assertThat;
import static org.assertj.core.api.Assertions.catchThrowable;
import static org.mockito.Mockito.spy;
import static org.mockito.Mockito.verify;

public class DefaultDomainEventPublisherIT {

    private MessageProducer messageProducer = spy(new SandBoxMessageProducer());
    private MessageFactory messageFactory = new SandboxMessageFactory();
    private DefaultDomainEventPublisher publisher = new DefaultDomainEventPublisher(messageProducer, messageFactory);

    @Test
    public void publish_should_create_a_corresponding_message() {
        //Given
        String id = "1";
        String pNummer = "PNummer";
        CaseCreated event = new CaseCreated(id, pNummer);
        ArgumentCaptor<Message> messageArgumentCaptor = ArgumentCaptor.forClass(Message.class);
        //When
        Throwable thrown = catchThrowable(() -> publisher.publish(event));
        //Then
        assertThat(thrown).isNull();
        verify(messageProducer).produce(messageArgumentCaptor.capture());
        Message sentMessage = messageArgumentCaptor.getValue();
        assertThat(sentMessage.getPayload()).contains(id, pNummer);
    }
}