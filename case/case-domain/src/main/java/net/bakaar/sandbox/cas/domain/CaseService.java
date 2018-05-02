package net.bakaar.sandbox.cas.domain;

import net.bakaar.sandbox.cas.domain.event.CaseCreated;
import net.bakaar.sandbox.cas.domain.repository.CaseRepository;
import net.bakaar.sandbox.event.common.DomainEventEmitter;

import java.time.LocalDate;

public class CaseService {
    private final DomainEventEmitter eventEmitter;
    private final CaseRepository repository;
    private final CaseDomainObjectFactory factory;

    public CaseService(DomainEventEmitter eventEmitter,
                       CaseRepository repository,
                       CaseDomainObjectFactory factory) {
        this.eventEmitter = eventEmitter;
        this.repository = repository;
        this.factory = factory;
    }

    public Case createCase(String pnummer, LocalDate birthDate) {
        Case caseCreated = factory.createCase(pnummer, birthDate);
        CaseCreated event = new CaseCreated(caseCreated.getId(), caseCreated.getPnummer());
        eventEmitter.emit(event);
        return repository.save(caseCreated);
    }
}
