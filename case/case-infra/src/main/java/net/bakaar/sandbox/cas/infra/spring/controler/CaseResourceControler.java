package net.bakaar.sandbox.cas.infra.spring.controler;

import net.bakaar.sandbox.cas.domain.Case;
import net.bakaar.sandbox.cas.domain.CaseService;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RestController;

import static net.bakaar.sandbox.cas.infra.spring.controler.CaseDTO.fromCase;
import static org.springframework.http.ResponseEntity.created;
import static org.springframework.web.util.UriComponentsBuilder.fromPath;

@RestController
public class CaseResourceControler {

    private static final String CASE_ROOT_URI = "/cases";
    private final CaseService service;

    public CaseResourceControler(CaseService service) {
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
                .body(fromCase(createdCase));
    }
}
