package net.bakaar.sandbox.person.domain;

import net.bakaar.sandbox.person.domain.entity.Partner;
import net.bakaar.sandbox.person.domain.repository.PartnerStore;
import net.bakaar.sandbox.shared.domain.vo.PNummer;
import org.junit.Test;

import java.time.LocalDate;

import static org.assertj.core.api.Assertions.assertThat;
import static org.mockito.ArgumentMatchers.any;
import static org.mockito.BDDMockito.given;
import static org.mockito.Mockito.mock;
import static org.mockito.Mockito.verify;

public class PersonDomaineServiceTest {

    @Test
    public void createPartner_should_create_and_call_number_service() {
        //Given
        PartnerStore partnerStore = mock(PartnerStore.class);
        given(partnerStore.push(any(Partner.class))).willAnswer(invocationOnMock -> invocationOnMock.getArgument(0));
        PersonDomaineService service = new PersonDomaineService(partnerStore);
        long id = 12345678L;
        //When
        Partner createdPartner = service.createPartner(id, "Einstein", "Albert", LocalDate.of(1879, 3, 14));
        //Then
        assertThat(createdPartner).isNotNull();
        assertThat(createdPartner.getId()).isEqualToComparingFieldByField(PNummer.of(id));
        verify(partnerStore).push(any(Partner.class));
    }
}