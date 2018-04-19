package net.bakaar.sandbox.cas.infra.repository;

import net.bakaar.sandbox.cas.domain.aggregate.Case;
import org.junit.Test;

import static org.assertj.core.api.Assertions.assertThat;

public class CaseRepositoryAdapterTest {

    private CaseRepositoryAdapter adapter = new CaseRepositoryAdapter();

    @Test
    public void save_should_returned_the_saved_case() {
        //Given
        String pnummer = "pnummer";
        Case aCase = new Case(pnummer);
        //When
        Case returnedCase = adapter.save(aCase);
        //Then
        assertThat(returnedCase).isNotNull().isEqualToComparingOnlyGivenFields(aCase, "pnummer");
    }
}