package net.bakaar.sandbox.person.rest.repository;

import net.bakaar.sandbox.person.rest.dto.PartnerDTO;
import net.bakaar.sandbox.shared.domain.vo.PNumber;

public interface PartnerReadStore {

    PartnerDTO fetchPartnerById(PNumber pNumber);
}
