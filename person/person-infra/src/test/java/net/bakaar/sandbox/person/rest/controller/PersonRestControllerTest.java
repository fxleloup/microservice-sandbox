package net.bakaar.sandbox.person.rest.controller;

import net.bakaar.sandbox.person.application.service.PersonApplicationService;
import net.bakaar.sandbox.person.rest.vo.PartnerDTO;
import org.junit.Test;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;

import static org.assertj.core.api.Assertions.assertThat;
import static org.mockito.BDDMockito.given;
import static org.mockito.Mockito.mock;
import static org.mockito.Mockito.verify;

public class PersonRestControllerTest {

    @Test
    public void create_should_call_application_service() {
        //Given
        PersonApplicationService applicationService = mock(PersonApplicationService.class);
        PersonRestController controller = new PersonRestController(applicationService);
        PartnerDTO partner = mock(PartnerDTO.class);
        PartnerDTO returnedPartner = mock(PartnerDTO.class);
        given(applicationService.createPartner(partner)).willReturn(returnedPartner);
        //When
        ResponseEntity<PartnerDTO> response = controller.create(partner);
        //Then
        verify(applicationService).createPartner(partner);
        assertThat(response.getStatusCode()).isEqualTo(HttpStatus.OK);
        assertThat(response.getBody()).isSameAs(returnedPartner);
    }
}
