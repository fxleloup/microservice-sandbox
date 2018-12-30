package net.bakaar.sandbox.cas.domain;

import net.bakaar.sandbox.cas.domain.entity.Case;
import net.bakaar.sandbox.cas.domain.event.CaseCreated;
import net.bakaar.sandbox.cas.domain.repository.BusinessIdRepository;
import net.bakaar.sandbox.cas.domain.repository.CaseRepository;
import net.bakaar.sandbox.event.domain.EventStore;
import net.bakaar.sandbox.shared.domain.vo.PNumber;
import org.junit.Test;
import org.mockito.ArgumentCaptor;

import java.util.UUID;

import static org.assertj.core.api.Assertions.assertThat;
import static org.mockito.ArgumentMatchers.any;
import static org.mockito.BDDMockito.given;
import static org.mockito.Mockito.mock;
import static org.mockito.Mockito.verify;

public class CaseServiceTest {

    private EventStore emitter = mock(EventStore.class);
    private CaseRepository repository = mock(CaseRepository.class);
    private BusinessIdRepository businessIdRepository = mock(BusinessIdRepository.class);
    private CaseService service = new CaseService(emitter, repository, businessIdRepository);

    @Test
    public void createCase_should_return_a_case() {
        // Given
        String pnummer = "P12345678";
        given(repository.save(any(Case.class))).willAnswer(invocationOnMock -> invocationOnMock.getArgument(0));
        given(businessIdRepository.generateId()).willReturn(UUID.randomUUID().toString());
        // When
        Case aCase = service.createCase(pnummer);
        // Then
        assertThat(aCase).isNotNull();
        assertThat(aCase.getInjured()).isEqualTo(PNumber.of(pnummer));
        verify(repository).save(aCase);
    }

    @Test
    public void createCase_should_emitt_an_event() {
        // Given
        String pnummer = "P12345678";
        given(repository.save(any(Case.class))).willAnswer(invocationOnMock -> invocationOnMock.getArgument(0));
        given(businessIdRepository.generateId()).willReturn(UUID.randomUUID().toString());
        // When
        Case aCase = service.createCase(pnummer);
        // Then
        ArgumentCaptor<CaseCreated> captor = ArgumentCaptor.forClass(CaseCreated.class);
        verify(emitter).store(captor.capture());
        CaseCreated eventEmitted = captor.getValue();
        assertThat(eventEmitted.getId()).isEqualTo(aCase.getId());
        assertThat(eventEmitted.getPNumber()).isEqualTo(aCase.getInjured());

    }
}