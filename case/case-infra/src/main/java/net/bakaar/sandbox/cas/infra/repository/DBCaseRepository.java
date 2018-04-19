package net.bakaar.sandbox.cas.infra.repository;

import net.bakaar.sandbox.cas.domain.aggregate.Case;
import net.bakaar.sandbox.cas.domain.repository.CaseRepository;
import net.bakaar.sandbox.cas.infra.repository.jpa.JpaCaseRepository;

public class DBCaseRepository implements CaseRepository {

    private final JpaCaseRepository repository;

    public DBCaseRepository(JpaCaseRepository repository) {
        this.repository = repository;
    }

    @Override
    public Case save(Case aCase) {
        return repository.save(aCase);
    }
}
