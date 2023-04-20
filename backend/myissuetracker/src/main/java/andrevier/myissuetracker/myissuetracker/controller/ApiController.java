package andrevier.myissuetracker.myissuetracker.controller;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.bind.annotation.RestController;

import andrevier.myissuetracker.myissuetracker.model.Project;
import andrevier.myissuetracker.myissuetracker.model.User;
import andrevier.myissuetracker.myissuetracker.service.UserService;


@RestController
@RequestMapping("api/v1")
@CrossOrigin(origins = "http://127.0.0.1:5500")
public class ApiController {

    private final UserService service;
    private User user;

    @Autowired
    public ApiController(UserService service) {
        this.service = service;
        this.user = new User();
    }

    @GetMapping("/getusers")
    public List<User> getAllUsers() {
        List<User> uList = service.getAllUsers();
        for (User u_ : uList) {
            System.out.println(u_);
        }
        return uList;
    }

    @PostMapping("/register")
    public User registerUser(@RequestBody User user) {
        this.user = service.registerUser(user);
        return this.user;
    }

    @PostMapping("/login")
    public User login(@RequestBody User user) {
        this.user = service.login(user);
        return this.user;
    }

    @GetMapping("/user")
    public User getCurrentUser() {
        System.out.println(this.user);
        return this.user;
    }

    @GetMapping("/user/projects")
    public List<Project> getUserProjects(@RequestBody User user) {
        return this.service.getProjects(user);
    }
}
