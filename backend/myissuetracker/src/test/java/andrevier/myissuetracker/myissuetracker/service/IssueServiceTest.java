package andrevier.myissuetracker.myissuetracker.service;

import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;

import andrevier.myissuetracker.myissuetracker.config.websecurity.Roles;
import andrevier.myissuetracker.myissuetracker.dao.AuthorityRepository;
import andrevier.myissuetracker.myissuetracker.dao.IssueRepository;
import andrevier.myissuetracker.myissuetracker.dao.ManageIssueRepository;
import andrevier.myissuetracker.myissuetracker.dto.IssueRequest;
import andrevier.myissuetracker.myissuetracker.dto.IssueRequestDto;
import andrevier.myissuetracker.myissuetracker.dto.ProjectRequest;
import andrevier.myissuetracker.myissuetracker.dto.UserRequest;
import andrevier.myissuetracker.myissuetracker.model.Authority;

import static org.assertj.core.api.Assertions.*;

import java.time.LocalDateTime;
import java.util.List;

@SpringBootTest
public class IssueServiceTest {
    @Autowired
    private IssueService issueService;
    @Autowired
    private ProjectService projectService;
    @Autowired
    private UserService userService;
    @Autowired
    private ManageIssueRepository manageIssueRepo;
    @Autowired
    private AuthorityRepository authorityRepository;
    
    @Test
    void testInitializations() {
        assertThat(this.issueService).isNotNull();
        assertThat(this.projectService).isNotNull();
        assertThat(this.userService).isNotNull();
        assertThat(this.manageIssueRepo).isNotNull();
    }

    @Test
    void testCreateIssueInAProject() {
        // Create an issue. The issue is linked to a project and a user by the
        // ids.
        // Register a new user.
        UserRequest createdUser = this.userService.registerUser(
            new UserRequest(
                null,
                "Manuel Paes",
                "manuelpaes@gmail.com",
                "manuel108"));

        // Register a new project.
        LocalDateTime startingTime = LocalDateTime.of(2023,5,10,9,50);
        
        ProjectRequest createdProject = this.projectService
            .createProject(
                new ProjectRequest(
                    "Learn how to sing",
                    "Take singing lessons.",
                    startingTime,
                    startingTime.plusMonths(5)),
                createdUser.getUserId());
        
        // The user register a new issue in this project.
        IssueRequest createdIssue = this.issueService.createIssueInAProject(
            createdProject.getProjectId(), 
            createdUser.getUserId(), 
            new IssueRequest(
                "Find a good teacher",
                "Look for the possible singing teachers in the neighborhood and their prices.",
                "normal",
                startingTime,
                startingTime.plusDays(7)));
        
        // Asserts.
        assertThat(createdIssue.getIssueId()).isNotNull();
        
        assertThat(createdIssue.getIssueName()).isEqualTo("Find a good teacher");
        
        assertThat(createdIssue.getIssueDescription())
            .isEqualTo("Look for the possible singing teachers in the neighborhood and their prices.");

        assertThat(createdIssue.getStartingDate()).isEqualTo(startingTime);

        assertThat(createdIssue.getDeadline()).isEqualTo(startingTime.plusDays(7));
    }

    @Test
    void testDeleteIssue() {
        // Register a new user.
        UserRequest createdUser = this.userService.registerUser(
            new UserRequest(
                null,
                "Lucas Sa Paes",
                "lucassapaes@gmail.com",
                "lusapaesl108"));

        // Register a new project.
        LocalDateTime startingTime = LocalDateTime.of(2023,5,10,9,50);
        
        ProjectRequest createdProject = this.projectService
            .createProject(
                new ProjectRequest(
                    "Learn how to speak russian",
                    "Plan my studies of russian.",
                    startingTime,
                    startingTime.plusMonths(5)),
                createdUser.getUserId());
        
        // The user register a new issue in this project.
        IssueRequest createdIssue = this.issueService.createIssueInAProject(
            createdProject.getProjectId(), 
            createdUser.getUserId(), 
            new IssueRequest(
                "Find a russian language teacher",
                "Look for the possible language teachers in the neighborhood and their prices.",
                "normal",
                startingTime,
                startingTime.plusDays(7)));
        
        // When
        this.issueService.deleteIssue(createdIssue.getIssueId());

        // Asserts
        assertThat(this.manageIssueRepo.findIssueRequestDtoByIds(
            createdUser.getUserId(),
            createdProject.getProjectId(),
            createdIssue.getIssueId())).isNull();
    }

    @Test
    void testGetIssuesFromProject() {
        // Get all issues opened in a project.
        // Register a new user.
        UserRequest createdUser = this.userService.registerUser(
            new UserRequest(
                null,
                "Felipe Mauritano",
                "femau@gmail.com",
                "femau108"));

        // Register a new project.
        LocalDateTime startingTime = LocalDateTime.of(2023,5,10,9,50);
        
        ProjectRequest createdProject = this.projectService
            .createProject(
                new ProjectRequest(
                    "Make a tour in the city",
                    "Choose the best and iconic places to visit.",
                    startingTime,
                    startingTime.plusMonths(5)),
                    createdUser.getUserId());
        
        // The user register a new issue in this project.
        IssueRequest createdIssue = this.issueService.createIssueInAProject(
            createdProject.getProjectId(), 
            createdUser.getUserId(), 
            new IssueRequest(
                "Find a good teacher",
                "Look for the possible singing teachers in the neighborhood and their prices.",
                "normal",
                startingTime,
                startingTime.plusDays(7)));
        
        // When
        List<IssueRequestDto> issuesList = this.issueService.getIssuesFromProject(
            createdProject.getProjectId(), createdUser.getUserId());
        
        // Asserts
        assertThat(issuesList.size()).isEqualTo(1);

        assertThat(issuesList.get(0).getIssueId())
            .isEqualTo(createdIssue.getIssueId());
    }

