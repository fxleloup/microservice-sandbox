package net.bakaar.sandbox.cas.domain;

import net.bakaar.sandbox.cas.domain.aggregate.Case;
import net.bakaar.sandbox.cas.domain.event.CaseCreated;
import net.bakaar.sandbox.cas.domain.repository.CaseRepository;
import net.bakaar.sandbox.event.publisher.DomainEventPublisher;

public class CaseService {
    private final DomainEventPublisher publisher;
    private final CaseRepository repository;

    public CaseService(DomainEventPublisher publisher, CaseRepository repository) {
        this.publisher = publisher;
        this.repository = repository;
    }

    public Case createCase(String pnummer) {
        Case caseCreated = new Case(pnummer);
        CaseCreated event = new CaseCreated(caseCreated.getId(), caseCreated.getPnummer());
        publisher.publish(event);
        return repository.save(caseCreated);
    }
}
