package net.bakaar.sandbox.person.rest.config;

import net.bakaar.sandbox.person.data.rest.BusinessNumberService;
import org.springframework.boot.test.context.TestConfiguration;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Primary;

@TestConfiguration
public class PactTestConfiguration {

  @Bean
  @Primary
  public BusinessNumberService testNumberService() {
    return () -> 10987654L;
  }
}
