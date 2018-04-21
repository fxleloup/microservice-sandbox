package net.bakaar.sandbox.cas.domain;

import net.bakaar.sandbox.cas.domain.provider.BussinessIdProvider;

public class CaseDomainObjectFactory {

    private final BussinessIdProvider bussinessIdProvider;

    public CaseDomainObjectFactory(BussinessIdProvider bussinessIdProvider) {
        this.bussinessIdProvider = bussinessIdProvider;
    }

    public Case createCase(String pnummer) {
        return new Case(bussinessIdProvider.generateId(), pnummer);
    }
}
