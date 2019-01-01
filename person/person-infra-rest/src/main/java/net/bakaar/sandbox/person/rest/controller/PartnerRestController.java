package net.bakaar.sandbox.person.rest.controller;

import net.bakaar.sandbox.person.rest.dto.PartnerDTO;
import net.bakaar.sandbox.person.rest.service.PersonRestService;
import net.bakaar.sandbox.shared.domain.vo.PNumber;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

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


    @GetMapping("/{partnerId}")
    @ResponseStatus(HttpStatus.OK)
    public PartnerDTO readAPartner(@PathVariable String partnerId) {
        return service.fetchPartnerById(PNumber.of(partnerId));
    }
}
