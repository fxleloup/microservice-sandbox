package net.bakaar.sandbox.cas.infra.spring.controler;

import net.bakaar.sandbox.cas.domain.Case;
import net.bakaar.sandbox.cas.domain.CaseService;
import net.bakaar.sandbox.shared.domain.vo.PNummer;
import org.junit.Test;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;

import java.time.LocalDate;
import java.util.UUID;

import static org.assertj.core.api.Assertions.assertThat;
import static org.mockito.BDDMockito.given;
import static org.mockito.Mockito.mock;
import static org.mockito.Mockito.verify;

public class CaseResourceControlerTest {

    private CaseService service = mock(CaseService.class);
    private CaseResourceControler controler = new CaseResourceControler(service);

    @Test
    public void addNewCase_should_call_domainService() {
        //Given
        String pnummer = "P12345678";
        LocalDate birthDate = LocalDate.of(1981, 12, 16);
        CaseDTO caseDTO = new CaseDTO()
                .addPnummerInjured(pnummer)
                .addBirhtDateInjured(birthDate);
        given(service.createCase(pnummer)).willReturn(Case.builder()
                .withBusinnessId(UUID.randomUUID().toString())
                .withInjured(PNummer.of(pnummer)));
        //When
        ResponseEntity<CaseDTO> responseEntity = controler.addNewCase(caseDTO);
        //Then
        verify(service).createCase(caseDTO.getInjured().getPnummer());
        assertThat(responseEntity).isNotNull();
        assertThat(responseEntity.getStatusCode()).isEqualTo(HttpStatus.CREATED);
        assertThat(responseEntity.getBody()).isNotNull().isInstanceOf(CaseDTO.class);
    }
}