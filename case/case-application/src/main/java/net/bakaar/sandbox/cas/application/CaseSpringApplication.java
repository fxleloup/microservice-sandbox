package net.bakaar.sandbox.cas.application;

import net.bakaar.sandbox.cas.infra.spring.CaseInfraConfiguration;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.context.annotation.Import;

@SpringBootApplication
@Import(CaseInfraConfiguration.class)
public class CaseSpringApplication {

    public static void main(String[] args) {
        SpringApplication.run(CaseSpringApplication.class, args);
    }
}
