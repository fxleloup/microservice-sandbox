package net.bakaar.sandbox.cas.domain.repository;

import net.bakaar.sandbox.cas.domain.aggregate.Case;

public interface CaseRepository {
    Case save(Case aCase);
}
