package net.bakaar.sandbox.person.data.jpa.adapter;

import net.bakaar.sandbox.person.data.jpa.mapper.PartnerEntityDomainMapper;
import net.bakaar.sandbox.person.data.jpa.repository.PersonRepository;
import net.bakaar.sandbox.person.domain.entity.Partner;
import net.bakaar.sandbox.person.domain.repository.PartnerStore;

public class PartnerStoreAdapter implements PartnerStore {
    private final PersonRepository repository;
    private final PartnerEntityDomainMapper mapper;

    public PartnerStoreAdapter(PersonRepository repository, PartnerEntityDomainMapper mapper) {
        this.repository = repository;
        this.mapper = mapper;
    }

    @Override
    public Partner push(Partner toStore) {
        return mapper.mapToDomain(repository.save(mapper.mapToEntity(toStore)));
    }
}
