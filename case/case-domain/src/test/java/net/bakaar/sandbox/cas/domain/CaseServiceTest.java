package net.bakaar.sandbox.cas.domain;

import net.bakaar.sandbox.cas.domain.aggregate.Case;
import net.bakaar.sandbox.cas.domain.repository.CaseRepository;
import net.bakaar.sandbox.event.common.DomainEvent;
import net.bakaar.sandbox.event.jpa.repository.DomainEventRaisedRepository;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.junit.MockitoJUnitRunner;

import static org.assertj.core.api.Assertions.assertThat;
import static org.mockito.ArgumentMatchers.any;
import static org.mockito.BDDMockito.given;
import static org.mockito.Mockito.verify;

@RunWith(MockitoJUnitRunner.class)
public class CaseServiceTest {

    @Mock
    private DomainEventRaisedRepository publisher;
    @Mock
    private CaseRepository repository;
    @InjectMocks
    private CaseService service;

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
        verify(publisher).save(any(DomainEvent.class));
    }
}