package net.bakaar.sandbox.person.rest;

import net.bakaar.sandbox.person.application.external.BusinessNumberServiceProperties;
import net.bakaar.sandbox.person.rest.controller.PartnerRestController;
import net.bakaar.sandbox.person.rest.service.PersonApplicationService;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.test.context.ContextConfiguration;
import org.springframework.test.context.junit4.SpringRunner;

import static org.assertj.core.api.Assertions.assertThat;

@RunWith(SpringRunner.class)
@ContextConfiguration(classes = PersonRestConfiguration.class)
public class PersonRestConfigurationIT {

    @Autowired
    private PartnerRestController controller;

    @Autowired
    private PersonApplicationService applicationService;

    @Autowired
    private BusinessNumberServiceProperties properties;

    @Test
    public void context_should_be_complete() {
        assertThat(controller).isNotNull();
        assertThat(applicationService).isNotNull();
        assertThat(properties).isNotNull();
        assertThat(properties.getUrl()).isEqualTo("http://bakaar.net/bns");
    }
}