package net.bakaar.sandbox.cas.domain;

public class Case {
    private final String pnummer;
    private final String id;

    public Case(String id, String pnummer) {
        this.pnummer = pnummer;
        this.id = id;
    }

    public String getPnummer() {
        return pnummer;
    }

    public String getId() {
        return id;
    }


}
