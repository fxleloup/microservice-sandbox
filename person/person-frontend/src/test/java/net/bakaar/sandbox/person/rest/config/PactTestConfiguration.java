package net.bakaar.sandbox.person.rest.config;

import net.bakaar.sandbox.person.domain.store.BusinessNumberStore;
import org.springframework.boot.test.context.TestConfiguration;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Primary;

@TestConfiguration
public class PactTestConfiguration {

  @Bean
  @Primary
  public BusinessNumberStore testNumberService() {
    return () -> 10987654L;
  }
}
