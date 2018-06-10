package net.bakaar.sandbox.cas.infra.spring;

import com.fasterxml.jackson.databind.ObjectMapper;
import com.fasterxml.jackson.datatype.jsr310.JavaTimeModule;
import net.bakaar.sandbox.cas.domain.CaseDomainObjectFactory;
import net.bakaar.sandbox.cas.domain.CaseService;
import net.bakaar.sandbox.cas.domain.provider.BusinessIdProvider;
import net.bakaar.sandbox.cas.domain.repository.CaseRepository;
import net.bakaar.sandbox.event.common.DomainEventEmitter;
import net.bakaar.sandbox.event.inmemory.InMemoryDomainEventEmitter;
import org.junit.Test;

import java.util.Set;

import static org.assertj.core.api.Assertions.assertThat;
import static org.mockito.Mockito.mock;
import static org.springframework.test.util.ReflectionTestUtils.getField;

public class CaseInfraConfigurationTest {

    private CaseInfraConfiguration configuration = new CaseInfraConfiguration();

    @Test
    public void objectMapper_should_configure_correctly() {
        //Given
        //When
        ObjectMapper mapper = configuration.objectMapper();
        //Then
        assertThat(mapper).isNotNull();
        assertThat((Set<Object>) getField(mapper, "_registeredModuleTypes")).contains(new JavaTimeModule().getTypeId());
    }

    @Test
    public void caseService_should_returned_a_configured_one() {
        //Given
        DomainEventEmitter emitter = mock(DomainEventEmitter.class);
        CaseRepository repository = mock(CaseRepository.class);
        BusinessIdProvider provider = mock(BusinessIdProvider.class);
        //When
        CaseService service = configuration.caseService(emitter, repository, provider);
        //Then
        assertThat(service).isInstanceOf(CaseService.class);
        assertThat(getField(service, "repository")).isEqualTo(repository);
        assertThat(getField(service, "eventEmitter")).isEqualTo(emitter);
        CaseDomainObjectFactory factory = (CaseDomainObjectFactory) getField(service, "factory");
        assertThat(factory).isNotNull();
        assertThat(getField(factory, "businessIdProvider")).isEqualTo(provider);
    }

    @Test
    public void domainEventEmitter_should_configure_emitter() {
        //When
        DomainEventEmitter emitter = configuration.domainEventEmitter();
        //Then
        assertThat(emitter).isInstanceOf(InMemoryDomainEventEmitter.class);
    }


}