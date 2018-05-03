package net.bakaar.sandbox.cas.domain;

import net.bakaar.sandbox.cas.domain.provider.BusinessIdProvider;
import net.bakaar.sandbox.cas.domain.vo.PNummer;

import java.time.LocalDate;

public class CaseDomainObjectFactory {

    private final BusinessIdProvider businessIdProvider;

    public CaseDomainObjectFactory(BusinessIdProvider businessIdProvider) {
        this.businessIdProvider = businessIdProvider;
    }

    public Case createCase(String pnummer, LocalDate birthDate) {
        return new Case(businessIdProvider.generateId(), PNummer.of(pnummer), birthDate);
    }
}
