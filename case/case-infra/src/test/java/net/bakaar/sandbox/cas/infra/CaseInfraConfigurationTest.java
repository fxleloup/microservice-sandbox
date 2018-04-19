package net.bakaar.sandbox.cas.infra;

import net.bakaar.sandbox.cas.domain.CaseService;
import net.bakaar.sandbox.cas.domain.repository.CaseRepository;
import net.bakaar.sandbox.cas.infra.event.DBDomainEventEmitter;
import net.bakaar.sandbox.cas.infra.repository.CaseRepositoryAdapter;
import net.bakaar.sandbox.event.common.DomainEventEmitter;
import org.junit.Test;

import static org.assertj.core.api.Assertions.assertThat;
import static org.mockito.Mockito.mock;

public class CaseInfraConfigurationTest {

    private CaseInfraConfiguration configuration = new CaseInfraConfiguration();
    private DomainEventEmitter emitter = mock(DomainEventEmitter.class);
    private CaseRepository repository = mock(CaseRepository.class);

    @Test
    public void caseRespository_should_return_adapter() {
        assertThat(configuration.caseRepository()).isInstanceOf(CaseRepositoryAdapter.class);
    }

    @Test
    public void domainEventEmitter_should_return_emitter() {
        assertThat(configuration.domainEventEmitter()).isInstanceOf(DBDomainEventEmitter.class);
    }

    @Test
    public void caseService_should_return_service() {
        assertThat(configuration.caseService(emitter, repository)).isInstanceOf(CaseService.class);
    }

}