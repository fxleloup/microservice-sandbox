package net.bakaar.sandbox.cas.infra.repository.springdata;

import net.bakaar.sandbox.cas.domain.Case;
import net.bakaar.sandbox.cas.domain.vo.PNummer;
import org.junit.Test;

import java.util.UUID;

import static org.assertj.core.api.Assertions.assertThat;

public class CaseEntityTest {

    @Test
    public void fromCase_should_create_entity_with_case_values() {
        //Given
        String pnummer = "P12345678";
        String id = UUID.randomUUID().toString();
        Case aCase = new Case(id, PNummer.of(pnummer));
        //When
        CaseEntity entity = CaseEntity.fromCase(aCase);
        //Then
        assertThat(entity.getPnummer()).isEqualTo(pnummer);
        assertThat(entity.getBusinessId()).isEqualTo(aCase.getId());
    }

    @Test
    public void toCase_should_change_entity_to_case() {
        //Given
        String pnummer = "P12345678";
        String id = UUID.randomUUID().toString();
        CaseEntity caseEntity = new CaseEntity().setBusinessId(id).setPnummer(pnummer);
        //When
        Case aCase = caseEntity.toCase();
        //Then
        assertThat(aCase.getInjured()).isEqualTo(PNummer.of(pnummer));
        assertThat(aCase.getId()).isEqualTo(id);
    }
}