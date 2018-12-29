package net.bakaar.sandbox.person.configuration;

import net.bakaar.sandbox.person.domain.service.CreatePartnerUseCase;
import net.bakaar.sandbox.person.domain.service.PersonDomaineService;
import net.bakaar.sandbox.person.domain.store.BusinessNumberStore;
import net.bakaar.sandbox.person.domain.store.PartnerStore;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;

@Configuration
public class PersonDomainConfiguration {

  @Bean
  public CreatePartnerUseCase createPartnerUseCase(PartnerStore store, BusinessNumberStore businessNumberStore) {
    return new PersonDomaineService(store, businessNumberStore);
  }


}
