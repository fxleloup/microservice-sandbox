package net.bakaar.sandbox.casemgmt.cucumber.steps;

import cucumber.api.java8.En;
import net.bakaar.sandbox.casemgmt.CaseService;
import net.bakaar.sandbox.casemgmt.aggregate.Case;
import net.bakaar.sandbox.casemgmt.cucumber.util.DummyEventEmmitter;
import net.bakaar.sandbox.casemgmt.event.CaseCreated;
import net.bakaar.sandbox.casemgmt.event.EventEmitter;

import static org.assertj.core.api.Assertions.assertThat;


public class CaseStepDefintion implements En {

    private final EventEmitter emitter = new DummyEventEmmitter();
    private final CaseService service = new CaseService(emitter);
    private Case caseCreated;

    public CaseStepDefintion() {
        When("^we create a case with a Partner number (.+)$", (String pnummer) -> {
            caseCreated = this.service.createCase(pnummer);
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
