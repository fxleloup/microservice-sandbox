package net.bakaar.sandbox.cas.infra.spring.provider;

import au.com.dius.pact.consumer.Pact;
import au.com.dius.pact.consumer.PactProviderRuleMk2;
import au.com.dius.pact.consumer.PactVerification;
import au.com.dius.pact.consumer.dsl.PactDslJsonRootValue;
import au.com.dius.pact.consumer.dsl.PactDslWithProvider;
import au.com.dius.pact.model.RequestResponsePact;
import org.junit.Rule;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.test.context.ContextConfiguration;
import org.springframework.test.context.junit4.SpringRunner;

import java.util.regex.Pattern;

import static org.assertj.core.api.Assertions.assertThat;

@RunWith(SpringRunner.class)
@ContextConfiguration(classes = {BusinessIdProviderTestConfiguration.class})
public class BusinessIdProviderServiceIT {

    @Rule
    public PactProviderRuleMk2 mockProvider = new PactProviderRuleMk2("businessId-provider", "localhost", 8080, this);
    @Autowired
    private BusinessIdProviderService service;

    @Pact(provider = "businessId-provider", consumer = "case-service")
    public RequestResponsePact createPactContract(PactDslWithProvider builder) {
        return builder
                .uponReceiving("Get New BusinessID")
                .path("/business-number")
                .method("GET")
                .willRespondWith()
                .status(200)
                .body(PactDslJsonRootValue.uuid())
                .toPact();
    }

    @Test
    @PactVerification("businessId-provider")
    public void businessIdProvider_should_call_businesIdService() {
        //Given
        //When
        //Then
        assertThat(service.generateId()).matches(Pattern.compile("^[0-9a-fA-F]{8}-[0-9a-fA-F]{4}-[0-9a-fA-F]{4}-[0-9a-fA-F]{4}-[0-9a-fA-F]{12}$"));
    }
}