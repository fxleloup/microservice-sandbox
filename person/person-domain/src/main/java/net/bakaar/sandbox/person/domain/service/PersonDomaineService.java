package net.bakaar.sandbox.person.domain.service;

import net.bakaar.sandbox.person.domain.entity.Partner;
import net.bakaar.sandbox.person.domain.store.BusinessNumberStore;
import net.bakaar.sandbox.person.domain.store.PartnerStore;
import net.bakaar.sandbox.shared.domain.vo.PNumber;

import java.time.LocalDate;

public class PersonDomaineService implements CreatePartnerUseCase {

    private final PartnerStore store;
    private final BusinessNumberStore businessNumberStore;

    public PersonDomaineService(PartnerStore store, BusinessNumberStore businessNumberStore) {
        this.store = store;
        this.businessNumberStore = businessNumberStore;
    }

    @Override
    public Partner createPartner(String name, String forename, LocalDate birthDate) {
        PNumber id = businessNumberStore.createPartnerNumber();
        return store.push(Partner.of(id, name, forename, birthDate));
    }
}
