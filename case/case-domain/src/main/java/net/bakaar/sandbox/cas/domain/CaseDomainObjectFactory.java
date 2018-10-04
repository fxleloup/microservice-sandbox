package net.bakaar.sandbox.cas.domain;

import net.bakaar.sandbox.cas.domain.provider.BusinessIdProvider;
import net.bakaar.sandbox.shared.domain.vo.PNummer;

public class CaseDomainObjectFactory {

    private final BusinessIdProvider businessIdProvider;

    public CaseDomainObjectFactory(BusinessIdProvider businessIdProvider) {
        this.businessIdProvider = businessIdProvider;
    }

    public Case createCase(String pnummer) {
        return Case.builder()
                .withBusinnessId(businessIdProvider.generateId())
                .withInjured(PNummer.of(pnummer));
    }
}
