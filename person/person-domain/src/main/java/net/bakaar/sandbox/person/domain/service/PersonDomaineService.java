package net.bakaar.sandbox.person.domain.service;

import net.bakaar.sandbox.person.domain.entity.Partner;
import net.bakaar.sandbox.person.domain.repository.BusinessNumberRepository;
import net.bakaar.sandbox.person.domain.repository.PartnerStore;

import java.time.LocalDate;

public class PersonDomaineService implements CreatePartnerUseCase {

    private final PartnerStore store;
    private final BusinessNumberRepository businessNumberRepository;

    public PersonDomaineService(PartnerStore store, BusinessNumberRepository businessNumberRepository) {
        this.store = store;
        this.businessNumberRepository = businessNumberRepository;
    }

    @Override
    public Partner createPartner(String name, String forename, LocalDate birthDate) {
        long id = businessNumberRepository.createPartnerNumber();
        return store.push(Partner.of(id, name, forename, birthDate));
    }
}
