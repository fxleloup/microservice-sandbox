package net.bakaar.businessnumber.endpoint;

import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import java.util.UUID;

@RestController
@RequestMapping("/business-number")
class BusinessNumberEndpoint {

    @GetMapping()
    public ResponseEntity<UUID> getBusinessNumber() {
        return ResponseEntity.ok(UUID.randomUUID());
    }
}
