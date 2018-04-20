package net.bakaar.sandbox.cas.domain;

import net.bakaar.sandbox.cas.domain.provider.BussinessIdProvider;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.junit.MockitoJUnitRunner;

import java.util.UUID;

import static org.assertj.core.api.Assertions.assertThat;
import static org.mockito.BDDMockito.given;
import static org.mockito.Mockito.verify;

@RunWith(MockitoJUnitRunner.class)
public class CaseDomainObjectFactoryTest {

    @Mock
    private BussinessIdProvider bussinessIdProvider;
    @InjectMocks
    private CaseDomainObjectFactory factory;

    @Test
    public void createCase_should_create_a_case_with_an_id() {
        //Given
        String pnummer = "pnummer";
        String id = UUID.randomUUID().toString();
        given(bussinessIdProvider.generateId()).willReturn(id);
        //When
        Case aCase = factory.createCase(pnummer);
        //Then
        verify(bussinessIdProvider).generateId();
        assertThat(aCase).isNotNull();
        assertThat(aCase.getId()).isEqualTo(id);
        assertThat(aCase.getPnummer()).isEqualTo(pnummer);
    }
}