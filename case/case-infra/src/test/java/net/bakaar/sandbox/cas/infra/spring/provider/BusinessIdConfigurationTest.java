package net.bakaar.sandbox.cas.infra.spring.provider;

import net.bakaar.sandbox.cas.domain.provider.BusinessIdProvider;
import org.junit.Test;
import org.springframework.web.client.RestTemplate;

import static org.assertj.core.api.Assertions.assertThat;
import static org.mockito.Mockito.mock;
import static org.springframework.test.util.ReflectionTestUtils.getField;

public class BusinessIdConfigurationTest {

    private BusinessIdConfiguration configuration = new BusinessIdConfiguration();

    @Test
    public void businessIdProvider_should_configure_provider() {
        //Given
        BusinessIdServiceProperties properties = mock(BusinessIdServiceProperties.class);
        RestTemplate restTemplate = mock(RestTemplate.class);
        //When
        BusinessIdProvider provider = configuration.businessIdProvider(properties, restTemplate);
        //Then
        assertThat(provider).isInstanceOf(BusinessIdProviderService.class);
        assertThat(getField(provider, "properties")).isEqualTo(properties);
        assertThat(getField(provider, "restTemplate")).isEqualTo(restTemplate);
    }

    @Test
    public void restTemplate_should_set_a_new_one_if_not_exists() {
        //Given
        RestTemplate returned = configuration.restTemplate();
        //Then
        assertThat(returned).isNotNull();
    }
}