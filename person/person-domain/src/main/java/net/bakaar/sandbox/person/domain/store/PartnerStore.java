package net.bakaar.sandbox.person.domain.store;

import net.bakaar.sandbox.person.domain.entity.Partner;

public interface PartnerStore {
    Partner push(Partner toStore);
}
