package andrevier.myissuetracker.myissuetracker.service;

import org.junit.jupiter.api.Test;
import static org.assertj.core.api.Assertions.*;

import java.time.LocalDateTime;
import java.util.List;
import java.util.Optional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;

import andrevier.myissuetracker.myissuetracker.dao.AuthorityRepository;
import andrevier.myissuetracker.myissuetracker.dao.IssueRepository;
import andrevier.myissuetracker.myissuetracker.dao.ManageProjectRepository;
import andrevier.myissuetracker.myissuetracker.dao.ProjectRepository;
import andrevier.myissuetracker.myissuetracker.dao.ProjectTimeRepository;
import andrevier.myissuetracker.myissuetracker.dao.UserRepository;
import andrevier.myissuetracker.myissuetracker.dto.IssueRequest;
import andrevier.myissuetracker.myissuetracker.dto.ProjectRequest;
import andrevier.myissuetracker.myissuetracker.dto.ProjectRequestDto;
import andrevier.myissuetracker.myissuetracker.dto.UserRequest;
import andrevier.myissuetracker.myissuetracker.model.Authority;
import andrevier.myissuetracker.myissuetracker.model.UserData;

@SpringBootTest
public class ProjectServiceTest {
    @Autowired
    private ProjectService service;
    @Autowired
    private UserRepository userRepository;
    @Autowired
    private ProjectRepository projectRepository;
    @Autowired
    private ProjectTimeRepository projectTimeRepository;
    @Autowired
    private ManageProjectRepository manageRepository;
    @Autowired
    private IssueService issueService;
    @Autowired
    private UserService userService;
    @Autowired
    private IssueRepository issueRepository;
    @Autowired
    private AuthorityRepository authorityRepository;
    
    @Test
    public void testInitializatiaon() {
        assertThat(this.service).isNotNull();
        assertThat(this.userRepository).isNotNull();
        assertThat(this.projectRepository).isNotNull();
        assertThat(this.projectTimeRepository).isNotNull();
        assertThat(this.manageRepository).isNotNull();
        assertThat(this.issueService).isNotNull();
    }

    @Test
    public void testCreateProject() {
        // Test if the creation of project is valid.
        // Requesting the creation of a project for a user.
        ProjectRequest project = new ProjectRequest(
            "Practice backbends",
            "Separate a time for a day to practice backbends.",
            LocalDateTime.now(),
            LocalDateTime.now().plusMonths(5));
        
        // For the user.
        UserData user1 = this.userRepository.save(
            new UserData(
                "Marcos Vinicios Gamboa",
                "marcos247",
                "mavigamboa@gmail.com"));
        
        // When a project is created.
        ProjectRequest projectCreated = this.service
            .createProject(project, user1.getUserId());
        
        // Asserts.
        assertThat(projectCreated.getProjectName())
            .isEqualTo(project.getProjectName());

        assertThat(projectCreated.getProjectDescription())
            .isEqualTo(project.getProjectDescription());
        
        assertThat(projectCreated.getProjectId()).isNotNull();
        
    }

    @Test
    void testDeleteProjectWithoutIssues() {
        // Delete an existing project.
        LocalDateTime startingTime = LocalDateTime.of(2023,5,10,9,50);

        LocalDateTime deadline = startingTime.plusMonths(10);

        ProjectRequest project = new ProjectRequest(
            "Go to the beach every week.",
            "Schedule a day in the week to go to the beach.",
            startingTime,
            deadline);
        
        // For the user.
        UserData user1 = this.userRepository.save(
            new UserData(
                "Marcus Correa Bulhoes",
                "mabu24",
                "macobu@gmail.com"));
        
        // The project is created.
        ProjectRequest projectCreated = this.service
            .createProject(project, user1.getUserId());

        // When the project is deleted.
        this.service.deleteProject(projectCreated.getProjectId());

        // Asserts.
        assertThat(
            this.manageRepository
            .findProjectById(projectCreated.getProjectId()))
            .isNull();

    }

