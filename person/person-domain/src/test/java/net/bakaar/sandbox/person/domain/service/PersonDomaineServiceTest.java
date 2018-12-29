package net.bakaar.sandbox.person.domain.service;

import net.bakaar.sandbox.person.domain.entity.Partner;
import net.bakaar.sandbox.person.domain.store.BusinessNumberStore;
import net.bakaar.sandbox.person.domain.store.PartnerStore;
import net.bakaar.sandbox.shared.domain.vo.PNumber;
import org.junit.Test;
import org.mockito.ArgumentMatchers;

import java.time.LocalDate;

import static org.assertj.core.api.Assertions.assertThat;
import static org.mockito.BDDMockito.given;
import static org.mockito.Mockito.mock;
import static org.mockito.Mockito.verify;

public class PersonDomaineServiceTest {

    @Test
    public void createPartner_should_create_and_call_number_service() {
        //Given
        PartnerStore partnerStore = mock(PartnerStore.class);
        given(partnerStore.push(ArgumentMatchers.any(Partner.class))).willAnswer(invocationOnMock -> invocationOnMock.getArgument(0));
        long id = 12345678L;
        BusinessNumberStore businessNumberStore = mock(BusinessNumberStore.class);
        given(businessNumberStore.createPartnerNumber()).willReturn(id);
        CreatePartnerUseCase service = new PersonDomaineService(partnerStore, businessNumberStore);
        //When
        Partner createdPartner = service.createPartner("Einstein", "Albert", LocalDate.of(1879, 3, 14));
        //Then
        assertThat(createdPartner).isNotNull();
        assertThat(createdPartner.getId()).isEqualToComparingFieldByField(PNumber.of(id));
        verify(partnerStore).push(ArgumentMatchers.any(Partner.class));
        verify(businessNumberStore).createPartnerNumber();
    }
}