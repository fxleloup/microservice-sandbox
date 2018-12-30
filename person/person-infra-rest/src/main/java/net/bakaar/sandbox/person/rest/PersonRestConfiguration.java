package net.bakaar.sandbox.person.rest;

import net.bakaar.sandbox.person.domain.service.CreatePartnerUseCase;
import net.bakaar.sandbox.person.rest.controller.PartnerRestController;
import net.bakaar.sandbox.person.rest.mapper.PartnerDomainDtoMapper;
import net.bakaar.sandbox.person.rest.repository.PartnerReadStore;
import net.bakaar.sandbox.person.rest.service.PersonRestService;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.ComponentScan;
import org.springframework.context.annotation.Configuration;

@Configuration
@ComponentScan(basePackageClasses = PartnerRestController.class)
public class PersonRestConfiguration {

    @Bean
    public PersonRestService restService(CreatePartnerUseCase partnerUseCase, PartnerReadStore partnerReadStore) {
        return new PersonRestService(partnerUseCase, partnerReadStore, new PartnerDomainDtoMapper());
    }
}
