package net.bakaar.sandbox.person.rest.controller;

import com.fasterxml.jackson.core.JsonProcessingException;
import com.fasterxml.jackson.databind.ObjectMapper;
import net.bakaar.sandbox.person.rest.PersonRestConfiguration;
import net.bakaar.sandbox.person.rest.dto.PartnerDTO;
import net.bakaar.sandbox.person.rest.service.PersonRestService;
import net.bakaar.sandbox.shared.domain.vo.PNumber;
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
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.get;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.post;
import static org.springframework.test.web.servlet.result.MockMvcResultHandlers.print;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.jsonPath;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.status;

@RunWith(SpringRunner.class)
@ContextConfiguration(classes = PersonRestConfiguration.class)
@WebMvcTest
@AutoConfigureMockMvc
public class PartnerRestControllerIT {

    private final String baseUrl = "/rest/api/v1/partners";
    @Autowired
    private MockMvc mockMvc;
    @Autowired
    private ObjectMapper mapper;
    @MockBean
    private PersonRestService service;
    private final PartnerDTO returnedDto = new PartnerDTO();

    @Test
    public void create_should_return_a_complete_partner() throws Exception {
        returnedDto.setId("P34567890");
        given(service.createPartner(any(PartnerDTO.class))).willReturn(returnedDto);
        PartnerDTO input = new PartnerDTO();
        mockMvc
                .perform(post(baseUrl)
                        .accept(APPLICATION_JSON_UTF8)
                        .contentType(APPLICATION_JSON_UTF8)
                        .content(asJsonString(input))
                )
                .andDo(print())
                .andExpect(status().isCreated())
                .andExpect(jsonPath("$.id").value("P34567890"))
        ;


    }

    @Test
    public void readAPartner_should_answer_OK() throws Exception {
        long id = 56743245L;
        PNumber pNumber = PNumber.of(id);
        String name = "MyName";
        returnedDto.setName(name);
        given(service.fetchPartnerById(pNumber)).willReturn(returnedDto);
        mockMvc.perform(get(baseUrl + "/" + pNumber.format())
                .accept(APPLICATION_JSON_UTF8)
                .contentType(APPLICATION_JSON_UTF8)
        )
                .andDo(print())
                .andExpect(status().isOk())
                .andExpect(jsonPath("$.name").value(name));
    }

    private String asJsonString(Object object) throws JsonProcessingException {
        return mapper.writeValueAsString(object);
    }
}
