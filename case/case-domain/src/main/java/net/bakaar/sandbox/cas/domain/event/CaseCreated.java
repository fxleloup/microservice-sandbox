package net.bakaar.sandbox.cas.domain.event;

public class CaseCreated implements Event {
    private final String id;
    private final String pnummer;

    public CaseCreated(String id, String pnummer) {
        this.id = id;
        this.pnummer = pnummer;
    }

    public String getId() {
        return id;
    }

    public String getPnummer() {
        return pnummer;
    }
}
