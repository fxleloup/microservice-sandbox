package net.bakaar.sandbox.cas.configuration;

import net.bakaar.sandbox.cas.domain.CaseService;
import net.bakaar.sandbox.cas.domain.CreateCaseUseCase;
import net.bakaar.sandbox.cas.domain.repository.BusinessIdRepository;
import net.bakaar.sandbox.cas.domain.repository.CaseRepository;
import net.bakaar.sandbox.event.common.DomainEventEmitter;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;

@Configuration
public class CaseDomainConfiguration {

    @Bean
    public CreateCaseUseCase createCaseUseCase(DomainEventEmitter eventEmmitter,
                                               CaseRepository caseRepository,
                                               BusinessIdRepository businessIdRepository) {
        return new CaseService(eventEmmitter, caseRepository, businessIdRepository);
    }
}
