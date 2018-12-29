package net.bakaar.sandbox.cas.domain.event;

import net.bakaar.sandbox.event.common.DomainEvent;
import net.bakaar.sandbox.shared.domain.vo.PNumber;

public class CaseCreated implements DomainEvent {
    private final String id;
    private final PNumber pnummer;

    public CaseCreated(String id, PNumber pnummer) {
        this.id = id;
        this.pnummer = pnummer;
    }

    public String getId() {
        return id;
    }

    public PNumber getPnummer() {
        return pnummer;
    }
}
