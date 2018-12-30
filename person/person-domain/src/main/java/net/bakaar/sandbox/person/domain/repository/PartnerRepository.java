package net.bakaar.sandbox.person.domain.repository;

import net.bakaar.sandbox.person.domain.entity.Partner;

public interface PartnerRepository {
    Partner push(Partner toStore);
}
