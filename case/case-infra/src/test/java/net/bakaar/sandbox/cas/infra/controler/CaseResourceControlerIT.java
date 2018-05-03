package net.bakaar.sandbox.cas.infra.controler;

import com.fasterxml.jackson.core.JsonProcessingException;
import com.fasterxml.jackson.databind.ObjectMapper;
import org.hamcrest.BaseMatcher;
import org.hamcrest.Description;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.web.servlet.AutoConfigureMockMvc;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.test.context.junit4.SpringRunner;
import org.springframework.test.web.servlet.MockMvc;

import java.time.LocalDate;

import static net.bakaar.sandbox.cas.infra.controler.CaseResourceControlerIT.RegexMatcher.matches;
import static org.springframework.http.MediaType.APPLICATION_JSON_UTF8;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.post;
import static org.springframework.test.web.servlet.result.MockMvcResultHandlers.print;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.*;

@RunWith(SpringRunner.class)
@SpringBootTest
@AutoConfigureMockMvc
public class CaseResourceControlerIT {

    @Autowired
    private MockMvc mockMvc;
    @Autowired
    private ObjectMapper mapper;

    @Test
    public void endpoint_should_return_case() throws Exception {
        // Given
        String pnummer = "P12345678";
        LocalDate birthDate = LocalDate.of(1981, 12, 16);
        CaseDTO caseDTO = new CaseDTO()
                .addPnummerInjured(pnummer)
                .addBirhtDateInjured(birthDate);
        mockMvc
                .perform(post("/cases")
                        .accept(APPLICATION_JSON_UTF8)
                        .contentType(APPLICATION_JSON_UTF8)
                        .content(asJsonString(caseDTO))

                )
                .andDo(print())
                .andExpect(status().isCreated())
                .andExpect(header().string("location", matches("/cases/([a-f0-9]){8}(-[a-f0-9]{4}){3}-([a-f0-9]){12}")))
                .andExpect(jsonPath("$.injured.pnummer").value(pnummer))
                //TODO add this test when Person Service and the link with is established
//                .andExpect(jsonPath("$.injured.birthDate").value(birthDate.format(DateTimeFormatter.ISO_DATE)))
                .andExpect(jsonPath("$.id").isNotEmpty())
        ;
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
