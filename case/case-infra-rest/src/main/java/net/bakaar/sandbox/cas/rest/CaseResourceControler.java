package net.bakaar.sandbox.cas.rest;

import net.bakaar.sandbox.cas.domain.CreateCaseUseCase;
import net.bakaar.sandbox.cas.domain.entity.Case;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RestController;

import static org.springframework.http.ResponseEntity.created;
import static org.springframework.web.util.UriComponentsBuilder.fromPath;

@RestController
public class CaseResourceControler {

    private static final String CASE_ROOT_URI = "/cases";
    private final CreateCaseUseCase service;

    CaseResourceControler(CreateCaseUseCase service) {
        this.service = service;
    }

    @PostMapping(value = CASE_ROOT_URI, consumes = "application/json")
    public ResponseEntity<CaseDTO> addNewCase(@RequestBody CaseDTO aCase) {
        Case createdCase = service.createCase(aCase.getInjured().getPnummer());
        return created(
                fromPath(CASE_ROOT_URI + "/" + createdCase.getId())
                        .build()
                        .toUri()
        )
                .body(CaseDTO.fromCase(createdCase));
    }
}
