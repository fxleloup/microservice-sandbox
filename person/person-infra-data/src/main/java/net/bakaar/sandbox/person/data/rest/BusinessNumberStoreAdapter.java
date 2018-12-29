package net.bakaar.sandbox.person.data.rest;

import net.bakaar.sandbox.person.domain.store.BusinessNumberStore;
import org.springframework.web.client.RestTemplate;

public class BusinessNumberStoreAdapter implements BusinessNumberStore {
    private final BusinessNumberServiceProperties properties;
    private final RestTemplate restTemplate;

    public BusinessNumberStoreAdapter(BusinessNumberServiceProperties properties, RestTemplate restTemplate) {
        this.properties = properties;
        this.restTemplate = restTemplate;
    }

    @Override
    public long createPartnerNumber() {
        return restTemplate.getForObject(properties.getUrl(), Long.class);
    }
}
