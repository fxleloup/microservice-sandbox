package net.bakaar.sandbox.cas.infra.messaging;

import net.bakaar.sandbox.messaging.common.Message;

public class SandboxMessage implements Message {
    private final String id;
    private final String pnummer;
    private final String sender;

    public SandboxMessage(String id, String pnummer, String sender) {
        this.id = id;
        this.pnummer = pnummer;
        this.sender = sender;
    }

    @Override
    public String getPayload() {
        return String.format("id=%s,pnummer=%s,sender=%s", id, pnummer, sender);
    }
}