    @Test
    void testDeleteProjectWithIssues() {
        // If the project has one or more issues, the issues have to be deleted
        // first.

        // Register a new user.
        UserRequest createdUser = this.userService.registerUser(
            new UserRequest(
                null,
                "Juan Paes",
                "juanpaes@gmail.com",
                "juanl108"));

        // Register a new project.
        LocalDateTime startingTime = LocalDateTime.of(2023,5,10,9,50);
        
        ProjectRequest createdProject = this.service.createProject(
            new ProjectRequest(
                "Go to Pedra do Sino",
                "Plan to stay a weekend in the Pedra do sino.",
                startingTime,
                startingTime.plusMonths(5)),
            createdUser.getUserId());
        
        // The user register 3 new issues in this project.
        IssueRequest createdIssue = this.issueService.createIssueInAProject(
            createdProject.getProjectId(), 
            createdUser.getUserId(), 
            new IssueRequest(
                "Choose a weekend",
                "Choose a possible weekend with good weather to go.",
                "normal",
                startingTime,
                startingTime.plusDays(7)));

        IssueRequest createdIssue2 = this.issueService.createIssueInAProject(
            createdProject.getProjectId(),
            createdUser.getUserId(),
            new IssueRequest(
                "Check how much I can spend",
                "Check my savings.", 
                "normal",
                startingTime.plusDays(8), 
                startingTime.plusDays(17)));
        
        IssueRequest createdIssue3 = this.issueService.createIssueInAProject(
            createdProject.getProjectId(),
            createdUser.getUserId(),
            new IssueRequest(
                "Look for a place to stay",
                "Look for hostels, airbnb and other options to stay.",
                "normal",
                startingTime.plusDays(17), 
                startingTime.plusDays(21)));
        
        // When 
        this.service.deleteProject(createdProject.getProjectId());

        // Assert that the project is not in the table.
        assertThat(this.projectRepository.findById(createdProject.getProjectId()))
            .isEqualTo(Optional.empty());

        // All issues have been deleted.
        assertThat(this.issueRepository.findAllIssueIdsWithProjectId(
            createdProject.getProjectId()).size()).isEqualTo(0);
        
    }

    @Test
    void testUpdateProjectWithIssues() {
        // Register a new user.
        UserRequest createdUser = this.userService.registerUser(
            new UserRequest(
                null,
                "Pablo Arruba",
                "pablosoy@gmail.com",
                "pablo108"));

        // Register a new project.
        LocalDateTime startingTime = LocalDateTime.of(2023,5,10,9,50);
        
        ProjectRequest createdProject = this.service.createProject(
            new ProjectRequest(
                "Go to Ilha Bela",
                "Plan to stay a weekend in the Ilha Bela.",
                startingTime,
                startingTime.plusMonths(5)),
            createdUser.getUserId());
        
        // The user register 3 new issues in this project.
        IssueRequest createdIssue = this.issueService.createIssueInAProject(
            createdProject.getProjectId(), 
            createdUser.getUserId(), 
            new IssueRequest(
                "Choose a weekend",
                "Choose a possible weekend with good weather to go.",
                "normal",
                startingTime,
                startingTime.plusDays(7)));
        
        // When the project is updated.
        createdProject.setProjectName("Go to Ilha Bela");
        
        createdProject.setProjectDescription("Choose a weekend in october to go.");

        this.service.updateProject(createdProject);

        // Asserts
        List<ProjectRequestDto> projectList = this.service
            .getProjectsWithUserId(createdUser.getUserId());

        assertThat(projectList.size()).isEqualTo(1);

        assertThat(projectList.get(0).getProjectId())
            .isEqualTo(createdProject.getProjectId());
        
        assertThat(projectList.get(0).getProjectName())
            .isEqualTo(createdProject.getProjectName());
        
        assertThat(projectList.get(0).getProjectDescription())
            .isEqualTo(createdProject.getProjectDescription());
    }
    
    @Test
    void testGetProjectsWithUserId() {
        // Get all projects for an user with the user's id.
        LocalDateTime startingTime = LocalDateTime.of(2023,5,10,9,50);

        LocalDateTime deadline = startingTime.plusMonths(10);

        ProjectRequest project = new ProjectRequest(
            "Buy a turtle.",
            "Save money to buy a turtle.",
            startingTime,
            deadline);
        
        // For the user.
        UserData user1 = this.userRepository.save(
            new UserData(
                "Marcus Sampaio Goes",
                "marcusgoes24",
                "masago@gmail.com"));
        
        // The project is created.
        ProjectRequest projectCreated = this.service
            .createProject(project, user1.getUserId());
        
        // When the list of projects is requested.
        List<ProjectRequestDto> projectList = this.service
            .getProjectsWithUserId(user1.getUserId());
        
        // Asserts.
        assertThat(projectList.size()).isEqualTo(1);

        assertThat(projectList.get(0).getProjectId())
            .isEqualTo(projectCreated.getProjectId());
        
        assertThat(projectList.get(0).getProjectName())
            .isEqualTo(projectCreated.getProjectName());
        
        assertThat(projectList.get(0).getProjectDescription())
            .isEqualTo(projectCreated.getProjectDescription());
        
        assertThat(projectList.get(0).getStartingDate())
            .isEqualTo(projectCreated.getStartingDate());
        
        assertThat(projectList.get(0).getDeadline())
            .isEqualTo(projectCreated.getDeadline());
    }

