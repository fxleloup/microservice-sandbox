package net.bakaar.sandbox.cas.domain;

import net.bakaar.sandbox.cas.domain.event.CaseCreated;
import net.bakaar.sandbox.cas.domain.provider.BusinessIdProvider;
import net.bakaar.sandbox.cas.domain.repository.CaseRepository;
import net.bakaar.sandbox.cas.domain.vo.PNummer;
import net.bakaar.sandbox.event.common.DomainEventEmitter;
import org.junit.Test;
import org.mockito.ArgumentCaptor;

import java.util.UUID;

import static org.assertj.core.api.Assertions.assertThat;
import static org.mockito.ArgumentMatchers.any;
import static org.mockito.BDDMockito.given;
import static org.mockito.Mockito.mock;
import static org.mockito.Mockito.verify;

public class CaseServiceTest {

    private DomainEventEmitter emitter = mock(DomainEventEmitter.class);
    private CaseRepository repository = mock(CaseRepository.class);
    private BusinessIdProvider businessIdProvider = mock(BusinessIdProvider.class);
    private CaseDomainObjectFactory factory = new CaseDomainObjectFactory(businessIdProvider);
    private CaseService service = new CaseService(emitter, repository, factory);

    @Test
    public void createCase_should_return_a_case() {
        // Given
        String pnummer = "P12345678";
        given(repository.save(any(Case.class))).willAnswer(invocationOnMock -> invocationOnMock.getArgument(0));
        given(businessIdProvider.generateId()).willReturn(UUID.randomUUID().toString());
        // When
        Case aCase = service.createCase(pnummer);
        // Then
        assertThat(aCase).isNotNull();
        assertThat(aCase.getPnummer()).isEqualTo(PNummer.of(pnummer));
        verify(repository).save(aCase);
    }

    @Test
    public void createCase_should_emitt_an_event() {
        // Given
        String pnummer = "P12345678";
        given(repository.save(any(Case.class))).willAnswer(invocationOnMock -> invocationOnMock.getArgument(0));
        given(businessIdProvider.generateId()).willReturn(UUID.randomUUID().toString());
        // When
        Case aCase = service.createCase(pnumme);
        // Then
        ArgumentCaptor<CaseCreated> captor = ArgumentCaptor.forClass(CaseCreated.class);
        verify(emitter).emit(captor.capture());
        CaseCreated eventEmitted = captor.getValue();
        assertThat(eventEmitted.getId()).isEqualTo(aCase.getId());
        assertThat(eventEmitted.getPnummer()).isEqualTo(aCase.getPnummer());

    }
}