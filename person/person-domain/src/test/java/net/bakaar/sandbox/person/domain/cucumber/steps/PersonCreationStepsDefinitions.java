package net.bakaar.sandbox.person.domain.cucumber.steps;

import cucumber.api.DataTable;
import cucumber.api.java8.En;
import net.bakaar.sandbox.person.domain.PersonDomaineService;
import net.bakaar.sandbox.person.domain.entity.Partner;
import net.bakaar.sandbox.person.domain.repository.BusinessNumberRepository;

import java.time.LocalDate;
import java.util.List;

import static java.lang.Integer.parseInt;
import static org.assertj.core.api.Assertions.assertThat;
import static org.assertj.core.api.Assertions.catchThrowable;
import static org.mockito.BDDMockito.given;
import static org.mockito.Mockito.mock;

public class PersonCreationStepsDefinitions implements En {

    private final BusinessNumberRepository businessNumberRepository = mock(BusinessNumberRepository.class);
    private final PersonDomaineService service = new PersonDomaineService(businessNumberRepository);
    private Partner createdPartner;
    private Throwable thrown;

    public PersonCreationStepsDefinitions() {
        When("^I create a partner with name \"?([^\"]*)\"? and forename \"?([^\"]*)\"? born the (\\d+)\\.(\\d+)\\.(\\d+)$", (String name, String forename, Integer day, Integer month, Integer year) -> {
            given(businessNumberRepository.featchPartnerNumber()).willReturn(12345678L);
            LocalDate birthDate;
            if (day == 0 && month == 0 && year == 0) {
                birthDate = null;
            } else {
                birthDate = LocalDate.of(year, month, day);
            }
            thrown = catchThrowable(() -> createdPartner = service.createPartner(name, forename, birthDate));
        });
        Then("^I sould receive a new partner with an attributed id$", () -> {
            assertThat(createdPartner).isNotNull();
            assertThat(createdPartner.getId()).isNotNull();
        });
        When("^I create a partner with the following data :$", (DataTable input) -> {
            given(businessNumberRepository.featchPartnerNumber()).willReturn(12345678L);
            List<String> table = input.asList(String.class);
            createdPartner = service.createPartner(table.get(0), table.get(1), convertToDate(table.get(2)));
        });
        Then("^I should receive an error mentionning that the info ([^\"]*) is missing$", (String fieldName) -> {
            assertThat(thrown).isNotNull();
            assertThat(thrown.getMessage()).containsIgnoringCase(fieldName);
        });
    }

    private LocalDate convertToDate(String toConvert) {
        String[] dateComponent = toConvert.split("\\.");
        return LocalDate.of(parseInt(dateComponent[2]), parseInt(dateComponent[1]), parseInt(dateComponent[0]));
    }
}
