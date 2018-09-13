package net.bakaar.sandbox.cas.infra.spring.provider;

import net.bakaar.sandbox.cas.domain.provider.BusinessIdProvider;
import org.springframework.web.client.RestTemplate;

import java.util.UUID;

public class BusinessIdProviderService implements BusinessIdProvider {

    private BusinessIdServiceProperties properties;
    private RestTemplate restTemplate;

    public BusinessIdProviderService(BusinessIdServiceProperties properties, RestTemplate restTemplate) {
        this.properties = properties;
        this.restTemplate = restTemplate;
    }

    @Override
    public String generateId() {
        return restTemplate.getForObject(properties.getUrl(), UUID.class).toString();
    }
}
