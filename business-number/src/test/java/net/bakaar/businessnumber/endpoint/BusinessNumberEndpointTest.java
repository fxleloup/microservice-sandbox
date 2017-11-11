package net.bakaar.businessnumber.endpoint;

import org.junit.Test;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;

import java.util.UUID;

import static org.assertj.core.api.Assertions.assertThat;

public class BusinessNumberEndpointTest {

    @Test
    public void getNumber_should_return_200_and_a_UUID() throws Exception {
        //Given
        BusinessNumberEndpoint endpoint = new BusinessNumberEndpoint();
        //When
        ResponseEntity response = endpoint.getBusinessNumber();
        //Then
        assertThat(response.getStatusCode()).isEqualTo(HttpStatus.OK);
        assertThat(response.getBody()).isInstanceOf(UUID.class);
    }
}