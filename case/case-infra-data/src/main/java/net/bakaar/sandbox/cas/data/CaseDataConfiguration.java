package net.bakaar.sandbox.cas.data;

import com.fasterxml.jackson.databind.ObjectMapper;
import com.fasterxml.jackson.databind.SerializationFeature;
import com.fasterxml.jackson.datatype.jsr310.JavaTimeModule;
import net.bakaar.sandbox.cas.data.jpa.CaseEntity;
import net.bakaar.sandbox.cas.data.jpa.SpringDataCaseRepository;
import net.bakaar.sandbox.cas.data.rest.BusinessIdRepositoryAdapter;
import net.bakaar.sandbox.cas.data.rest.BusinessIdServiceProperties;
import net.bakaar.sandbox.cas.domain.repository.BusinessIdRepository;
import org.springframework.boot.autoconfigure.domain.EntityScan;
import org.springframework.boot.context.properties.EnableConfigurationProperties;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.data.jpa.repository.config.EnableJpaRepositories;
import org.springframework.web.client.RestTemplate;

@Configuration
@EnableJpaRepositories(basePackageClasses = {SpringDataCaseRepository.class})
@EntityScan(basePackageClasses = {CaseEntity.class})
@EnableConfigurationProperties({BusinessIdServiceProperties.class})
public class CaseDataConfiguration {

    @Bean
    public ObjectMapper objectMapper() {
        ObjectMapper mapper = new ObjectMapper();
        mapper.registerModule(new JavaTimeModule());
        mapper.disable(SerializationFeature.WRITE_DATES_AS_TIMESTAMPS);
        return mapper;
    }

    @Bean
    public BusinessIdRepository businessIdProvider(BusinessIdServiceProperties properties, RestTemplate restTemplate) {
        return new BusinessIdRepositoryAdapter(properties, restTemplate);
    }

}
