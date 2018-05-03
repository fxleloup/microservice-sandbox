package net.bakaar.sandbox.cas.domain;

import net.bakaar.sandbox.cas.domain.vo.PNummer;
import org.junit.Test;

import static org.assertj.core.api.Assertions.assertThat;

public class CaseTest {

    @Test
    public void builder_should_make_case() {
        //Given
        String id = "ID";
        PNummer pNummer = PNummer.of("P12345678");
        //When
        Case aCase = Case.builder().withBusinnessId(id).withInjured(pNummer);
        //Then
        assertThat(aCase).isNotNull();
        assertThat(aCase.getId()).isEqualTo(id);
        assertThat(aCase.getInjured()).isEqualTo(pNummer);
    }
}