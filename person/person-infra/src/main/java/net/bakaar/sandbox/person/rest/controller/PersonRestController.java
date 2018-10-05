package net.bakaar.sandbox.person.rest.controller;

import net.bakaar.sandbox.person.application.service.PersonApplicationService;
import net.bakaar.sandbox.person.rest.vo.PartnerDTO;
import org.springframework.http.ResponseEntity;

public class PersonRestController {
    private final PersonApplicationService applicationService;

    public PersonRestController(PersonApplicationService applicationService) {
        this.applicationService = applicationService;
    }

    public ResponseEntity<PartnerDTO> create(PartnerDTO partner) {
        return ResponseEntity.ok(applicationService.createPartner(partner));
    }
}
