package net.bakaar.sandbox.person.domain.entity;

import junitparams.JUnitParamsRunner;
import junitparams.Parameters;
import net.bakaar.sandbox.person.domain.entity.vo.PartnerId;
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
        LocalDate birthDate = LocalDate.of(1928,7,26);
        //When
        Partner createdPartner = Partner.of(1,name, forename, birthDate);
        //Then
        assertThat(createdPartner.getId()).isEqualToComparingFieldByField(PartnerId.of(1));
        assertThat(createdPartner.getName()).isEqualTo(name);
        assertThat(createdPartner.getForename()).isEqualTo(forename);
        assertThat(createdPartner.getBirthDate()).isEqualTo(birthDate);
    }

    @Test
    @Parameters(method = "parametersForMissingArguments")
    public void of_should_throw_exception_if_field_is_missing(String name, String forename, LocalDate birthdate, String ... errors) {
        //Given
        //When
        Throwable thrown = catchThrowable(() -> Partner.of(1L,name, forename, birthdate));
        //Then
        assertThat(thrown).isInstanceOf(IllegalArgumentException.class);
        assertThat(thrown.getMessage()).contains(errors);
    }

    private Object[] parametersForMissingArguments(){
        return new Object[][]{
                {"Kubrick", "Stanley", null, "birthDate"},
                {null, "Stanley", LocalDate.of(1928,7,26), "name"},
                {"Kubrick", null, LocalDate.of(1928,7,26), "forename"},
        };
    }
}