package net.bakaar.sandbox.person.application.service;

import net.bakaar.sandbox.person.application.external.BusinessNumberService;
import net.bakaar.sandbox.person.application.mapper.PartnerDomainDtoMapper;
import net.bakaar.sandbox.person.domain.PersonDomaineService;
import net.bakaar.sandbox.person.rest.vo.PartnerDTO;

public class DefaultPersonApplicationService implements PersonApplicationService {
    private final PersonDomaineService domainService;
    private final BusinessNumberService businessNumberService;
    private final PartnerDomainDtoMapper mapper;

    public DefaultPersonApplicationService(PersonDomaineService domainService, BusinessNumberService businessNumberService, PartnerDomainDtoMapper mapper) {
        this.domainService = domainService;
        this.businessNumberService = businessNumberService;
        this.mapper = mapper;
    }

    @Override
    public PartnerDTO createPartner(PartnerDTO partner) {
        long id = businessNumberService.fetchPartnerNummer();
        return mapper.mapToDto(
                domainService.createPartner(id, partner.getName(), partner.getForename(), partner.getBirthDate())
        );
    }
}
