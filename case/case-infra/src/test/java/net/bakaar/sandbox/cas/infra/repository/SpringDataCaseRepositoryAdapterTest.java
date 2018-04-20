package net.bakaar.sandbox.cas.infra.repository;

import net.bakaar.sandbox.cas.domain.Case;
import net.bakaar.sandbox.cas.infra.repository.springdata.CaseEntity;
import net.bakaar.sandbox.cas.infra.repository.springdata.SpringDataCaseRepository;
import net.bakaar.sandbox.cas.infra.repository.springdata.SpringDataCaseRepositoryAdapter;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.mockito.ArgumentCaptor;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.junit.MockitoJUnitRunner;

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
        Case aCase = new Case(bussinessIdProvider.generateId(), pnummer);
        given(springDataCaseRepository.save(any(CaseEntity.class))).willAnswer(invocation -> invocation.getArgument(0));
        //When
        Case returnedCase = springDataCaseRepositoryAdapter.save(aCase);
        //Then
        ArgumentCaptor<CaseEntity> caseEntityArgumentCaptor = ArgumentCaptor.forClass(CaseEntity.class);
        verify(springDataCaseRepository).save(caseEntityArgumentCaptor.capture());
        assertThat(returnedCase).isNotNull().isEqualToComparingOnlyGivenFields(aCase, "pnummer", "id");
        CaseEntity entity = caseEntityArgumentCaptor.getValue();
        assertThat(entity).isNotNull();
    }
}