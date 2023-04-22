package andrevier.myissuetracker.myissuetracker.controller;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;

import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.bind.annotation.RestController;

import andrevier.myissuetracker.myissuetracker.dao.ProjectRepository;
import andrevier.myissuetracker.myissuetracker.dto.ProjectRequest;
import andrevier.myissuetracker.myissuetracker.dto.ProjectRequestDto;
import andrevier.myissuetracker.myissuetracker.model.Project;
import andrevier.myissuetracker.myissuetracker.model.User;
import andrevier.myissuetracker.myissuetracker.service.UserService;


@RestController
@RequestMapping("api/v1")
@CrossOrigin(origins = "http://127.0.0.1:5500")
public class ApiController {

    private final UserService service;

    @Autowired
    public ApiController(UserService service) {
        this.service = service;
    }

    @GetMapping("/get-all-users")
    public List<User> getAllUsers() {
        List<User> uList = service.getAllUsers();
        return uList;
    }

    @PostMapping("/register-user")
    public User registerUser(@RequestBody User user) {
        return service.registerUser(user);        
    }

    @PostMapping("/login")
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
    @GetMapping("/get-all-projects")
    public List<ProjectRequestDto> getUserProjects() {
        return this.service.getProjects();
    }

    @GetMapping("/get-projects-with-user-id/{userId}")
    public List<ProjectRequestDto> getProjectById(@PathVariable Long userId) {
        return service.getProjectsByUserId(userId);
    }

    @PostMapping("/create-project/{userId}")
    public ProjectRequest createProject(@RequestBody ProjectRequest newProject, @PathVariable Long userId){
        return service.createProject(newProject, userId);
    }

    @PutMapping("/update-project")
    public String updateProject(@RequestBody ProjectRequest updateProject){
        // Update the project with ProjectRequest attributes. It is 
        // important that the project Id field is not null, otherwise it 
        // can't find the project.
        if (updateProject.getProjectId() != null) {
            service.updateProject(updateProject);
            return "Accepted";
        } else {
            return "Null project Id";
        }

    }

}
