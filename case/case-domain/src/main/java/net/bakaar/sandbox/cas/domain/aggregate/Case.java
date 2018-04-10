package net.bakaar.sandbox.cas.domain.aggregate;

import java.util.UUID;

public class Case {
    private final String pnummer;
    private String id;

    public Case(String pnummer) {
        this.pnummer = pnummer;
        id = UUID.randomUUID().toString();
    }

    public String getPnummer() {
        return pnummer;
    }

    public String getId() {
        return id;
    }


}
