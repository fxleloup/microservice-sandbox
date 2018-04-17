package net.bakaar.sandbox.cas.infra.messaging;

import net.bakaar.sandbox.messaging.common.Message;
import net.bakaar.sandbox.messaging.producer.MessageProducer;

public class SandBoxMessageProducer implements MessageProducer {
    @Override
    public void produce(Message messsage) {
        System.out.println(messsage);
    }
}
