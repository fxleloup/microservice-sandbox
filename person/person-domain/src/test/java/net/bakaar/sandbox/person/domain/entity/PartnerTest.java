package net.bakaar.sandbox.person.domain.entity;

import junitparams.JUnitParamsRunner;
import junitparams.Parameters;
import net.bakaar.sandbox.shared.domain.vo.PNumber;
import org.junit.Test;
import org.junit.runner.RunWith;

import java.time.LocalDate;

import static org.assertj.core.api.Assertions.assertThat;
import static org.assertj.core.api.Assertions.catchThrowable;

@RunWith(JUnitParamsRunner.class)
public class PartnerTest {

    @Test
    public void of_should_set_all_the_mandatory_value() {
        //Given
        String name = "Kubrick";
        String forename = "Stanley";
        PNumber id = PNumber.of(12345678);
        LocalDate birthDate = LocalDate.of(1928, 7, 26);
        //When
        Partner createdPartner = Partner.of(id, name, forename, birthDate);
        //Then
        assertThat(createdPartner.getId()).isSameAs(id);
        assertThat(createdPartner.getName()).isEqualTo(name);
        assertThat(createdPartner.getForename()).isEqualTo(forename);
        assertThat(createdPartner.getBirthDate()).isEqualTo(birthDate);
    }

    @Test
    @Parameters(method = "parametersForMissingArguments")
    public void of_should_throw_exception_if_field_is_missing(PNumber id, String name, String forename, LocalDate birthdate, String... errors) {
        //Given
        //When
        Throwable thrown = catchThrowable(() -> Partner.of(id, name, forename, birthdate));
        //Then
        assertThat(thrown).isInstanceOf(IllegalArgumentException.class);
        assertThat(thrown.getMessage()).contains(errors);
    }

    private Object[] parametersForMissingArguments() {
        return new Object[][]{

                {null, "Kubrick", "Stanley", LocalDate.of(1928, 7, 26), "id"},
                {PNumber.of(12345678L), null, "Stanley", LocalDate.of(1928, 7, 26), "name"},
                {PNumber.of(12345678L), "Kubrick", null, LocalDate.of(1928, 7, 26), "forename"},
                {PNumber.of(12345678L), "", "Stanley", LocalDate.of(1928, 7, 26), "name"},
                {PNumber.of(12345678L), "Kubrick", "", LocalDate.of(1928, 7, 26), "forename"},
                {PNumber.of(12345678L), " ", "Stanley", LocalDate.of(1928, 7, 26), "name"},
                {PNumber.of(12345678L), "Kubrick", " ", LocalDate.of(1928, 7, 26), "forename"},
        };
    }
}