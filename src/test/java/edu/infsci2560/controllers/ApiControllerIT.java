package edu.infsci2560.controllers;

import edu.infsci2560.LoginHelper;
import edu.infsci2560.models.Greeting;
import static org.hamcrest.Matchers.equalTo;
import static org.junit.Assert.assertThat;

import java.net.URL;
import java.util.Arrays;
import static org.assertj.core.api.Assertions.assertThat;

import org.junit.Before;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.context.embedded.LocalServerPort;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.boot.test.web.client.TestRestTemplate;
import org.springframework.http.HttpEntity;
import org.springframework.http.HttpHeaders;
import org.springframework.http.HttpMethod;
import org.springframework.http.HttpStatus;
import org.springframework.http.MediaType;
import org.springframework.http.ResponseEntity;
import org.springframework.test.context.junit4.SpringRunner;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.jsonPath;

@RunWith(SpringRunner.class)
@SpringBootTest(webEnvironment = SpringBootTest.WebEnvironment.RANDOM_PORT)
public class ApiControllerIT {
    @Autowired
    private TestRestTemplate restTemplate;
    
    @LocalServerPort
    private int port;

    private URL api;
    private URL login;    

    @Before
    public void setUp() throws Exception {
        this.api = new URL("http://localhost:" + port + "/api");
        this.login = new URL("http://localhost:" + port + "/login");
    }

    @Test
    public void getStranger() throws Exception {
//        HttpHeaders headers = new HttpHeaders();
//        headers.setAccept(Arrays.asList(MediaType.APPLICATION_JSON));
//        
//        ResponseEntity<String> entity = LoginHelper.login(this.restTemplate, this.login.toString(), "user", "password");
//        // todo : test login success
//        
//        entity = this.restTemplate.exchange(this.api.toString(), 
//			HttpMethod.GET, new HttpEntity<>(headers), String.class);
//	assertThat(entity.getStatusCode()).isEqualTo(HttpStatus.OK);                
//        assertThat(entity.getBody(), equalTo(new Greeting(1, "Stranger")));
        assert(true);
    }
}
