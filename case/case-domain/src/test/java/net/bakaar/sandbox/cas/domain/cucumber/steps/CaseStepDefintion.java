package net.bakaar.sandbox.cas.domain.cucumber.steps;

import cucumber.api.java8.En;
import net.bakaar.sandbox.cas.domain.CaseService;
import net.bakaar.sandbox.cas.domain.aggregate.Case;
import net.bakaar.sandbox.cas.domain.cucumber.repository.DummyCaseRepository;
import net.bakaar.sandbox.cas.domain.cucumber.repository.DummyEventEmmitter;
import net.bakaar.sandbox.cas.domain.event.CaseCreated;
import net.bakaar.sandbox.cas.domain.repository.CaseRepository;
import net.bakaar.sandbox.cas.domain.repository.EventEmitter;

import static org.assertj.core.api.Assertions.assertThat;
import static org.assertj.core.api.Assertions.catchThrowable;


public class CaseStepDefintion implements En {

    private final EventEmitter emitter = new DummyEventEmmitter();
    private final CaseRepository repository = new DummyCaseRepository();
    private final CaseService service = new CaseService(emitter, repository);
    private Case caseCreated;

    public CaseStepDefintion() {
        When("^we create a case with a Partner number (.+)$", (String pnummer) -> {
            Throwable throwable = catchThrowable(() -> caseCreated = this.service.createCase(pnummer));
            assertThat(throwable).isNull();
        });

        Then("^an Event mentioning the new case is emitted$", () -> {
            assertThat(emitter).isInstanceOf(DummyEventEmmitter.class);
            CaseCreated event = (CaseCreated) ((DummyEventEmmitter) emitter).getEvent();
            assertThat(event.getId()).isEqualTo(caseCreated.getId());
            assertThat(event.getPnummer()).isEqualTo(caseCreated.getPnummer());
        });

        Then("^this Case should have an id$", () -> {
            assertThat(caseCreated.getId()).isNotNull();
        });
    }
}
