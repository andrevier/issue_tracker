package andrevier.myissuetracker.myissuetracker.service;

import org.junit.jupiter.api.Test;
import static org.assertj.core.api.Assertions.*;

import java.time.LocalDateTime;
import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;

import andrevier.myissuetracker.myissuetracker.dao.ManageProjectRepository;
import andrevier.myissuetracker.myissuetracker.dao.ProjectRepository;
import andrevier.myissuetracker.myissuetracker.dao.ProjectTimeRepository;
import andrevier.myissuetracker.myissuetracker.dao.UserRepository;
import andrevier.myissuetracker.myissuetracker.dto.ProjectRequest;
import andrevier.myissuetracker.myissuetracker.dto.ProjectRequestDto;
import andrevier.myissuetracker.myissuetracker.model.ManageProject;
import andrevier.myissuetracker.myissuetracker.model.Project;
import andrevier.myissuetracker.myissuetracker.model.ProjectTime;
import andrevier.myissuetracker.myissuetracker.model.User;

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
    
    @Test
    public void testInitializatiaon() {
        assertThat(this.service).isNotNull();
        assertThat(this.userRepository).isNotNull();
        assertThat(this.projectRepository).isNotNull();
        assertThat(this.projectTimeRepository).isNotNull();
        assertThat(this.manageRepository).isNotNull();
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
        User user1 = this.userRepository.save(
            new User(
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
    void testDeleteProject() {
        // Delete an existing project.
        LocalDateTime startingTime = LocalDateTime.of(2023,5,10,9,50);

        LocalDateTime deadline = startingTime.plusMonths(10);

        ProjectRequest project = new ProjectRequest(
            "Go to the beach every week.",
            "Schedule a day in the week to go to the beach.",
            startingTime,
            deadline);
        
        // For the user.
        User user1 = this.userRepository.save(
            new User(
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
        User user1 = this.userRepository.save(
            new User(
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
        User user1 = this.userRepository.save(
            new User(
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
        User user1 = this.userRepository.save(
            new User(
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
}
