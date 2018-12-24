package net.bakaar.sandbox.person.domain.service;

import net.bakaar.sandbox.person.domain.entity.Partner;

import java.time.LocalDate;

public interface CreatePartnerUseCase {
    Partner createPartner(long id, String name, String forename, LocalDate birthDate);
}
