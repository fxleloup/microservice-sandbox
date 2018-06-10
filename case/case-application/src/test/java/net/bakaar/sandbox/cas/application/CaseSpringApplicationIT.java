package net.bakaar.sandbox.cas.application;

import net.bakaar.sandbox.cas.domain.provider.BusinessIdProvider;
import net.bakaar.sandbox.cas.domain.repository.CaseRepository;
import net.bakaar.sandbox.event.common.DomainEventEmitter;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.test.context.junit4.SpringRunner;

import static org.assertj.core.api.Assertions.assertThat;

@RunWith(SpringRunner.class)
@SpringBootTest
public class CaseSpringApplicationIT {

    @Autowired
    private DomainEventEmitter emitter;
    @Autowired
    private CaseRepository repository;
    @Autowired
    private BusinessIdProvider idProvider;

    @Test
    public void interfaces_should_be_instantiated() {
        assertThat(emitter).isNotNull();
        assertThat(repository).isNotNull();
        assertThat(idProvider).isNotNull();
    }
}