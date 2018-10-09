package net.bakaar.sandbox.person.application.service;

import net.bakaar.sandbox.person.application.external.BusinessNumberService;
import net.bakaar.sandbox.person.application.mapper.PartnerDomainDtoMapper;
import net.bakaar.sandbox.person.domain.PersonDomaineService;
import net.bakaar.sandbox.person.domain.entity.Partner;
import net.bakaar.sandbox.person.rest.vo.PartnerDTO;
import org.junit.Test;

import java.time.LocalDate;

import static org.assertj.core.api.Assertions.assertThat;
import static org.mockito.ArgumentMatchers.any;
import static org.mockito.ArgumentMatchers.eq;
import static org.mockito.BDDMockito.given;
import static org.mockito.Mockito.mock;
import static org.mockito.Mockito.verify;

public class DefaultPersonApplicationServiceTest {

    @Test
    public void createPartner_should_call_domain_service() {
        //Given
        long id = 98765432L;
        Partner mockedPartner = mock(Partner.class);
        PersonDomaineService domainService = mock(PersonDomaineService.class);
        given(domainService.createPartner(eq(id), any(), any(), any())).willReturn(mockedPartner);

        BusinessNumberService businessNumberService = mock(BusinessNumberService.class);
        given(businessNumberService.fetchPartnerNummer()).willReturn(id);

        PartnerDomainDtoMapper mapper = mock(PartnerDomainDtoMapper.class);
        PartnerDTO mockedDto = mock(PartnerDTO.class);
        given(mapper.mapToDto(mockedPartner)).willReturn(mockedDto);

        PersonApplicationService service = new DefaultPersonApplicationService(domainService, businessNumberService, mapper);

        PartnerDTO input = new PartnerDTO();
        LocalDate birthDate = LocalDate.of(1981, 12, 16);
        input.setBirthDate(birthDate);
        String forename = "forename";
        input.setForename(forename);
        String name = "name";
        input.setName(name);
        //When
        PartnerDTO dto = service.createPartner(input);
        //Then
        verify(businessNumberService).fetchPartnerNummer();
        verify(domainService).createPartner(id, name, forename, birthDate);
        verify(mapper).mapToDto(mockedPartner);
        assertThat(dto).isNotNull().isSameAs(mockedDto);
    }
}