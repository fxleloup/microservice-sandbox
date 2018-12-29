package net.bakaar.sandbox.person.data.rest;

import net.bakaar.sandbox.person.domain.repository.BusinessNumberRepository;
import org.springframework.web.client.RestTemplate;

public class BusinessNumberRepositoryAdapter implements BusinessNumberRepository {
    private final BusinessNumberServiceProperties properties;
    private final RestTemplate restTemplate;

    public BusinessNumberRepositoryAdapter(BusinessNumberServiceProperties properties, RestTemplate restTemplate) {
        this.properties = properties;
        this.restTemplate = restTemplate;
    }

    @Override
    public long createPartnerNumber() {
        return restTemplate.getForObject(properties.getUrl(), Long.class);
    }
}
