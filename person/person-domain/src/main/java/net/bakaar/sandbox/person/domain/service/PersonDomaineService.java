package net.bakaar.sandbox.person.domain.service;

import net.bakaar.sandbox.person.domain.entity.Partner;
import net.bakaar.sandbox.person.domain.repository.PartnerStore;

import java.time.LocalDate;

public class PersonDomaineService implements CreatePartnerUseCase {

    private final PartnerStore store;

    public PersonDomaineService(PartnerStore store) {
        this.store = store;
    }

    @Override
    public Partner createPartner(long id, String name, String forename, LocalDate birthDate) {
        return store.push(Partner.of(id, name, forename, birthDate));
    }
}
