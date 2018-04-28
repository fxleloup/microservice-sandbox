package net.bakaar.sandbox.cas.infra.controler;

import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RestController;

import static org.springframework.http.ResponseEntity.created;
import static org.springframework.web.util.UriComponentsBuilder.fromPath;

@RestController
public class CaseResourceControler {

    private final static String CASE_ROOT_URI = "/cases";

    @PostMapping(value = CASE_ROOT_URI, consumes = "application/json")
    public ResponseEntity<CaseDTO> addNewCase(@RequestBody CaseDTO aCase) {
        return created(
                fromPath(CASE_ROOT_URI + "/" + aCase.getId())
                        .build()
                        .toUri())
                .body(aCase);
    }
}
