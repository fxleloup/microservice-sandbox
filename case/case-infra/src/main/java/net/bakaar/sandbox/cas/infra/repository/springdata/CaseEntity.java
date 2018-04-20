package net.bakaar.sandbox.cas.infra.repository.springdata;

import net.bakaar.sandbox.cas.domain.aggregate.Case;

import javax.persistence.Entity;

@Entity
public class CaseEntity {
    private String pnummer;
    private String businessId;

    public static CaseEntity fromCase(Case aCase) {
        return new CaseEntity()
                .setPnummer(aCase.getPnummer())
                .setBusinessId(aCase.getId());
    }

    public Case toCase() {
        return new Case(this.getPnummer());
    }

    public String getPnummer() {
        return pnummer;
    }

    public CaseEntity setPnummer(String pnummer) {
        this.pnummer = pnummer;
        return this;
    }

    public String getBusinessId() {
        return businessId;
    }

    public CaseEntity setBusinessId(String businessId) {
        this.businessId = businessId;
        return this;
    }
}
