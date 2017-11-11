package net.bakaar.businessnumber.endpoint;


import net.bakaar.businessnumber.BusinessnumberApplication;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.web.servlet.AutoConfigureMockMvc;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.test.context.junit4.SpringRunner;
import org.springframework.test.web.servlet.MockMvc;

import static org.assertj.core.api.Assertions.assertThat;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.get;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.status;

@RunWith(SpringRunner.class)
@SpringBootTest(classes = BusinessnumberApplication.class)
@AutoConfigureMockMvc
public class BusinessNumberEndpointIT {

    @Autowired
    private MockMvc mockMvc;

    @Test
    public void endpoint_should_return_simple_UUID() throws Exception {
        assertThat(mockMvc
                .perform(get("/business-number"))
                .andExpect(status().isOk())
                .andReturn().getResponse().getContentAsString())
                .matches("\"([a-f0-9]){8}(-[a-f0-9]{4}){3}-([a-f0-9]){12}\"");
    }
}
