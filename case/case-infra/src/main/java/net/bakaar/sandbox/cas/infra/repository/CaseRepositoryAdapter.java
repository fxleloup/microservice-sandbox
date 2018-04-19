package net.bakaar.sandbox.cas.infra.repository;

import net.bakaar.sandbox.cas.domain.aggregate.Case;
import net.bakaar.sandbox.cas.domain.repository.CaseRepository;

public class CaseRepositoryAdapter implements CaseRepository {
    @Override
    public Case save(Case aCase) {
        return aCase;
    }
}
