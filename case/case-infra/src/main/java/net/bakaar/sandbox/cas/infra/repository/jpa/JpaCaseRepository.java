package net.bakaar.sandbox.cas.infra.repository.jpa;

import net.bakaar.sandbox.cas.domain.aggregate.Case;

public interface JpaCaseRepository {
    Case save(Case aCase);
}