    @Test
    void testfindProjectById() {
        // Get all the project data with the project id only.
        LocalDateTime startingTime = LocalDateTime.of(2023,5,10,9,50);

        LocalDateTime deadline = startingTime.plusMonths(10);

        ProjectRequest project = new ProjectRequest(
            "Practice running long distances.",
            "Schedule some time in the week to run.",
            startingTime,
            deadline);
        
        // For the user.
        UserData user1 = this.userRepository.save(
            new UserData(
                "Marcus Rivera Goes",
                "marcusgoes24",
                "marigo@gmail.com"));
        
        // When a project is created.
        ProjectRequest projectCreated = this.service
            .createProject(project, user1.getUserId());
        
        // When the project is accessed by the id.
        ProjectRequestDto projectRequested = this.manageRepository.findProjectById(
            projectCreated.getProjectId());
        
        // Asserts
        assertThat(projectRequested.getProjectId())
            .isEqualTo(projectCreated.getProjectId());
        
        assertThat(projectRequested.getProjectName())
            .isEqualTo(projectCreated.getProjectName());
        
        assertThat(projectRequested.getProjectDescription())
            .isEqualTo(projectCreated.getProjectDescription());
        
        assertThat(projectRequested.getStartingDate())
            .isEqualTo(projectCreated.getStartingDate());
        
        assertThat(projectRequested.getDeadline())
            .isEqualTo(projectCreated.getDeadline());
    }

    @Test
    void testUpdateProject() {
        // After creating a project, test if the update is correct.
        LocalDateTime startingTime = LocalDateTime.of(2023,5,10,9,50);

        LocalDateTime deadline = startingTime.plusMonths(10);

        ProjectRequest project = new ProjectRequest(
            "Practice backflips",
            "Separate a time for a day to practice backflips.",
            startingTime,
            deadline);
        
        // For the user.
        UserData user1 = this.userRepository.save(
            new UserData(
                "Marcus Aurelius",
                "marcus247",
                "maau@gmail.com"));
        
        // When a project is created.
        ProjectRequest projectCreated = this.service
            .createProject(project, user1.getUserId());
        
        // Update description.
        projectCreated.setProjectDescription("Plan how to practice backflips.");

        this.service.updateProject(projectCreated);

        // When a project is requested.
        ProjectRequestDto projectRequested = this.manageRepository
            .findProjectById(projectCreated.getProjectId());

        // Asserts.
        assertThat(projectRequested.getProjectId())
        .isEqualTo(projectCreated.getProjectId());
        
        assertThat(projectRequested.getProjectName())
            .isEqualTo(projectCreated.getProjectName());
        
        assertThat(projectRequested.getProjectDescription())
            .isEqualTo(projectCreated.getProjectDescription());
        
        assertThat(projectRequested.getStartingDate())
            .isEqualTo(projectCreated.getStartingDate());
        
        assertThat(projectRequested.getDeadline())
            .isEqualTo(projectCreated.getDeadline());
    }

    @Test
    void testUserHasAuthorityWhenCreatesAProject() {
        LocalDateTime startingTime = LocalDateTime.of(2023,5,10,9,50);

        LocalDateTime deadline = startingTime.plusMonths(10);

        ProjectRequest project = new ProjectRequest(
            "Wake up early in the morning.",
            "Schedule to wake up early every day.",
            startingTime,
            deadline);
        
        // For the user.
        UserData user1 = this.userRepository.save(
            new UserData(
                "Camargo Rivera Goes",
                "carigoes24",
                "carigo@gmail.com"));
        
        // When a project is created and the authorities are checked.
        ProjectRequest projectCreated = this.service
            .createProject(project, user1.getUserId());

        List<Authority> authorities = this.authorityRepository
            .findAllAuthoritiesWithRole(
                "PROJECT_ADMIN:" + project.getProjectId().toString());
        
        // Asserts.
        assertThat(authorities.size()).isEqualTo(1);
        assertThat(authorities.get(0).getAuthority()).isEqualTo(
            "PROJECT_ADMIN:" + project.getProjectId().toString()
        );
    }

    @Test
    void testDeleteAuthorityWhenProjectIsDeleted() {
        // Test if an authority of a project is deleted when
        // a project is deleted.
        // Delete an existing project.
        LocalDateTime startingTime = LocalDateTime.of(2023,5,10,9,50);

        LocalDateTime deadline = startingTime.plusMonths(10);

        ProjectRequest project = new ProjectRequest(
            "Go to the beach every week.",
            "Schedule a day in the week to go to the beach.",
            startingTime,
            deadline);
        
        // For the user.
        UserData user1 = this.userRepository.save(
            new UserData(
                "Iago Correa Bulhoes",
                "iabu24",
                "iacobu@gmail.com"));
        
        // The project is created.
        ProjectRequest projectCreated = this.service
            .createProject(project, user1.getUserId());

        // When the project is deleted.
        this.service.deleteProject(projectCreated.getProjectId());

        List<Authority> authorityList = this.authorityRepository
            .findAllAuthoritiesWithRole(
                "PROJECT_ADMIN:" + projectCreated.getProjectId());
        
        // Asserts.
        assertThat(authorityList).isEmpty();
    }
}
