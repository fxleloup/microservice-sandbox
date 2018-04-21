package net.bakaar.sandbox.cas.infra;

import com.fasterxml.jackson.databind.ObjectMapper;
import net.bakaar.sandbox.cas.domain.CaseService;
import net.bakaar.sandbox.cas.domain.provider.BussinessIdProvider;
import net.bakaar.sandbox.cas.domain.repository.CaseRepository;
import net.bakaar.sandbox.cas.infra.event.db.DBDomainEventEmitter;
import net.bakaar.sandbox.cas.infra.event.db.EventRaisedFactory;
import net.bakaar.sandbox.cas.infra.event.db.EventRaisedRepository;
import net.bakaar.sandbox.cas.infra.repository.springdata.SpringDataCaseRepository;
import net.bakaar.sandbox.cas.infra.repository.springdata.SpringDataCaseRepositoryAdapter;
import net.bakaar.sandbox.event.common.DomainEventEmitter;
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
    private final EventRaisedFactory factory = new EventRaisedFactory(new ObjectMapper());

    @Test
    public void caseRespository_should_return_adapter() {
        assertThat(configuration.caseRepository(springDataCaseRepository)).isInstanceOf(SpringDataCaseRepositoryAdapter.class);
    }

    @Test
    public void domainEventEmitter_should_return_emitter() {
        assertThat(configuration.domainEventEmitter(eventRaisedRepository, factory)).isInstanceOf(DBDomainEventEmitter.class);
    }

    @Test
    public void caseService_should_return_service() {
        assertThat(configuration.caseService(emitter, repository, provider)).isInstanceOf(CaseService.class);
    }

}