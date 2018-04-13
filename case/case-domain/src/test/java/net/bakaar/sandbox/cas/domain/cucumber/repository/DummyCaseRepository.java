package net.bakaar.sandbox.cas.domain.cucumber.repository;

import net.bakaar.sandbox.cas.domain.aggregate.Case;
import net.bakaar.sandbox.cas.domain.repository.CaseRepository;

public class DummyCaseRepository implements CaseRepository {
    @Override
    public Case save(Case aCase) {
        return aCase;
    }
}
