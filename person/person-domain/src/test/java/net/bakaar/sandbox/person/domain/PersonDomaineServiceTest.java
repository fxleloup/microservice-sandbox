package net.bakaar.sandbox.person.domain;

import net.bakaar.sandbox.person.domain.entity.Partner;
import net.bakaar.sandbox.person.domain.repository.BusinessNumberRepository;
import net.bakaar.sandbox.shared.domain.vo.PNummer;
import org.junit.Test;

import java.time.LocalDate;

import static org.assertj.core.api.Assertions.assertThat;
import static org.mockito.BDDMockito.given;
import static org.mockito.Mockito.*;

public class PersonDomaineServiceTest {

    @Test
    public void createPartner_should_create_and_call_number_service() {
        //Given
        BusinessNumberRepository businessNumberRepository = mock(BusinessNumberRepository.class);
        long id = 12345678L;
        given(businessNumberRepository.fetchPartnerNumber()).willReturn(id);
        PersonDomaineService service = new PersonDomaineService(businessNumberRepository);
        //When
        Partner createdPartner = spy(service.createPartner("Einstein", "Albert", LocalDate.of(1879, 3, 14)));
        //Then
        verify(businessNumberRepository).fetchPartnerNumber();
        assertThat(createdPartner).isNotNull();
        assertThat(createdPartner.getId()).isEqualToComparingFieldByField(PNummer.of(id));
    }
}