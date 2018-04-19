package net.bakaar.sandbox.cas.infra.repository;

import net.bakaar.sandbox.cas.domain.aggregate.Case;
import net.bakaar.sandbox.cas.infra.repository.jpa.JpaCaseRepository;
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
public class DBCaseRepositoryTest {

    @Mock
    private JpaCaseRepository jpaCaseRepository;
    @InjectMocks
    private DBCaseRepository dbCaseRepository;

    @Test
    public void save_should_returned_the_saved_case() {
        //Given
        String pnummer = "pnummer";
        Case aCase = new Case(pnummer);
        given(jpaCaseRepository.save(any(Case.class))).willAnswer(invocation -> invocation.getArgument(0));
        //When
        Case returnedCase = dbCaseRepository.save(aCase);
        //Then
        verify(jpaCaseRepository).save(aCase);
        assertThat(returnedCase).isNotNull().isEqualToComparingOnlyGivenFields(aCase, "pnummer");
    }
}