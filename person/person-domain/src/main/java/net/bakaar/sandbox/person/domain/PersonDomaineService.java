package net.bakaar.sandbox.person.domain;

import net.bakaar.sandbox.person.domain.entity.Partner;
import net.bakaar.sandbox.person.domain.repository.BusinessNumberRepository;

import java.time.LocalDate;

public class PersonDomaineService {

    private final BusinessNumberRepository businessNumberRepository;

    public PersonDomaineService(BusinessNumberRepository businessNumberRepository) {
        this.businessNumberRepository = businessNumberRepository;
    }

    public Partner createPartner(String name, String forename, LocalDate birthDate) {
        long id = businessNumberRepository.fetchPartnerNumber();
        return Partner.of(id, name, forename, birthDate);
    }
}
