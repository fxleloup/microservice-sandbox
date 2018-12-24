package net.bakaar.sandbox.person.rest;

import au.com.dius.pact.provider.junit.Provider;
import au.com.dius.pact.provider.junit.loader.PactFolder;
import au.com.dius.pact.provider.junit.target.HttpTarget;
import au.com.dius.pact.provider.junit.target.Target;
import au.com.dius.pact.provider.junit.target.TestTarget;
import au.com.dius.pact.provider.spring.SpringRestPactRunner;
import net.bakaar.sandbox.person.data.rest.BusinessNumberService;
import org.junit.Before;
import org.junit.runner.RunWith;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.boot.test.mock.mockito.MockBean;
import org.springframework.test.context.ActiveProfiles;

import static org.mockito.BDDMockito.given;

@RunWith(SpringRestPactRunner.class)
@SpringBootTest(webEnvironment = SpringBootTest.WebEnvironment.DEFINED_PORT)
@ActiveProfiles("pact")
@Provider("partner-api")
@PactFolder("pacts")
public class PartnerAPIProviderPactIT {

  @MockBean
  private BusinessNumberService numberService;

  @Before
  public void prepareMock() {
    given(numberService.fetchPartnerNummer()).willReturn(30L);
  }

  @TestTarget
  public final Target target = new HttpTarget(8091);

}
