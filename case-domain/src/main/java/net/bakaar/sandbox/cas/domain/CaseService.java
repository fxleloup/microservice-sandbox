package net.bakaar.sandbox.cas.domain;

import net.bakaar.sandbox.cas.domain.aggregate.Case;
import net.bakaar.sandbox.cas.domain.event.CaseCreated;
import net.bakaar.sandbox.cas.domain.event.EventEmitter;

public class CaseService {
    private final EventEmitter emitter;

    public CaseService(EventEmitter emitter) {
        this.emitter = emitter;
    }

    public Case createCase(String pnummer) {
        Case caseCreated = new Case(pnummer);
        CaseCreated event = new CaseCreated(caseCreated.getId(), caseCreated.getPnummer());
        emitter.emit(event);
        return caseCreated;
    }
}
