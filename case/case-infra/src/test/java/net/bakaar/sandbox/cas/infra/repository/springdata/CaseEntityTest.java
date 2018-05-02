package net.bakaar.sandbox.cas.infra.repository.springdata;

import net.bakaar.sandbox.cas.domain.Case;
import org.junit.Test;

import java.time.LocalDate;
import java.util.UUID;

import static org.assertj.core.api.Assertions.assertThat;

public class CaseEntityTest {

    @Test
    public void fromCase_should_create_entity_with_case_values() {
        //Given
        String pnummer = "pNummer";
        LocalDate birthDate = LocalDate.now();
        String id = UUID.randomUUID().toString();
        Case aCase = new Case(id, pnummer, birthDate);
        //When
        CaseEntity entity = CaseEntity.fromCase(aCase);
        //Then
        assertThat(entity.getPnummer()).isEqualTo(pnummer);
        assertThat(entity.getBusinessId()).isEqualTo(aCase.getId());
        assertThat(entity.getBirthDate()).isEqualTo(birthDate);
    }

    @Test
    public void toCase_should_change_entity_to_case() {
        //Given
        String pnummer = "pNummer";
        String id = UUID.randomUUID().toString();
        CaseEntity caseEntity = new CaseEntity().setBusinessId(id).setPnummer(pnummer);
        //When
        Case aCase = caseEntity.toCase();
        //Then
        assertThat(aCase.getPnummer()).isEqualTo(pnummer);
        assertThat(aCase.getId()).isEqualTo(id);
    }
}