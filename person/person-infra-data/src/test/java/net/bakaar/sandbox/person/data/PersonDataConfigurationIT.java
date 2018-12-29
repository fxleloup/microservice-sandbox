package net.bakaar.sandbox.person.data;

import net.bakaar.sandbox.person.data.jpa.repository.PersonRepository;
import net.bakaar.sandbox.person.data.rest.BusinessNumberServiceProperties;
import net.bakaar.sandbox.person.domain.store.BusinessNumberStore;
import net.bakaar.sandbox.person.domain.store.PartnerStore;
import net.bakaar.sandbox.person.rest.repository.PartnerReadStore;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.orm.jpa.DataJpaTest;
import org.springframework.boot.test.mock.mockito.MockBean;
import org.springframework.test.context.ContextConfiguration;
import org.springframework.test.context.junit4.SpringRunner;
import org.springframework.web.client.RestTemplate;

import static org.assertj.core.api.Assertions.assertThat;

@RunWith(SpringRunner.class)
@DataJpaTest
@ContextConfiguration(classes = {PersonDataConfiguration.class})
public class PersonDataConfigurationIT {

    @Autowired
    private BusinessNumberStore businessNumberStore;

    @Autowired
    private BusinessNumberServiceProperties properties;

    @MockBean
    private RestTemplate restTemplate;

    @Autowired
    private PartnerStore partnerStore;

    @Autowired
    private PartnerReadStore partnerReadStore;

    @Autowired
    private PersonRepository personRepository;

    @Test
    public void context_should_load() {
        assertThat(partnerStore).isNotNull().isSameAs(partnerReadStore);
        assertThat(personRepository).isNotNull();
        assertThat(properties).isNotNull();
        assertThat(businessNumberStore).isNotNull();
    }
}