package andrevier.myissuetracker.myissuetracker.dao;

import static org.assertj.core.api.Assertions.*;
import java.time.LocalDateTime;
import java.util.List;

import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;

import andrevier.myissuetracker.myissuetracker.dao.*;
import andrevier.myissuetracker.myissuetracker.dto.IssueRequestDto;
import andrevier.myissuetracker.myissuetracker.dto.ManageIssueDto;
import andrevier.myissuetracker.myissuetracker.model.Issue;
import andrevier.myissuetracker.myissuetracker.model.IssueTime;
import andrevier.myissuetracker.myissuetracker.model.ManageIssue;
import andrevier.myissuetracker.myissuetracker.model.ManageProject;
import andrevier.myissuetracker.myissuetracker.model.Project;
import andrevier.myissuetracker.myissuetracker.model.ProjectTime;
import andrevier.myissuetracker.myissuetracker.model.UserData;

@SpringBootTest
public class ManageIssueRepositoryTest {
    @Autowired
    private UserRepository userRepo;

    @Autowired
    private ProjectRepository projectRepo;

    @Autowired
    private ProjectTimeRepository projectTimeRepo;

    @Autowired
    private ManageProjectRepository manageProjectRepo;

    @Autowired
    private IssueRepository issueRepo;

    @Autowired
    private IssueTimeRepository issueTimeRepo;

    @Autowired
    private ManageIssueRepository manageIssueRepo;

    @Test
    void testFindByIssueId() {
        // Test to find issues by id. Given the following user,
        // project and issue registered.
        UserData user1 = this.userRepo.save(
            new UserData(
                    "Domenico Almeida",
                    "domeniconarede",
                    "domenico@gmail.com"));
        
        Project user1p1 = this.projectRepo.save(
                new Project(
                        "Go to Taiwan",
                        "Save money to go to Taiwan in one year."));

        ProjectTime user1p1Time = this.projectTimeRepo.save(
                new ProjectTime(
                        LocalDateTime.parse("2023-04-17T09:20:30"),
                        LocalDateTime.parse("2024-04-01T15:20:30")));
                    
        this.manageProjectRepo.save(
                new ManageProject(user1p1, user1p1Time, user1)
        );
        
        // Issues from the project p1.
        IssueTime timeForIssue1 = this.issueTimeRepo.save(
            new IssueTime(
                    LocalDateTime.now(),
                    LocalDateTime.parse("2024-05-17T00:00:00")));

        Issue issue1 = this.issueRepo.save(
            new Issue(
                    "Choose the car.",
                    "Choose the type of car considering the price.",
                    "normal",
                    user1p1));
        
        this.manageIssueRepo.save(
            new ManageIssue(issue1, timeForIssue1, user1));
        
        // When the query is executed.
        ManageIssueDto manageIssue = this.manageIssueRepo
            .findByIssueId(issue1.getIssueId());
        
        assertThat(manageIssue.getIssueId()).isEqualTo(issue1.getIssueId());
    }

    @Test
    void testFindIssuesByProjectAndUser() {
        // Test to find issues by id. Given the following user,
        // project and issue registered.
        UserData user1 = this.userRepo.save(
            new UserData(
                    "Mauro Almeida",
                    "mauroconarede",
                    "mauroal@gmail.com"));
        
        Project user1p1 = this.projectRepo.save(
                new Project(
                        "Go to Osaka",
                        "Save money to go to Osaka in one year."));

        ProjectTime user1p1Time = this.projectTimeRepo.save(
                new ProjectTime(
                        LocalDateTime.parse("2023-04-17T09:20:30"),
                        LocalDateTime.parse("2024-04-01T15:20:30")));
                    
        this.manageProjectRepo.save(
                new ManageProject(user1p1, user1p1Time, user1)
        );
        
        // Issues from the project p1.
        IssueTime timeForIssue1 = this.issueTimeRepo.save(
            new IssueTime(
                    LocalDateTime.now(),
                    LocalDateTime.parse("2024-05-17T00:00:00")));

        Issue issue1 = this.issueRepo.save(
            new Issue(
                    "Save money.",
                    "Save 10% of the money each month.",
                    "normal",
                    user1p1));
        
        this.manageIssueRepo.save(
            new ManageIssue(issue1, timeForIssue1, user1));

        //
        IssueTime timeForIssue2 = this.issueTimeRepo.save(
            new IssueTime(
                    LocalDateTime.now(),
                    LocalDateTime.parse("2023-05-17T00:00:00")));

        Issue issue2 = this.issueRepo.save(
            new Issue(
                    "Estimate how much will cost.",
                    "The total cost of the trip.",
                    "normal",
                    user1p1));
        
        this.manageIssueRepo.save(
            new ManageIssue(issue2, timeForIssue2, user1));
        //
        IssueTime timeForIssue3 = this.issueTimeRepo.save(
            new IssueTime(
                    LocalDateTime.now(),
                    LocalDateTime.parse("2024-01-17T00:00:00")));

        Issue issue3 = this.issueRepo.save(
            new Issue(
                    "Study japanese.",
                    "Study japanese each month.",
                    "normal",
                    user1p1));
        
        this.manageIssueRepo.save(
            new ManageIssue(issue3, timeForIssue3, user1));

        // When
        List<IssueRequestDto> issueList = this.manageIssueRepo
            .findIssuesByProjectAndUser(
                user1p1.getProjectId(), user1.getUserId());

        // Asserts
        assertThat(issueList.size()).isEqualTo(3);

        assertThat(issueList.get(0).getIssueId())
            .isEqualTo(issue1.getIssueId());

        assertThat(issueList.get(1).getIssueName()).isEqualTo(issue2.getIssueName());
        
    }
}
