package net.bakaar.sandbox.casemgmt;

import net.bakaar.sandbox.casemgmt.aggregate.Case;
import net.bakaar.sandbox.casemgmt.event.CaseCreated;
import net.bakaar.sandbox.casemgmt.event.EventEmitter;

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
