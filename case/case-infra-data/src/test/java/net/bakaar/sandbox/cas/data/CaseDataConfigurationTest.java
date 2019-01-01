package net.bakaar.sandbox.cas.data;

import com.fasterxml.jackson.databind.ObjectMapper;
import com.fasterxml.jackson.datatype.jsr310.JavaTimeModule;
import net.bakaar.sandbox.cas.data.rest.BusinessIdRepositoryAdapter;
import net.bakaar.sandbox.cas.data.rest.BusinessIdServiceProperties;
import net.bakaar.sandbox.cas.domain.repository.BusinessIdRepository;
import org.junit.Test;
import org.springframework.web.client.RestTemplate;

import java.util.Set;

import static org.assertj.core.api.Assertions.assertThat;
import static org.mockito.Mockito.mock;
import static org.springframework.test.util.ReflectionTestUtils.getField;

public class CaseDataConfigurationTest {

    private CaseDataConfiguration configuration = new CaseDataConfiguration();

    @Test
    public void objectMapper_should_configure_correctly() {
        //Given
        //When
        ObjectMapper mapper = configuration.objectMapper();
        //Then
        assertThat(mapper).isNotNull();
        assertThat((Set<Object>) getField(mapper, "_registeredModuleTypes")).contains(new JavaTimeModule().getTypeId());
    }

    @Test
    public void businessIdProvider_should_configure_provider() {
        //Given
        BusinessIdServiceProperties properties = mock(BusinessIdServiceProperties.class);
        RestTemplate restTemplate = mock(RestTemplate.class);
        //When
        BusinessIdRepository provider = configuration.businessIdProvider(properties, restTemplate);
        //Then
        assertThat(provider).isInstanceOf(BusinessIdRepositoryAdapter.class);
        assertThat(getField(provider, "properties")).isEqualTo(properties);
        assertThat(getField(provider, "restTemplate")).isEqualTo(restTemplate);
    }
}
