package net.bakaar.sandbox.cas.infra.messaging;

import net.bakaar.sandbox.messaging.common.Message;

public class SandboxMessage implements Message {

    private final String payload;

    public SandboxMessage(String payload) {
        this.payload = payload;
    }

    @Override
    public String getPayload() {
        return payload;
    }
}
