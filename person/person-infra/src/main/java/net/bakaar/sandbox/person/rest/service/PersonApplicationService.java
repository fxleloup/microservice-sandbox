package net.bakaar.sandbox.person.rest.service;

import net.bakaar.sandbox.person.rest.vo.PartnerDTO;

public interface PersonApplicationService {
    PartnerDTO createPartner(PartnerDTO partner);
}
