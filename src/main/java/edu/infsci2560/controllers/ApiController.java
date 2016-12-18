package edu.infsci2560.controllers;

import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.bind.annotation.RequestMapping;

@RestController
public class ApiController {
    
    @RequestMapping("/api")
    public String index() {
        return "Greetings INFSCI2560 from Spring Boot!";
    }    
}
