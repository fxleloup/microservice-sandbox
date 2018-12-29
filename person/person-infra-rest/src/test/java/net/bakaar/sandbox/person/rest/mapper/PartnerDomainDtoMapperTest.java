package net.bakaar.sandbox.person.rest.mapper;

import net.bakaar.sandbox.person.domain.entity.Partner;
import net.bakaar.sandbox.person.rest.dto.PartnerDTO;
import net.bakaar.sandbox.shared.domain.vo.PNumber;
import org.junit.Test;

import java.time.LocalDate;

import static org.assertj.core.api.Assertions.assertThat;

public class PartnerDomainDtoMapperTest {

    private final long id = 19876543L;
    private final String name = "Bloch";
    private final String forename = "Joshua";
    private final LocalDate birthDate = LocalDate.now();
    private PartnerDomainDtoMapper mapper = new PartnerDomainDtoMapper();

    @Test
    public void mapToDto_should_map_correctly() {
        //Given
        Partner inputPartner = Partner.of(id, name, forename, birthDate);
        //When
        PartnerDTO dto = mapper.mapToDto(inputPartner);
        //Then
        assertThat(dto).isNotNull();
        assertThat(dto.getForename()).isEqualTo(forename);
        assertThat(dto.getId()).isEqualTo("P" + id);
        assertThat(dto.getBirthDate()).isSameAs(birthDate);
        assertThat(dto.getName()).isEqualTo(name);
    }

    @Test
    public void mapToDomain_should_map_correctly() {
        //Given
        PartnerDTO dto = new PartnerDTO();
        dto.setBirthDate(birthDate);
        dto.setForename(forename);
        dto.setName(name);
        dto.setId("P" + id);
        //When
        Partner domain = mapper.mapToDomain(dto);
        //Then
        assertThat(domain).isNotNull();
        assertThat(domain.getBirthDate()).isSameAs(birthDate);
        assertThat(domain.getName()).isEqualTo(name);
        assertThat(domain.getForename()).isEqualTo(forename);
        assertThat(domain.getId()).isEqualToComparingFieldByField(PNumber.of(id));
    }
}