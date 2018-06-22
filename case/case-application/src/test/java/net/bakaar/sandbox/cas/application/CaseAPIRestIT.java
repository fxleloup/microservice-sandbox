package net.bakaar.sandbox.cas.application;

import com.fasterxml.jackson.core.JsonProcessingException;
import com.fasterxml.jackson.databind.ObjectMapper;
import net.bakaar.sandbox.cas.infra.spring.controler.CaseDTO;
import net.bakaar.sandbox.cas.infra.spring.repository.CaseEntity;
import org.hamcrest.BaseMatcher;
import org.hamcrest.Description;
import org.junit.ClassRule;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.web.servlet.AutoConfigureMockMvc;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.test.context.junit4.SpringRunner;
import org.springframework.test.web.servlet.MockMvc;
import org.testcontainers.containers.FixedHostPortGenericContainer;
import org.testcontainers.containers.GenericContainer;
import org.testcontainers.containers.wait.strategy.Wait;

import javax.persistence.EntityManager;
import java.util.List;

import static org.assertj.core.api.Assertions.assertThat;
import static org.springframework.http.MediaType.APPLICATION_JSON_UTF8;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.post;
import static org.springframework.test.web.servlet.result.MockMvcResultHandlers.print;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.jsonPath;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.status;

@RunWith(SpringRunner.class)
@SpringBootTest
@AutoConfigureMockMvc
public class CaseAPIRestIT {

    @Autowired
    private MockMvc mockMvc;
    @Autowired
    private ObjectMapper mapper;
    @Autowired
    private EntityManager entityManager;

    @ClassRule
    public static GenericContainer redis =
            new FixedHostPortGenericContainer("sandbox/businessnumber:BETA")
                    .withFixedExposedPort(8081, 8080)
                    .waitingFor(Wait.forHttp("/business-number"));

    @Test
    public void endpoint_should_return_case() throws Exception {
        // Given
        String pnummer = "P12345678";
        CaseDTO caseDTO = new CaseDTO()
                .addPnummerInjured(pnummer);
        mockMvc
                .perform(post("/cases")
                        .accept(APPLICATION_JSON_UTF8)
                        .contentType(APPLICATION_JSON_UTF8)
                        .content(asJsonString(caseDTO))
                )
                .andDo(print())
                .andExpect(status().isCreated())
                //FIXME check if necessary
//                .andExpect(header().string("location", matches("/cases/([a-f0-9]){8}(-[a-f0-9]{4}){3}-([a-f0-9]){12}")))
                .andExpect(jsonPath("$.injured.pnummer").value(pnummer))
                //TODO add this test when Person Service and the link with is established
//                .andExpect(jsonPath("$.injured.birthDate").value(birthDate.format(DateTimeFormatter.ISO_DATE)))
                .andExpect(jsonPath("$.id").isNotEmpty())
        ;
        List<CaseEntity> cases = entityManager.createQuery("select cas from CaseEntity cas", CaseEntity.class).getResultList();
        assertThat(cases).isNotEmpty();
        assertThat(cases.size()).isEqualTo(1);
        CaseEntity entity = cases.get(0);
        assertThat(entity.getPnummer()).isEqualTo(pnummer);
        assertThat(entity.getBusinessId()).isNotNull();
        assertThat(entity.getId()).isPositive();

    }

    private String asJsonString(Object object) throws JsonProcessingException {
        return mapper.writeValueAsString(object);
    }

    public static class RegexMatcher extends BaseMatcher<String> {
        private final String regex;

        public RegexMatcher(String regex) {
            this.regex = regex;
        }

        public static RegexMatcher matches(String regex) {
            return new RegexMatcher(regex);
        }

        public boolean matches(Object o) {
            return ((String) o).matches(regex);

        }

        public void describeTo(Description description) {
            description.appendText("matches regex=");
        }
    }

}
