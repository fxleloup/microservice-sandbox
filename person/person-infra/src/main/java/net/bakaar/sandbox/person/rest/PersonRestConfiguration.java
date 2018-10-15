package net.bakaar.sandbox.person.rest;

import net.bakaar.sandbox.person.application.external.BusinessNumberRestClient;
import net.bakaar.sandbox.person.application.external.BusinessNumberService;
import net.bakaar.sandbox.person.application.external.BusinessNumberServiceProperties;
import net.bakaar.sandbox.person.application.mapper.PartnerDomainDtoMapper;
import net.bakaar.sandbox.person.application.service.DefaultPersonApplicationService;
import net.bakaar.sandbox.person.application.service.PersonApplicationService;
import net.bakaar.sandbox.person.domain.PersonDomaineService;
import net.bakaar.sandbox.person.domain.repository.PartnerStore;
import net.bakaar.sandbox.person.rest.controller.PartnerRestController;
import org.springframework.boot.context.properties.EnableConfigurationProperties;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.ComponentScan;
import org.springframework.context.annotation.Configuration;
import org.springframework.web.client.RestTemplate;

@Configuration
@EnableConfigurationProperties(BusinessNumberServiceProperties.class)
@ComponentScan(basePackageClasses = PartnerRestController.class)
public class PersonRestConfiguration {

    @Bean
    public PersonApplicationService applicationService(PartnerStore partnerStore, BusinessNumberService numberService) {
        return new DefaultPersonApplicationService(new PersonDomaineService(partnerStore), numberService, new PartnerDomainDtoMapper());
    }

    @Bean
    public BusinessNumberService businessNumberService(BusinessNumberServiceProperties properties, RestTemplate restTemplate) {
        return new BusinessNumberRestClient(properties, restTemplate);
    }

}
