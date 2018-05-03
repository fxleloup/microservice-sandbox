package net.bakaar.sandbox.cas.domain;

import net.bakaar.sandbox.cas.domain.vo.PNummer;

public class Case {
    private final PNummer injured;
    private final String id;

    private Case(String id, PNummer injured) {
        this.injured = injured;
        this.id = id;
    }

    public PNummer getInjured() {
        return injured;
    }

    public String getId() {
        return id;
    }

    public static BusinessIdBuilder builder() {
        return id -> injured -> new Case(id, injured);
    }

    public interface InjuredBuilder {
        Case withInjured(PNummer injured);
    }

    public interface BusinessIdBuilder {
        InjuredBuilder withBusinnessId(String id);
    }
}
