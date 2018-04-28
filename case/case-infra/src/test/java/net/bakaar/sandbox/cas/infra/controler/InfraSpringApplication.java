package net.bakaar.sandbox.cas.infra.controler;

import net.bakaar.sandbox.cas.infra.CaseInfraConfiguration;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.context.annotation.Import;

@SpringBootApplication
@Import(CaseInfraConfiguration.class)
public class InfraSpringApplication {
}
