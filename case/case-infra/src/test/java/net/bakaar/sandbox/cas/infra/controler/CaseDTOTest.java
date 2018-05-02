package net.bakaar.sandbox.cas.infra.controler;

import org.junit.Test;

import java.time.LocalDate;

import static org.assertj.core.api.Assertions.assertThat;

public class CaseDTOTest {

    private CaseDTO dto = new CaseDTO();

    @Test
    public void setPNummerInjured_should_set_pnummer_in_injured() {
        //Given
        String pnummer = "PNummer";
        //When
        dto.addPnummerInjured(pnummer);
        //Then
        assertThat(dto.getInjured().getPnummer()).isEqualTo(pnummer);
    }

    @Test
    public void setBirthDateInjured_should_set_birthDate_in_injured() {
        //Given
        LocalDate birthDate = LocalDate.now();
        //When
        dto.addBirhtDateInjured(birthDate);
        //Then
        assertThat(dto.getInjured().getBirthDate()).isEqualTo(birthDate);
    }
}