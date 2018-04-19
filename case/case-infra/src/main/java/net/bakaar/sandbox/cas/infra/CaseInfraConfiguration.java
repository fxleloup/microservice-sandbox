package net.bakaar.sandbox.cas.infra;

import net.bakaar.sandbox.cas.domain.CaseService;
import net.bakaar.sandbox.cas.domain.repository.CaseRepository;
import net.bakaar.sandbox.cas.infra.event.DBDomainEventEmitter;
import net.bakaar.sandbox.cas.infra.repository.DBCaseRepository;
import net.bakaar.sandbox.cas.infra.repository.jpa.JpaCaseRepository;
import net.bakaar.sandbox.event.common.DomainEventEmitter;

public class CaseInfraConfiguration {
    public CaseRepository caseRepository(JpaCaseRepository repository) {
        return new DBCaseRepository(repository);
    }

    public CaseService caseService(DomainEventEmitter emitter, CaseRepository repository) {
        return new CaseService(emitter, repository);
    }

    public DomainEventEmitter domainEventEmitter() {
        return new DBDomainEventEmitter();
    }
}
