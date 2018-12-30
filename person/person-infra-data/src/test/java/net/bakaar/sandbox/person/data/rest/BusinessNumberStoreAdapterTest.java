package net.bakaar.sandbox.person.data.rest;

import net.bakaar.sandbox.shared.domain.vo.PNumber;
import org.junit.Test;
import org.springframework.web.client.RestTemplate;

import static org.assertj.core.api.Assertions.assertThat;
import static org.mockito.BDDMockito.given;
import static org.mockito.Mockito.mock;
import static org.mockito.Mockito.verify;

public class BusinessNumberStoreAdapterTest {

    @Test
    public void fetch_should_call_rest_endpoint() {
        //Given
        long result = 98765432L;
        String baseUrl = "Http://TestUrl.net";
        String completeUrl = baseUrl + "/rest/api/v1/business-number?type=partner";
        RestTemplate restTemplate = mock(RestTemplate.class);
        given(restTemplate.getForObject(completeUrl, Long.class)).willReturn(result);
        BusinessNumberServiceProperties properties = mock(BusinessNumberServiceProperties.class);
        given(properties.getUrl()).willReturn(baseUrl);
        BusinessNumberStoreAdapter client = new BusinessNumberStoreAdapter(properties, restTemplate);
        //When
        PNumber number = client.createPartnerNumber();
        //Then
        verify(restTemplate).getForObject(completeUrl, Long.class);
        verify(properties).getUrl();
        assertThat(number.getValue()).isEqualTo(result);
    }
}