package net.bakaar.sandbox.cas.domain;

import net.bakaar.sandbox.cas.domain.vo.PNummer;

import java.time.LocalDate;

public class Case {
    private final PNummer pnummer;
    private final String id;
    private LocalDate birthDate;

    public Case(String id, PNummer pnummer, LocalDate birthDate) {
        this.pnummer = pnummer;
        this.id = id;
        this.birthDate = birthDate;
    }

    public PNummer getPnummer() {
        return pnummer;
    }

    public String getId() {
        return id;
    }


    public LocalDate getBirthDate() {
        return birthDate;
    }
}
