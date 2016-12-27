package edu.infsci2560.controllers;

import edu.infsci2560.models.Greeting;
import java.util.concurrent.atomic.AtomicInteger;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.ResponseBody;

@RestController
public class ApiController {    
    private final AtomicInteger counter = new AtomicInteger();
    
    @RequestMapping("/api")        
    public @ResponseBody Greeting index(@RequestParam(value="name", required=false, defaultValue="Stranger") String name) {
        return new Greeting(counter.incrementAndGet(), name);
    }
}
