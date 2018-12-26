package net.bakaar.sandbox.person.rest;

import au.com.dius.pact.provider.junit.Provider;
import au.com.dius.pact.provider.junit.loader.PactFolder;
import au.com.dius.pact.provider.junit.target.Target;
import au.com.dius.pact.provider.junit.target.TestTarget;
import au.com.dius.pact.provider.spring.SpringRestPactRunner;
import au.com.dius.pact.provider.spring.target.SpringBootHttpTarget;
import net.bakaar.sandbox.person.PersonApplication;
import net.bakaar.sandbox.person.rest.config.PactTestConfiguration;
import org.junit.runner.RunWith;
import org.springframework.boot.test.context.SpringBootTest;

@RunWith(SpringRestPactRunner.class)
@SpringBootTest(classes = {PactTestConfiguration.class, PersonApplication.class}, webEnvironment = SpringBootTest.WebEnvironment.RANDOM_PORT)
@Provider("partner-api")
@PactFolder("pacts")
public class PartnerAPIProviderPactIT {

  @TestTarget
  public final Target target = new SpringBootHttpTarget();

}
