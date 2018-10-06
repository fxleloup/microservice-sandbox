package net.bakaar.sandbox.person.rest.controller;

import net.bakaar.sandbox.person.application.service.PersonApplicationService;
import net.bakaar.sandbox.person.rest.vo.PartnerDTO;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import java.net.URI;

@RestController
@RequestMapping(path = "/rest/api/v1/partners")
public class PartnerRestController {
    private final PersonApplicationService applicationService;

    public PartnerRestController(PersonApplicationService applicationService) {
        this.applicationService = applicationService;
    }

    @PostMapping
    public ResponseEntity<PartnerDTO> create(PartnerDTO partner) {
        return ResponseEntity.created(URI.create("")).body(applicationService.createPartner(partner));
    }
}
