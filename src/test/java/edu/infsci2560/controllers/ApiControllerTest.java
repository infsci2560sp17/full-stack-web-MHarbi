package edu.infsci2560.controllers;

import edu.infsci2560.models.Greeting;
import java.nio.charset.Charset;
import org.junit.Before;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.content;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.status;
import static org.hamcrest.Matchers.*;

import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.http.MediaType;
import org.springframework.security.test.context.support.WithMockUser;
import static org.springframework.security.test.web.servlet.setup.SecurityMockMvcConfigurers.springSecurity;
import org.springframework.test.context.ActiveProfiles;
import org.springframework.test.context.ContextConfiguration;
import org.springframework.test.context.junit4.SpringRunner;
import org.springframework.test.context.web.WebAppConfiguration;
import org.springframework.test.web.servlet.MockMvc;
import org.springframework.test.web.servlet.request.MockMvcRequestBuilders;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.jsonPath;
import org.springframework.test.web.servlet.setup.MockMvcBuilders;
import org.springframework.web.context.WebApplicationContext;


@RunWith(SpringRunner.class)
@SpringBootTest
@ContextConfiguration
@WebAppConfiguration
@ActiveProfiles(profiles = { "test" })
public class ApiControllerTest {
    
    private MediaType contentType = new MediaType(MediaType.APPLICATION_JSON.getType(),
            MediaType.APPLICATION_JSON.getSubtype(),
            Charset.forName("utf8"));

    @Autowired
    private WebApplicationContext context;

    //@Autowired
    private MockMvc mvc;

    @Before
    public void setup() {
        mvc = MockMvcBuilders
                .webAppContextSetup(context)
                .apply(springSecurity())
                .build();
    }
    
    @Test
    @WithMockUser(username="user",roles={"USER", "ADMIN"})
    public void getStranger() throws Exception {
        Greeting expected = new Greeting(1, "Stranger");
        mvc.perform(MockMvcRequestBuilders.get("/api").accept(MediaType.APPLICATION_JSON))
                .andExpect(status().isOk())
                .andExpect(content().contentType(contentType))                
                .andExpect(jsonPath("$.id", is(expected.getId())))
                .andExpect(jsonPath("$.name", is(expected.getName())));
    }
    
    @Test
    @WithMockUser(username="user",roles={"USER", "ADMIN"})
    public void getJohnDoe() throws Exception {
        Greeting expected = new Greeting(2, "John");
        mvc.perform(MockMvcRequestBuilders.get("/api?name=John").accept(MediaType.APPLICATION_JSON))
                .andExpect(status().isOk())
                .andExpect(content().contentType(contentType))                
                .andExpect(jsonPath("$.id", is(expected.getId())))
                .andExpect(jsonPath("$.name", is(expected.getName())));
    }
}
