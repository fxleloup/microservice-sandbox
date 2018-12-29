package net.bakaar.sandbox.person.data;

import net.bakaar.sandbox.person.data.jpa.repository.PersonRepository;
import net.bakaar.sandbox.person.data.rest.BusinessNumberServiceProperties;
import net.bakaar.sandbox.person.domain.repository.BusinessNumberRepository;
import net.bakaar.sandbox.person.domain.repository.PartnerStore;
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
    private BusinessNumberRepository businessNumberRepository;

    @Autowired
    private BusinessNumberServiceProperties properties;

    @MockBean
    private RestTemplate restTemplate;

    @Autowired
    private PartnerStore partnerStore;

    @Autowired
    private PersonRepository personRepository;

    @Test
    public void rest_beans_should_be_in_context() {
        assertThat(businessNumberRepository).isNotNull();
        assertThat(properties).isNotNull();
    }

    @Test
    public void jpa_beans_should_be_in_context() {
        assertThat(partnerStore).isNotNull();
        assertThat(personRepository).isNotNull();
    }
}