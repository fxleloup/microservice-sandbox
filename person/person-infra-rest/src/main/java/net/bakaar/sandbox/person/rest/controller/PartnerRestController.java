package net.bakaar.sandbox.person.rest.controller;

import net.bakaar.sandbox.person.rest.dto.PartnerDTO;
import net.bakaar.sandbox.person.rest.service.PersonRestService;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import java.net.URI;

@RestController
@RequestMapping(path = "/rest/api/v1/partners")
public class PartnerRestController {
    private final PersonRestService service;

    public PartnerRestController(PersonRestService service) {
        this.service = service;
    }

    @PostMapping
    public ResponseEntity<PartnerDTO> create(@RequestBody PartnerDTO partner) {
        return ResponseEntity.created(URI.create("")).body(service.createPartner(partner));
    }
}
