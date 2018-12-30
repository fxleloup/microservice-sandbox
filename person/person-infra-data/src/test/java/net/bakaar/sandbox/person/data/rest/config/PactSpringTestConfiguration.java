package net.bakaar.sandbox.person.data.rest.config;

import net.bakaar.sandbox.person.data.rest.BusinessNumberServiceProperties;
import net.bakaar.sandbox.person.data.rest.BusinessNumberStoreAdapter;
import net.bakaar.sandbox.person.domain.store.BusinessNumberStore;
import org.springframework.boot.test.context.TestConfiguration;
import org.springframework.context.annotation.Bean;
import org.springframework.web.client.RestTemplate;

@TestConfiguration
public class PactSpringTestConfiguration {

    public static final String TEST_URL = "http://localhost:8090/bns";

    @Bean
    public RestTemplate restTemplate() {
        return new RestTemplate();
    }

    @Bean
    public BusinessNumberStore businessNumberService(RestTemplate restTemplate) {
        BusinessNumberServiceProperties properties = new BusinessNumberServiceProperties();
        properties.setUrl(TEST_URL);
        return new BusinessNumberStoreAdapter(properties, restTemplate);
    }

}
