package net.bakaar.sandbox.cas.infra;

import com.fasterxml.jackson.databind.ObjectMapper;
import net.bakaar.sandbox.cas.domain.CaseDomainObjectFactory;
import net.bakaar.sandbox.cas.domain.CaseService;
import net.bakaar.sandbox.cas.domain.provider.BussinessIdProvider;
import net.bakaar.sandbox.cas.domain.repository.CaseRepository;
import net.bakaar.sandbox.cas.infra.repository.springdata.SpringDataCaseRepository;
import net.bakaar.sandbox.cas.infra.repository.springdata.SpringDataCaseRepositoryAdapter;
import net.bakaar.sandbox.event.common.DomainEventEmitter;
import net.bakaar.sandbox.event.db.DBDomainEventEmitter;
import net.bakaar.sandbox.event.db.DBEventRaisedFactory;
import net.bakaar.sandbox.event.db.EventRaisedRepository;
import net.bakaar.sandbox.event.inmemory.InMemoryDomainEventEmitter;

public class CaseInfraConfiguration {
    public CaseRepository caseRepository(SpringDataCaseRepository repository) {
        return new SpringDataCaseRepositoryAdapter(repository);
    }

    public CaseService caseService(DomainEventEmitter emitter,
                                   CaseRepository repository,
                                   BussinessIdProvider provider) {
        return new CaseService(emitter, repository, new CaseDomainObjectFactory(provider));
    }

    public DomainEventEmitter dbDomainEventEmitter(EventRaisedRepository repository, DBEventRaisedFactory factory) {
        return new DBDomainEventEmitter(repository, factory);
    }

    public DBEventRaisedFactory eventRaisedFactory(ObjectMapper mapper) {
        return new DBEventRaisedFactory(mapper);
    }

    public DomainEventEmitter inMemoryDomainEventemitter() {
        return new InMemoryDomainEventEmitter();
    }
}
