package net.bakaar.sandbox.cas.infra;

import com.fasterxml.jackson.databind.ObjectMapper;
import com.fasterxml.jackson.databind.SerializationFeature;
import com.fasterxml.jackson.datatype.jsr310.JavaTimeModule;
import net.bakaar.sandbox.cas.domain.CaseDomainObjectFactory;
import net.bakaar.sandbox.cas.domain.CaseService;
import net.bakaar.sandbox.cas.domain.provider.BusinessIdProvider;
import net.bakaar.sandbox.cas.domain.repository.CaseRepository;
import net.bakaar.sandbox.cas.infra.repository.springdata.CaseEntity;
import net.bakaar.sandbox.cas.infra.repository.springdata.SpringDataCaseRepository;
import net.bakaar.sandbox.event.common.DomainEventEmitter;
import net.bakaar.sandbox.event.inmemory.InMemoryDomainEventEmitter;
import org.springframework.boot.autoconfigure.domain.EntityScan;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.ComponentScan;
import org.springframework.context.annotation.Configuration;
import org.springframework.data.jpa.repository.config.EnableJpaRepositories;

import java.util.UUID;

@Configuration
@EnableJpaRepositories(basePackageClasses = {SpringDataCaseRepository.class})
@EntityScan(basePackageClasses = {CaseEntity.class})
@ComponentScan(basePackageClasses = {CaseInfraConfiguration.class})
public class CaseInfraConfiguration {

    @Bean
    public ObjectMapper objectMapper() {
        ObjectMapper mapper = new ObjectMapper();
        mapper.registerModule(new JavaTimeModule());
        mapper.disable(SerializationFeature.WRITE_DATES_AS_TIMESTAMPS);
        return mapper;
    }

    @Bean
    public CaseService caseService(DomainEventEmitter emitter,
                                   CaseRepository repository,
                                   BusinessIdProvider provider) {
        return new CaseService(emitter, repository, new CaseDomainObjectFactory(provider));
    }

    @Bean
    public DomainEventEmitter domainEventEmitter() {
        return new InMemoryDomainEventEmitter();
    }

    @Bean
    public BusinessIdProvider businessIdProvider() {
        return () -> UUID.randomUUID().toString();
    }
}
