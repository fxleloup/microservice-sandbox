package net.bakaar.sandbox.person.rest.controller;

import net.bakaar.sandbox.person.rest.dto.PartnerDTO;
import net.bakaar.sandbox.person.rest.service.PersonRestService;
import net.bakaar.sandbox.shared.domain.vo.PNumber;
import org.junit.Test;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;

import static org.assertj.core.api.Assertions.assertThat;
import static org.mockito.BDDMockito.given;
import static org.mockito.Mockito.mock;
import static org.mockito.Mockito.verify;

public class PartnerRestControllerTest {

    private final PersonRestService service = mock(PersonRestService.class);
    private final PartnerRestController controller = new PartnerRestController(service);
    private final PartnerDTO returnedDto = mock(PartnerDTO.class);

    @Test
    public void create_should_call_service() {
        //Given
        PartnerDTO partner = mock(PartnerDTO.class);
        given(service.createPartner(partner)).willReturn(returnedDto);
        //When
        ResponseEntity<PartnerDTO> response = controller.create(partner);
        //Then
        verify(service).createPartner(partner);
        assertThat(response.getStatusCode()).isEqualTo(HttpStatus.CREATED);
        assertThat(response.getBody()).isSameAs(returnedDto);
    }

    @Test
    public void fetchAPartnerById_should_return_the_correct_partner() {
        //Given
        long id = 45678909L;
        PNumber pNumber = PNumber.of(id);
        given(service.fetchPartnerById(pNumber)).willReturn(returnedDto);
        //When
        PartnerDTO dto = controller.readAPartner("P" + id);
        //Then
        assertThat(dto).isNotNull().isSameAs(returnedDto);
    }
}
