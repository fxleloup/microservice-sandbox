package net.bakaar.sandbox.person.rest;

import net.bakaar.sandbox.person.rest.controller.PartnerRestController;
import org.springframework.context.annotation.ComponentScan;
import org.springframework.context.annotation.Configuration;

@Configuration
@ComponentScan(basePackageClasses = PartnerRestController.class)
public class PersonRestConfiguration {

}
