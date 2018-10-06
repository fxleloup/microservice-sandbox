package net.bakaar.sandbox.person.rest.controller;

import com.fasterxml.jackson.core.JsonProcessingException;
import com.fasterxml.jackson.databind.ObjectMapper;
import net.bakaar.sandbox.person.application.service.PersonApplicationService;
import net.bakaar.sandbox.person.rest.PersonRestConfiguration;
import net.bakaar.sandbox.person.rest.vo.PartnerDTO;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.web.servlet.AutoConfigureMockMvc;
import org.springframework.boot.test.autoconfigure.web.servlet.WebMvcTest;
import org.springframework.boot.test.mock.mockito.MockBean;
import org.springframework.test.context.ContextConfiguration;
import org.springframework.test.context.junit4.SpringRunner;
import org.springframework.test.web.servlet.MockMvc;

import static org.mockito.ArgumentMatchers.any;
import static org.mockito.BDDMockito.given;
import static org.springframework.http.MediaType.APPLICATION_JSON_UTF8;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.post;
import static org.springframework.test.web.servlet.result.MockMvcResultHandlers.print;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.jsonPath;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.status;

@RunWith(SpringRunner.class)
@ContextConfiguration(classes = PersonRestConfiguration.class)
@WebMvcTest
@AutoConfigureMockMvc
public class PartnerRestControllerIT {

    @Autowired
    private MockMvc mockMvc;
    @Autowired
    private ObjectMapper mapper;
    @MockBean
    private PersonApplicationService service;

    @Test
    public void create_should_return_a_complete_partner() throws Exception {
        // Given
        PartnerDTO input = new PartnerDTO();
        PartnerDTO returned = new PartnerDTO();
        returned.setId("P34567890");
        given(service.createPartner(any(PartnerDTO.class))).willReturn(returned);
        mockMvc
                .perform(post("/rest/api/v1/partners")
                        .accept(APPLICATION_JSON_UTF8)
                        .contentType(APPLICATION_JSON_UTF8)
                        .content(asJsonString(input))
                )
                .andDo(print())
                .andExpect(status().isCreated())
                .andExpect(jsonPath("$.id").value("P34567890"))
        ;


    }

    private String asJsonString(Object object) throws JsonProcessingException {
        return mapper.writeValueAsString(object);
    }
}
