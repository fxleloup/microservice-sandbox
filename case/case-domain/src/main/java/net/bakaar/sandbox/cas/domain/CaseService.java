package net.bakaar.sandbox.cas.domain;

import net.bakaar.sandbox.cas.domain.entity.Case;
import net.bakaar.sandbox.cas.domain.event.CaseCreated;
import net.bakaar.sandbox.cas.domain.repository.BusinessIdRepository;
import net.bakaar.sandbox.cas.domain.repository.CaseRepository;
import net.bakaar.sandbox.event.domain.EventStore;

public class CaseService implements CreateCaseUseCase {
    private final EventStore eventStore;
    private final CaseRepository repository;
    private final CaseDomainObjectFactory factory;

    public CaseService(EventStore eventStore,
                       CaseRepository caseRepository,
                       BusinessIdRepository businessIdRepository) {
        this.eventStore = eventStore;
        this.repository = caseRepository;
        this.factory = new CaseDomainObjectFactory(businessIdRepository);
    }

    @Override
    public Case createCase(String pnummer) {
        Case caseCreated = factory.createCase(pnummer);
        CaseCreated event = new CaseCreated(caseCreated.getId(), caseCreated.getInjured());
        eventStore.store(event);
        return repository.save(caseCreated);
    }
}
