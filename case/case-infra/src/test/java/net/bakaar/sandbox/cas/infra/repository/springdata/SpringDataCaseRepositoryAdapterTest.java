package net.bakaar.sandbox.cas.infra.repository.springdata;

import net.bakaar.sandbox.cas.domain.Case;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.mockito.ArgumentCaptor;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.junit.MockitoJUnitRunner;

import java.time.LocalDate;
import java.util.UUID;

import static org.assertj.core.api.Assertions.assertThat;
import static org.mockito.ArgumentMatchers.any;
import static org.mockito.BDDMockito.given;
import static org.mockito.Mockito.verify;

@RunWith(MockitoJUnitRunner.class)
public class SpringDataCaseRepositoryAdapterTest {

    @Mock
    private SpringDataCaseRepository springDataCaseRepository;
    @InjectMocks
    private SpringDataCaseRepositoryAdapter springDataCaseRepositoryAdapter;

    @Test
    public void save_should_returned_the_saved_case() {
        //Given
        String pnummer = "pnummer";
        LocalDate birthDate = LocalDate.now();
        String id = UUID.randomUUID().toString();
        Case aCase = new Case(id, pnummer, birthDate);
        given(springDataCaseRepository.save(any(CaseEntity.class))).willAnswer(invocation -> invocation.getArgument(0));
        //When
        Case returnedCase = springDataCaseRepositoryAdapter.save(aCase);
        //Then
        ArgumentCaptor<CaseEntity> caseEntityArgumentCaptor = ArgumentCaptor.forClass(CaseEntity.class);
        verify(springDataCaseRepository).save(caseEntityArgumentCaptor.capture());
        assertThat(returnedCase).isNotNull()
                .isEqualToComparingOnlyGivenFields(aCase, "pnummer", "birthDate", "id");
        CaseEntity entity = caseEntityArgumentCaptor.getValue();
        assertThat(entity).isNotNull();
    }
}