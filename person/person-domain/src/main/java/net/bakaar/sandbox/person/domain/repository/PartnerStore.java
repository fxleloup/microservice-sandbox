package net.bakaar.sandbox.person.domain.repository;

import net.bakaar.sandbox.person.domain.entity.Partner;

public interface PartnerStore {
    Partner push(Partner toStore);
}
