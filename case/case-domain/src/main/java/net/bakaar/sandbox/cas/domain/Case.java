package net.bakaar.sandbox.cas.domain;

import java.time.LocalDate;

public class Case {
    private final String pnummer;
    private final String id;
    private LocalDate birthDate;

    public Case(String id, String pnummer, LocalDate birthDate) {
        this.pnummer = pnummer;
        this.id = id;
        this.birthDate = birthDate;
    }

    public String getPnummer() {
        return pnummer;
    }

    public String getId() {
        return id;
    }


    public LocalDate getBirthDate() {
        return birthDate;
    }
}
