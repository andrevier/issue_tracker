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

import andrevier.myissuetracker.myissuetracker.dto.ProjectRequestDto;
import andrevier.myissuetracker.myissuetracker.model.Project;
import andrevier.myissuetracker.myissuetracker.model.User;
import andrevier.myissuetracker.myissuetracker.service.UserService;


@RestController
@RequestMapping("api/v1")
@CrossOrigin(origins = "http://127.0.0.1:5500")
public class ApiController {

    private final UserService service;
    private List<User> currentUserList;

    @Autowired
    public ApiController(UserService service) {
        this.service = service;
    }

    @GetMapping("/get-all-users")
    public List<User> getAllUsers() {
        List<User> uList = service.getAllUsers();
        return uList;
    }

    @GetMapping("/get-all-projects")
    public List<ProjectRequestDto> getUserProjects() {
        return this.service.getProjects();
    }


    @PostMapping("register-user")
    public User registerUser(@RequestBody User user) {
        return service.registerUser(user);        
    }

    @PostMapping("login")
    public User login(@RequestBody User user) {
        return service.login(user);
    }

    // Rethink this function. Access the current user only makes sense
    // to one user only.
    // @GetMapping("/current-user")
    // public User getCurrentUser() {
    //     return new User();
    // }

    // @GetMapping("/user-by-id/{userId}")
    // public User getUserByID(@PathVariable("userId") Long id) {
    //     return new User();
    // }

    // @GetMapping("project-by-id/{projectId}")
    // public ProjectRequestDto getProjectById(@PathVariable(projectId) Long projectId) {
    //     return new;
    // }

    

}
