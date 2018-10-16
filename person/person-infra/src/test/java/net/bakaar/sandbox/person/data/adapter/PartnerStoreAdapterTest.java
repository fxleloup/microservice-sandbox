package net.bakaar.sandbox.person.data.adapter;

import net.bakaar.sandbox.person.data.entity.PersonEntity;
import net.bakaar.sandbox.person.data.mapper.PartnerEntityDomainMapper;
import net.bakaar.sandbox.person.data.repository.PersonRepository;
import net.bakaar.sandbox.person.domain.entity.Partner;
import org.junit.Test;

import static org.assertj.core.api.Assertions.assertThat;
import static org.mockito.ArgumentMatchers.any;
import static org.mockito.BDDMockito.given;
import static org.mockito.Mockito.mock;
import static org.mockito.Mockito.verify;

public class PartnerStoreAdapterTest {

    @Test
    public void push_should_store_partner_in_db() {
        //Given
        Partner input = mock(Partner.class);
        Partner returned = mock(Partner.class);
        PartnerEntityDomainMapper mapper = mock(PartnerEntityDomainMapper.class);
        PersonEntity entity = mock(PersonEntity.class);
        PersonRepository repository = mock(PersonRepository.class);
        given(repository.save(any(PersonEntity.class))).willAnswer(invocation -> invocation.getArgument(0));
        given(mapper.mapToDomain(entity)).willReturn(returned);
        given(mapper.mapToEntity(input)).willReturn(entity);
        PartnerStoreAdapter adapter = new PartnerStoreAdapter(repository, mapper);
        //When
        Partner partner = adapter.push(input);
        //Then
        verify(mapper).mapToEntity(input);
        verify(repository).save(entity);
        verify(mapper).mapToDomain(entity);
        assertThat(partner).isSameAs(returned);
    }
}