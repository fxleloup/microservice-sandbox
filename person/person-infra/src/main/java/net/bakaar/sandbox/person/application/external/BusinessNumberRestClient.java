package net.bakaar.sandbox.person.application.external;

import org.springframework.web.client.RestTemplate;

public class BusinessNumberRestClient implements BusinessNumberService {
    private final BusinessNumberServiceProperties properties;
    private final RestTemplate restTemplate;

    public BusinessNumberRestClient(BusinessNumberServiceProperties properties, RestTemplate restTemplate) {
        this.properties = properties;
        this.restTemplate = restTemplate;
    }

    @Override
    public long fetchPartnerNummer() {
        return restTemplate.getForObject(properties.getUrl(), Long.class);
    }
}
