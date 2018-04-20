package net.bakaar.sandbox.cas.infra.repository.springdata;

import net.bakaar.sandbox.cas.domain.aggregate.Case;
import org.junit.Test;

import static org.assertj.core.api.Assertions.assertThat;

public class CaseEntityTest {

    @Test
    public void fromCase_should_create_entity_with_case_values() {
        //Given
        String pnummer = "pNummer";
        Case aCase = new Case(pnummer);
        //When
        CaseEntity entity = CaseEntity.fromCase(aCase);
        //Then
        assertThat(entity.getPnummer()).isEqualTo(pnummer);
        assertThat(entity.getBusinessId()).isEqualTo(aCase.getId());
    }
}