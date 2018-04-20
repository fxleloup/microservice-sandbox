package net.bakaar.sandbox.cas.domain;

import net.bakaar.sandbox.cas.domain.repository.CaseRepository;
import net.bakaar.sandbox.cas.domain.util.UUIDIdProvider;
import net.bakaar.sandbox.event.common.DomainEvent;
import net.bakaar.sandbox.event.common.DomainEventEmitter;
import org.junit.Test;

import static org.assertj.core.api.Assertions.assertThat;
import static org.mockito.ArgumentMatchers.any;
import static org.mockito.BDDMockito.given;
import static org.mockito.Mockito.mock;
import static org.mockito.Mockito.verify;

public class CaseServiceTest {

    private DomainEventEmitter emitter = mock(DomainEventEmitter.class);
    private CaseRepository repository = mock(CaseRepository.class);
    private CaseDomainObjectFactory factory = new CaseDomainObjectFactory(new UUIDIdProvider());
    private CaseService service = new CaseService(emitter, repository, factory);

    @Test
    public void createCase_should_return_a_case() {
        // Given
        String pnummer = "P1234566";
        given(repository.save(any(Case.class))).willAnswer(invocationOnMock -> invocationOnMock.getArgument(0));
        // When
        Case aCase = service.createCase(pnummer);
        // Then
        assertThat(aCase).isNotNull();
        assertThat(aCase.getPnummer()).isEqualTo(pnummer);
        verify(repository).save(aCase);
    }

    @Test
    public void createCase_should_emitt_an_event() {
        // Given
        // When
        service.createCase("P1234566");
        // Then
        verify(emitter).emit(any(DomainEvent.class));
    }
}