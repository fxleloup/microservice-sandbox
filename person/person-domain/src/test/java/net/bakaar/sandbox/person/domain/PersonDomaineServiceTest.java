package net.bakaar.sandbox.person.domain;

import net.bakaar.sandbox.person.domain.entity.Partner;
import net.bakaar.sandbox.person.domain.entity.vo.PartnerId;
import net.bakaar.sandbox.person.domain.repository.BusinessNumberRepository;
import org.junit.Test;

import java.time.LocalDate;

import static org.assertj.core.api.Assertions.assertThat;
import static org.mockito.BDDMockito.given;
import static org.mockito.Mockito.mock;
import static org.mockito.Mockito.verify;

public class PersonDomaineServiceTest {

    @Test
    public void createPartner_should_create_and_call_number_service() {
        //Given
        BusinessNumberRepository businessNumberRepository = mock(BusinessNumberRepository.class);
        long id = 1234567890L;
        given(businessNumberRepository.featchPartnerNumber()).willReturn(id);
        PersonDomaineService service = new PersonDomaineService(businessNumberRepository);
        //When
        Partner createdPartner =  service.createPartner("Einstein", "Albert", LocalDate.of(1879, 3, 14));
        //Then
        verify(businessNumberRepository).featchPartnerNumber();
        assertThat(createdPartner).isNotNull();
        assertThat(createdPartner.getId()).isEqualToComparingFieldByField(PartnerId.of(id));
    }
}