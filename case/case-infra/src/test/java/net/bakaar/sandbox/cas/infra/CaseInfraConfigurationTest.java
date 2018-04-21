package net.bakaar.sandbox.cas.infra;

import com.fasterxml.jackson.databind.ObjectMapper;
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
import org.junit.Test;

import static org.assertj.core.api.Assertions.assertThat;
import static org.mockito.Mockito.mock;

public class CaseInfraConfigurationTest {

    private CaseInfraConfiguration configuration = new CaseInfraConfiguration();
    private DomainEventEmitter emitter = mock(DomainEventEmitter.class);
    private CaseRepository repository = mock(CaseRepository.class);
    private EventRaisedRepository eventRaisedRepository = mock(EventRaisedRepository.class);
    private SpringDataCaseRepository springDataCaseRepository = mock(SpringDataCaseRepository.class);
    private BussinessIdProvider provider = mock(BussinessIdProvider.class);
    private final ObjectMapper mapper = new ObjectMapper();
    private final DBEventRaisedFactory factory = new DBEventRaisedFactory(mapper);

    @Test
    public void caseRespository_should_return_adapter() {
        assertThat(configuration.caseRepository(springDataCaseRepository)).isInstanceOf(SpringDataCaseRepositoryAdapter.class);
    }

    @Test
    public void dbDomainEventEmitter_should_return_emitter() {
        assertThat(configuration.dbDomainEventEmitter(eventRaisedRepository, factory)).isInstanceOf(DBDomainEventEmitter.class);
    }

    @Test
    public void caseService_should_return_service() {
        assertThat(configuration.caseService(emitter, repository, provider)).isInstanceOf(CaseService.class);
    }

    @Test
    public void eventRaisedFactory_should_return_the_factory() {
        assertThat(configuration.eventRaisedFactory(mapper)).isInstanceOf(DBEventRaisedFactory.class);
    }

    @Test
    public void inMemoryDomainEventEmitter_should_return_emitter() {
        assertThat(configuration.inMemoryDomainEventemitter()).isInstanceOf(InMemoryDomainEventEmitter.class);
    }

    //TODO BusinessIdProvider
}