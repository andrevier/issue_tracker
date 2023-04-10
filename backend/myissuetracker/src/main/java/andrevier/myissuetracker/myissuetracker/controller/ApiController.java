package andrevier.myissuetracker.myissuetracker.controller;

import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
public class ApiController {
    @GetMapping("/")
    public String greet() {
        return "Hello World!";
    }

}
