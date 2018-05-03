package net.bakaar.sandbox.cas.domain;

import net.bakaar.sandbox.cas.domain.vo.PNummer;

public class Case {
    private final PNummer injured;
    private final String id;

    public Case(String id, PNummer injured) {
        this.injured = injured;
        this.id = id;
    }

    public PNummer getInjured() {
        return injured;
    }

    public String getId() {
        return id;
    }
}
