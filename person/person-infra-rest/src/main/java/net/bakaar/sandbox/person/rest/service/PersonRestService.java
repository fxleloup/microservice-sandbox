package net.bakaar.sandbox.person.rest.service;

import net.bakaar.sandbox.person.data.rest.BusinessNumberService;
import net.bakaar.sandbox.person.domain.service.CreatePartnerUseCase;
import net.bakaar.sandbox.person.rest.dto.PartnerDTO;
import net.bakaar.sandbox.person.rest.mapper.PartnerDomainDtoMapper;

public class PersonRestService {
    private final CreatePartnerUseCase createPartnerUseCase;
    private final BusinessNumberService businessNumberService;
    private final PartnerDomainDtoMapper mapper;

    public PersonRestService(CreatePartnerUseCase createPartnerUseCase, BusinessNumberService businessNumberService, PartnerDomainDtoMapper mapper) {
        this.createPartnerUseCase = createPartnerUseCase;
        this.businessNumberService = businessNumberService;
        this.mapper = mapper;
    }

    public PartnerDTO createPartner(PartnerDTO partner) {
        long id = businessNumberService.fetchPartnerNummer();
        return mapper.mapToDto(
                createPartnerUseCase.createPartner(id, partner.getName(), partner.getForename(), partner.getBirthDate())
        );
    }
}
