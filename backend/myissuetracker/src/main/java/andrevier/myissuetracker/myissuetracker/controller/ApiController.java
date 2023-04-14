package andrevier.myissuetracker.myissuetracker.controller;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import andrevier.myissuetracker.myissuetracker.model.User;
import andrevier.myissuetracker.myissuetracker.service.ApiService;

@RestController
@RequestMapping(path = "api/v1")
public class ApiController {

    private final ApiService service;

    @Autowired
    public ApiController(ApiService service) {
        this.service = service;
    }

    @GetMapping("/user")
    public List<User> getAllUsers() {
        return service.getAllUsers();
    }

    @PostMapping
    public void registerUser(@RequestBody User user) {
        service.registerUser(user);        
    }

}
