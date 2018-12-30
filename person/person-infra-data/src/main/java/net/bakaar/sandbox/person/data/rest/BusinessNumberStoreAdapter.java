package net.bakaar.sandbox.person.data.rest;

import net.bakaar.sandbox.person.domain.store.BusinessNumberStore;
import net.bakaar.sandbox.shared.domain.vo.PNumber;
import org.springframework.web.client.RestTemplate;

public class BusinessNumberStoreAdapter implements BusinessNumberStore {
    private final BusinessNumberServiceProperties properties;
    private final RestTemplate restTemplate;

    private static final String API_URL = "rest/api/v1/business-number";

    public BusinessNumberStoreAdapter(BusinessNumberServiceProperties properties, RestTemplate restTemplate) {
        this.properties = properties;
        this.restTemplate = restTemplate;
    }

    @Override
    public PNumber createPartnerNumber() {
        String url = String.format("%s/%s?type=partner", properties.getUrl(), API_URL);
        return PNumber.of(restTemplate.getForObject(url, Long.class));
    }
}
