package andrevier.myissuetracker.myissuetracker.controller;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;

import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import andrevier.myissuetracker.myissuetracker.dto.IssueRequest;
import andrevier.myissuetracker.myissuetracker.dto.IssueRequestDto;
import andrevier.myissuetracker.myissuetracker.dto.ProjectRequest;
import andrevier.myissuetracker.myissuetracker.dto.ProjectRequestDto;
import andrevier.myissuetracker.myissuetracker.dto.UserRequest;
import andrevier.myissuetracker.myissuetracker.dto.UserRequestDto;
import andrevier.myissuetracker.myissuetracker.service.IssueService;
import andrevier.myissuetracker.myissuetracker.service.ProjectService;
import andrevier.myissuetracker.myissuetracker.service.UserService;


@RestController
@RequestMapping("api/v1")
@CrossOrigin(origins = "http://127.0.0.1:5500")
public class ApiController {
    @Autowired
    private UserService userService;

    @Autowired
    private ProjectService projectService;

    @Autowired
    private IssueService issueService;

   
    @GetMapping("/all-users")
    public List<UserRequestDto> getUsers() {
        return userService.getUsers();
    }

    @PostMapping("/register-user")
    public UserRequest registerUser(@RequestBody UserRequest user) {
        return userService.registerUser(user);        
    }

    @PostMapping("/login")
    public UserRequest login(@RequestBody UserRequest user) {
        return userService.login(user);
    }
    
    // Projects
    @GetMapping("/all-projects")
    public List<ProjectRequestDto> getProjects() {
        return this.projectService.getProjects();
    }

    @GetMapping("/projects-with-user-id/{userId}")
    public List<ProjectRequestDto> getProjectWithUserId(
        @PathVariable Long userId) {
        return projectService.getProjectsWithUserId(userId);
    }

    @PostMapping("/create-project/{userId}")
    public ProjectRequest createProject(
        @RequestBody ProjectRequest newProject, @PathVariable Long userId){
        return projectService.createProject(newProject, userId);
    }

    @PutMapping("/update-project")
    public String updateProject(@RequestBody ProjectRequest updateProject){
        // Update the project with ProjectRequest attributes. It is 
        // important that the project Id field is not null, otherwise it 
        // can't find the project.
        if (updateProject.getProjectId() != null) {
            projectService.updateProject(updateProject);
            return "Accepted";
        } else {
            return "Null project Id";
        }

    }

    @DeleteMapping("/delete-project/{projectId}")
    public String deleteProject(@PathVariable Long projectId) {
        this.projectService.deleteProject(projectId);
        return "Accepted.";
    }

    // Issues
    @GetMapping("/issues/{userId}/{projectId}")
    public List<IssueRequestDto> getIssuesFromProject(
        @PathVariable Long projectId, @PathVariable Long userId) {
        return this.issueService.getIssuesFromProject(projectId, userId);
    }

    @PostMapping("/create-issue/{userId}/{projectId}")
    public IssueRequest createIssueInAProject(
        @PathVariable Long projectId, 
        @PathVariable Long userId,
        @RequestBody IssueRequest issueRequest) {
            return this.issueService.createIssueInAProject(
                projectId, userId, issueRequest);

    }

    @PutMapping("/update-issue")
    public String updateIssue(@RequestBody IssueRequest request) {
        this.issueService.updateIssue(request);
        return "Updated.";
    }

    @DeleteMapping("/delete-issue/{issueId}")
    public void deleteIssue(@PathVariable Long issueId) {
        this.issueService.deleteIssue(issueId);
    }
}
