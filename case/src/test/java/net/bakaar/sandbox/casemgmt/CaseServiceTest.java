package net.bakaar.sandbox.casemgmt;

import net.bakaar.sandbox.casemgmt.aggregate.Case;
import net.bakaar.sandbox.casemgmt.event.Event;
import net.bakaar.sandbox.casemgmt.event.EventEmitter;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.junit.MockitoJUnitRunner;

import static org.assertj.core.api.Assertions.assertThat;
import static org.mockito.ArgumentMatchers.any;
import static org.mockito.Mockito.verify;

@RunWith(MockitoJUnitRunner.class)
public class CaseServiceTest {

    @Mock
    private EventEmitter emitter;
    @InjectMocks
    private CaseService service;

    @Test
    public void createCase_should_return_a_case() {
        // Given
        String pnummer = "P1234566";
        // When
        Case aCase = service.createCase(pnummer);
        // Then
        assertThat(aCase).isNotNull();
        assertThat(aCase.getPnummer()).isEqualTo(pnummer);
    }

    @Test
    public void createCase_should_emitt_an_event() {
        // Given
        // When
        service.createCase("P1234566");
        // Then
        verify(emitter).emit(any(Event.class));
    }
}