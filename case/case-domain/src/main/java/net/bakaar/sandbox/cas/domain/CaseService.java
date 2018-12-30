package net.bakaar.sandbox.cas.domain;

import net.bakaar.sandbox.cas.domain.entity.Case;
import net.bakaar.sandbox.cas.domain.event.CaseCreated;
import net.bakaar.sandbox.cas.domain.repository.BusinessIdRepository;
import net.bakaar.sandbox.cas.domain.repository.CaseRepository;
import net.bakaar.sandbox.event.common.DomainEventEmitter;

public class CaseService implements CreateCaseUseCase {
    private final DomainEventEmitter eventEmitter;
    private final CaseRepository repository;
    private final CaseDomainObjectFactory factory;

    public CaseService(DomainEventEmitter eventEmitter,
                       CaseRepository caseRepository,
                       BusinessIdRepository businessIdRepository) {
        this.eventEmitter = eventEmitter;
        this.repository = caseRepository;
        this.factory = new CaseDomainObjectFactory(businessIdRepository);
    }

    @Override
    public Case createCase(String pnummer) {
        Case caseCreated = factory.createCase(pnummer);
        CaseCreated event = new CaseCreated(caseCreated.getId(), caseCreated.getInjured());
        eventEmitter.emit(event);
        return repository.save(caseCreated);
    }
}