    @Test
    void testUpdateIssue() {
        // Register a new user.
        UserRequest createdUser = this.userService.registerUser(
            new UserRequest(
                null,
                "Alex Mauritano",
                "alemau@gmail.com",
                "alemau108"));

        // Register a new project.
        LocalDateTime startingTime = LocalDateTime.of(2023,5,10,9,50);
        
        ProjectRequest createdProject = this.projectService
            .createProject(
                new ProjectRequest(
                    "Go to Iceland",
                    "plan my trip to Iceland.",
                    startingTime,
                    startingTime.plusMonths(5)),
                    createdUser.getUserId());
        
        // The user register a new issue in this project.
        IssueRequest createdIssue = this.issueService.createIssueInAProject(
            createdProject.getProjectId(), 
            createdUser.getUserId(), 
            new IssueRequest(
                "Find the best season to go to Iceland",
                "Look for the tickets and their prices.",
                "normal",
                startingTime,
                startingTime.plusDays(14)));

        // When the issue is updated.
        createdIssue.setPriorityLabel("High");

        createdIssue.setIssueDescription(
            "Look for places to stay and prices.");

        this.issueService.updateIssue(createdIssue);

        IssueRequestDto updatedIssue = this.manageIssueRepo
            .findIssueRequestDtoByIds(
                createdUser.getUserId(),
                createdProject.getProjectId(),
                createdIssue.getIssueId());
        
        // Asserts
        assertThat(updatedIssue.getIssueId())
            .isEqualTo(createdIssue.getIssueId());
        assertThat(updatedIssue.getIssueName())
            .isEqualTo(createdIssue.getIssueName());
        assertThat(updatedIssue.getIssueDescription())
            .isEqualTo(createdIssue.getIssueDescription());
    }

    @Test
    void testUserHasAuthorityWhenCreatesIssue() {
        // Test if User has the authority passed when an issue is created.
        // Register a new user.
        UserRequest createdUser = this.userService.registerUser(
            new UserRequest(
                null,
                "Alex Camargo Mauritano",
                "alecamau@gmail.com",
                "alecamau108"));

        // Register a new project.
        LocalDateTime startingTime = LocalDateTime.of(2023,5,10,9,50);
        
        ProjectRequest createdProject = this.projectService
            .createProject(
                new ProjectRequest(
                    "Go to Norway",
                    "plan my trip to Norway.",
                    startingTime,
                    startingTime.plusMonths(5)),
                    createdUser.getUserId());
        
        // The user register a new issue in this project.
        IssueRequest createdIssue = this.issueService.createIssueInAProject(
            createdProject.getProjectId(), 
            createdUser.getUserId(), 
            new IssueRequest(
                "Find the best season to go to Norway",
                "Look for the tickets and their prices.",
                "normal",
                startingTime,
                startingTime.plusDays(14)));

        // When the issue all the authorities for this issue is searched.
        List<Authority> authorities = this.authorityRepository
            .findAllAuthoritiesWithRole(
                Roles.issueAdmin(createdIssue.getIssueId()));
        
        // Asserts.
        assertThat(authorities.size()).isEqualTo(1);

        assertThat(authorities.get(0).getAuthority())
            .isEqualTo(Roles.issueAdmin(createdIssue.getIssueId()));       
        
    }

    @Test
    void testDeleteAuthorityWhenIssueIsDeleted() {
        // Test if all admin authorities are deleted when an issue is deleted.
        UserRequest createdUser = this.userService.registerUser(
            new UserRequest(
                null,
                "Gustavo Camargo Mauritano",
                "gutacamau@gmail.com",
                "gutacamau108"));

        // Register a new project.
        LocalDateTime startingTime = LocalDateTime.of(2023,5,10,9,50);
        
        ProjectRequest createdProject = this.projectService
            .createProject(
                new ProjectRequest(
                    "Go to Bora-bora island",
                    "plan my trip to Bora-bora.",
                    startingTime,
                    startingTime.plusMonths(5)),
                    createdUser.getUserId());
        
        // The user register a new issue in this project.
        IssueRequest createdIssue = this.issueService.createIssueInAProject(
            createdProject.getProjectId(), 
            createdUser.getUserId(), 
            new IssueRequest(
                "Find the best season to go to Bora-bora",
                "Look for the tickets and their prices.",
                "normal",
                startingTime,
                startingTime.plusDays(14)));
        
        // Delete an issue.
        this.issueService.deleteIssue(createdIssue.getIssueId());

        // Assert
        List<Authority> authorities = this.authorityRepository
            .findAllAuthoritiesWithRole(
                Roles.issueAdmin(createdIssue.getIssueId()));
        
        assertThat(authorities).isEmpty();
    }
}
