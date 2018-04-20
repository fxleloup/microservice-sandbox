package net.bakaar.sandbox.cas.infra;

import net.bakaar.sandbox.cas.domain.CaseService;
import net.bakaar.sandbox.cas.domain.repository.CaseRepository;
import net.bakaar.sandbox.cas.infra.event.db.DBDomainEventEmitter;
import net.bakaar.sandbox.cas.infra.event.db.EventRaisedRepository;
import net.bakaar.sandbox.cas.infra.repository.springdata.SpringDataCaseRepository;
import net.bakaar.sandbox.cas.infra.repository.springdata.SpringDataCaseRepositoryAdapter;
import net.bakaar.sandbox.event.common.DomainEventEmitter;

public class CaseInfraConfiguration {
    public CaseRepository caseRepository(SpringDataCaseRepository repository) {
        return new SpringDataCaseRepositoryAdapter(repository);
    }

    public CaseService caseService(DomainEventEmitter emitter, CaseRepository repository) {
        return new CaseService(emitter, repository);
    }

    public DomainEventEmitter domainEventEmitter(EventRaisedRepository repository) {
        return new DBDomainEventEmitter(repository);
    }
}
