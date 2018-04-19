package net.bakaar.sandbox.cas.domain;

import net.bakaar.sandbox.cas.domain.aggregate.Case;
import net.bakaar.sandbox.cas.domain.event.CaseCreated;
import net.bakaar.sandbox.cas.domain.repository.CaseRepository;
import net.bakaar.sandbox.event.common.DomainEventEmitter;

public class CaseService {
    private final DomainEventEmitter eventEmitter;
    private final CaseRepository repository;

    public CaseService(DomainEventEmitter eventEmitter, CaseRepository repository) {
        this.eventEmitter = eventEmitter;
        this.repository = repository;
    }

    public Case createCase(String pnummer) {
        Case caseCreated = new Case(pnummer);
        CaseCreated event = new CaseCreated(caseCreated.getId(), caseCreated.getPnummer());
        eventEmitter.emit(event);
        return repository.save(caseCreated);
    }
}
