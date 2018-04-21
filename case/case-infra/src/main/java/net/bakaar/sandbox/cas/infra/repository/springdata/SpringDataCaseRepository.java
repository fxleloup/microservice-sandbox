package net.bakaar.sandbox.cas.infra.repository.springdata;

import org.springframework.data.repository.CrudRepository;

public interface SpringDataCaseRepository extends CrudRepository<CaseEntity, Integer> {
}
