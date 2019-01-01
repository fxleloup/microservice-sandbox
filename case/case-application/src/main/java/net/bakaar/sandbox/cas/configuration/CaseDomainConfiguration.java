package net.bakaar.sandbox.cas.configuration;

import net.bakaar.sandbox.cas.domain.CaseService;
import net.bakaar.sandbox.cas.domain.CreateCaseUseCase;
import net.bakaar.sandbox.cas.domain.repository.BusinessIdRepository;
import net.bakaar.sandbox.cas.domain.repository.CaseRepository;
import net.bakaar.sandbox.event.domain.EventStore;
import net.bakaar.sandbox.event.inmemory.InMemoryEventStore;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;

@Configuration
public class CaseDomainConfiguration {

    @Bean
    public EventStore eventStore() {
        return new InMemoryEventStore();
    }

    @Bean
    public CreateCaseUseCase createCaseUseCase(EventStore eventStore,
                                               CaseRepository caseRepository,
                                               BusinessIdRepository businessIdRepository) {
        return new CaseService(eventStore, caseRepository, businessIdRepository);
    }
}
