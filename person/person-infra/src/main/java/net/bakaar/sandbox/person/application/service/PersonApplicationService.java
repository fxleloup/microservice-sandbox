package net.bakaar.sandbox.person.application.service;

import net.bakaar.sandbox.person.rest.vo.PartnerDTO;

public interface PersonApplicationService {
    PartnerDTO createPartner(PartnerDTO partner);
}
