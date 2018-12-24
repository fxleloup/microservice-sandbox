package net.bakaar.sandbox.person.rest.controller;

import net.bakaar.sandbox.person.rest.dto.PartnerDTO;
import net.bakaar.sandbox.person.rest.service.PersonRestService;
import org.junit.Test;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;

import static org.assertj.core.api.Assertions.assertThat;
import static org.mockito.BDDMockito.given;
import static org.mockito.Mockito.mock;
import static org.mockito.Mockito.verify;

public class PartnerRestControllerTest {

    @Test
    public void create_should_call_service() {
        //Given
        PersonRestService service = mock(PersonRestService.class);
        PartnerRestController controller = new PartnerRestController(service);
        PartnerDTO partner = mock(PartnerDTO.class);
        PartnerDTO returnedPartner = mock(PartnerDTO.class);
        given(service.createPartner(partner)).willReturn(returnedPartner);
        //When
        ResponseEntity<PartnerDTO> response = controller.create(partner);
        //Then
        verify(service).createPartner(partner);
        assertThat(response.getStatusCode()).isEqualTo(HttpStatus.CREATED);
        assertThat(response.getBody()).isSameAs(returnedPartner);
    }
}
