package net.bakaar.sandbox.cas.infra.spring.provider;

import org.springframework.boot.test.context.TestConfiguration;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Import;

@TestConfiguration
@Import(BusinessIdConfiguration.class)
public class BusinessIdProviderTestConfiguration {

    @Bean
    public BusinessIdServiceProperties properties() {
        BusinessIdServiceProperties properties = new BusinessIdServiceProperties();
        properties.setUrl("http://localhost:8080/business-number");
        return properties;
    }


}
