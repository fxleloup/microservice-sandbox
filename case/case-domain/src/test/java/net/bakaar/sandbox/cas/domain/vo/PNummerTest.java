package net.bakaar.sandbox.cas.domain.vo;

import nl.jqno.equalsverifier.EqualsVerifier;
import org.junit.Test;

import static org.assertj.core.api.Assertions.assertThat;
import static org.assertj.core.api.Assertions.catchThrowable;
import static org.springframework.test.util.ReflectionTestUtils.getField;

public class PNummerTest {

    @Test
    public void of_should_create_a_new_PNummer() {
        //Given
        String pnummer = "P12345678";
        //When
        PNummer created = PNummer.of(pnummer);
        //Then
        assertThat(created).isNotNull();
    }

    @Test
    public void should_only_store_number() {
        //Given
        String pnummer = "P12345678";
        //When
        PNummer created = PNummer.of(pnummer);
        //Then
        assertThat((Integer) getField(created, "value")).isEqualTo(12345678);
    }

    @Test
    public void of_should_throw_exception_arg_null() {
        //Given
        //When
        Throwable thrown = catchThrowable(() -> PNummer.of(null));
        //Then
        assertThat(thrown).isInstanceOf(IllegalArgumentException.class);
        assertThat(thrown.getMessage()).contains("null");
    }

    @Test
    public void of_should_throw_exception_if_pattern_wrong() {
        //Given
        //When
        Throwable thrown = catchThrowable(() -> PNummer.of("jhd31"));
        //Then
        assertThat(thrown).isInstanceOf(IllegalArgumentException.class);
        assertThat(thrown.getMessage()).contains("PNummer should follow the pattern P[0-9]{8}");
    }

    @Test
    public void format_should_return_in_good_pattern() {
        //Given
        PNummer pNummer = PNummer.of("P12345678");
        //When
        String formated = pNummer.format();
        //Then
        assertThat(formated).matches("P[0-9]{8}");
    }

    @Test
    public void equalsContract() {
        EqualsVerifier.forClass(PNummer.class)
                .verify();
    }
}