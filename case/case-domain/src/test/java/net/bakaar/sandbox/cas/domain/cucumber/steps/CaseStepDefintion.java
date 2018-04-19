package net.bakaar.sandbox.cas.domain.cucumber.steps;

import cucumber.api.java8.En;
import net.bakaar.sandbox.cas.domain.CaseService;
import net.bakaar.sandbox.cas.domain.aggregate.Case;
import net.bakaar.sandbox.cas.domain.event.CaseCreated;
import net.bakaar.sandbox.cas.domain.repository.CaseRepository;
import net.bakaar.sandbox.event.common.DomainEventEmitter;
import org.mockito.ArgumentCaptor;

import static org.assertj.core.api.Assertions.assertThat;
import static org.assertj.core.api.Assertions.catchThrowable;
import static org.mockito.ArgumentMatchers.any;
import static org.mockito.BDDMockito.given;
import static org.mockito.Mockito.mock;
import static org.mockito.Mockito.verify;

public class CaseStepDefintion implements En {

    private DomainEventEmitter publisher = mock(DomainEventEmitter.class);
    private CaseRepository repository = mock(CaseRepository.class);
    private CaseService service = new CaseService(publisher, repository);
    private ArgumentCaptor<CaseCreated> eventArgumentCaptor = ArgumentCaptor.forClass(CaseCreated.class);
    private Case aCase;

    public CaseStepDefintion() {

        When("^we create a case with a Partner number (.+)$", (String pnummer) -> {
            given(repository.save(any(Case.class))).willAnswer(invocation -> invocation.getArgument(0));
            Throwable throwable = catchThrowable(() -> aCase = this.service.createCase(pnummer));
            verify(repository).save(any(Case.class));
            verify(publisher).emit(eventArgumentCaptor.capture());
            assertThat(throwable).isNull();
        });

        Then("^an Event mentioning the new case is emitted$", () -> {
            CaseCreated event = eventArgumentCaptor.getValue();
            assertThat(event.getId()).isEqualTo(aCase.getId());
            assertThat(event.getPnummer()).isEqualTo(aCase.getPnummer());
        });

        Then("^this Case should have an id$", () -> {
            assertThat(aCase.getId()).isNotNull();
        });
    }
}
