package net.bakaar.sandbox.cas.domain;

import net.bakaar.sandbox.cas.domain.aggregate.Case;
import net.bakaar.sandbox.cas.domain.event.CaseCreated;
import net.bakaar.sandbox.cas.domain.repository.CaseRepository;
import net.bakaar.sandbox.cas.domain.repository.EventEmitter;

public class CaseService {
    private final EventEmitter emitter;
    private final CaseRepository repository;

    public CaseService(EventEmitter emitter, CaseRepository repository) {
        this.emitter = emitter;
        this.repository = repository;
    }

    public Case createCase(String pnummer) throws Exception {
        Case caseCreated = new Case(pnummer);
        CaseCreated event = new CaseCreated(caseCreated.getId(), caseCreated.getPnummer());
        emitter.emit(event);
        return repository.save(caseCreated);
    }
}
