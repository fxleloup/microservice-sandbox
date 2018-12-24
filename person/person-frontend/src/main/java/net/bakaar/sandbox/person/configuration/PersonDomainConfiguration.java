package net.bakaar.sandbox.person.configuration;

import net.bakaar.sandbox.person.domain.repository.PartnerStore;
import net.bakaar.sandbox.person.domain.service.CreatePartnerUseCase;
import net.bakaar.sandbox.person.domain.service.PersonDomaineService;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;

@Configuration
public class PersonDomainConfiguration {

  @Bean
  public CreatePartnerUseCase createPartnerUseCase(PartnerStore store) {
    return new PersonDomaineService(store);
  }


}
