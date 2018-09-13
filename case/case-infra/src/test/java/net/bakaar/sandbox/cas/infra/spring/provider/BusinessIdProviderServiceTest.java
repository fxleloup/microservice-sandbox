package net.bakaar.sandbox.cas.infra.spring.provider;

import org.junit.Test;
import org.junit.runner.RunWith;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.junit.MockitoJUnitRunner;
import org.springframework.web.client.RestTemplate;

import java.util.UUID;

import static org.assertj.core.api.Assertions.assertThat;
import static org.mockito.BDDMockito.given;
import static org.mockito.Mockito.verify;

@RunWith(MockitoJUnitRunner.class)
public class BusinessIdProviderServiceTest {

    @Mock
    private BusinessIdServiceProperties properties;
    @Mock
    private RestTemplate restTemplate;

    @InjectMocks
    private BusinessIdProviderService service;

    @Test
    public void genrateId_should_call_the_bns_service() {
        //Given
        properties.setUrl("http://example.com/bns");
        given(restTemplate.getForObject(properties.getUrl(), UUID.class)).willReturn(UUID.randomUUID());
        //When
        String response = service.generateId();
        //Then
        verify(restTemplate).getForObject(properties.getUrl(), UUID.class);
        assertThat(response).isNotNull();

    }
}