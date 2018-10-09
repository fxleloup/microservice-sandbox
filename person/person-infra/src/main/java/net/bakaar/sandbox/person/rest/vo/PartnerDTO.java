package net.bakaar.sandbox.person.rest.vo;

import lombok.Getter;
import lombok.Setter;

import java.time.LocalDate;

@Getter
@Setter
public class PartnerDTO {

    private String id;
    private String name;
    private String forename;
    private LocalDate birthDate;
}
