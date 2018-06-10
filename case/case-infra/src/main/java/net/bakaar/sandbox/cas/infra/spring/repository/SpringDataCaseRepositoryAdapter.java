package net.bakaar.sandbox.cas.infra.spring.repository;

import net.bakaar.sandbox.cas.domain.Case;
import net.bakaar.sandbox.cas.domain.repository.CaseRepository;
import org.springframework.stereotype.Component;

@Component
public class SpringDataCaseRepositoryAdapter implements CaseRepository {

    private final SpringDataCaseRepository repository;

    public SpringDataCaseRepositoryAdapter(SpringDataCaseRepository repository) {
        this.repository = repository;
    }

    @Override
    public Case save(Case aCase) {
        CaseEntity entity = CaseEntity.fromCase(aCase);
        return repository.save(entity).toCase();
    }
}
