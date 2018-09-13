package net.bakaar.sandbox.cas.infra.spring.provider;

import net.bakaar.sandbox.cas.domain.provider.BusinessIdProvider;
import org.springframework.boot.context.properties.EnableConfigurationProperties;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.web.client.RestTemplate;

@Configuration
@EnableConfigurationProperties({BusinessIdServiceProperties.class})
public class BusinessIdConfiguration {

    @Bean
    public BusinessIdProvider businessIdProvider(BusinessIdServiceProperties properties, RestTemplate restTemplate) {
        return new BusinessIdProviderService(properties, restTemplate);
    }

    @Bean
    public RestTemplate restTemplate() {
        return new RestTemplate();
    }

}
