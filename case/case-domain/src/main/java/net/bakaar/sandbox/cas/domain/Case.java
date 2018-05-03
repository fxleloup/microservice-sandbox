package net.bakaar.sandbox.cas.domain;

import net.bakaar.sandbox.cas.domain.vo.PNummer;

public class Case {
    private final PNummer pnummer;
    private final String id;

    public Case(String id, PNummer pnummer) {
        this.pnummer = pnummer;
        this.id = id;
    }

    public PNummer getPnummer() {
        return pnummer;
    }

    public String getId() {
        return id;
    }
}
