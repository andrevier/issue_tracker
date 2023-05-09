package andrevier.myissuetracker.myissuetracker.dao;

import java.time.LocalDateTime;
import java.util.List;

import static org.assertj.core.api.Assertions.*;

import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;

import andrevier.myissuetracker.myissuetracker.dto.ManageDto;
import andrevier.myissuetracker.myissuetracker.dto.ProjectRequestDto;
import andrevier.myissuetracker.myissuetracker.model.ManageProject;
import andrevier.myissuetracker.myissuetracker.model.Project;
import andrevier.myissuetracker.myissuetracker.model.ProjectTime;
import andrevier.myissuetracker.myissuetracker.model.User;

@SpringBootTest
public class ManageProjectRepositoryTest {

    @Autowired
    private ManageProjectRepository manageRepo;

    @Autowired
    private UserRepository userRepo;

    @Autowired
    private ProjectRepository projectRepo;

    @Autowired
    private ProjectTimeRepository projectTimeRepo;

    @Test
    public void testFindByUser() {
        // // Given the user and one project registered.
        User user1 = new User(
                    "Jose Rojas Camomilla",
                    "jose108",
                    "joserojascamo@gmail.com");
        
        this.userRepo.save(user1);

        Project user1Project = this.projectRepo.save(
            new Project(
                    "Do a trip in the next summer.",
                "Save money to go on vacation in the next summer."));

        ProjectTime user1ProjectTime = this.projectTimeRepo.save(
                new ProjectTime(
                        LocalDateTime.parse("2023-04-17T09:20:30"),
                        LocalDateTime.parse("2024-08-31T15:20:30")));
                
        this.manageRepo.save(
                new ManageProject( user1Project, user1ProjectTime, user1)
        );

        // When 
        List<ManageProject> user1Projects = this.manageRepo.findByUser(user1);

        // Asserts
        assertThat(user1Projects.size()).isEqualTo(1);

        assertThat(user1Projects.get(0).getUser().getEmail())
            .isEqualTo(user1.getEmail());
    }

    @Test
    public void testFindProjectsByUserId() {
        // Given the user and three projects registered.
        User user1 = new User(
                    "Marco de la Vega",
                    "marconet",
                    "marcovega@gmail.com");
        
        this.userRepo.save(user1);
        //
        Project user1Project1 = this.projectRepo.save(
            new Project(
                    "Buy a car in Niteroi",
                "Save money to buy a car in one year."));

        ProjectTime user1ProjectTime1 = this.projectTimeRepo.save(
                new ProjectTime(
                        LocalDateTime.parse("2023-04-17T09:20:30"),
                        LocalDateTime.parse("2024-08-31T15:20:30")));
                
        this.manageRepo.save(
                new ManageProject( user1Project1, user1ProjectTime1, user1)
        );
        //
        Project user1Project2 = this.projectRepo.save(
            new Project(
                    "Buy a car in Washington",
                "Save money to buy a car in one year."));

        ProjectTime user1ProjectTime2 = this.projectTimeRepo.save(
                new ProjectTime(
                        LocalDateTime.parse("2023-04-17T09:20:30"),
                        LocalDateTime.parse("2024-08-31T15:20:30")));
                
        this.manageRepo.save(
                new ManageProject( user1Project2, user1ProjectTime2, user1)
        );
        //
        Project user1Project3 = this.projectRepo.save(
            new Project(
                    "Buy a car in Belo Horizonte",
                "Save money to buy a car in one year."));

        ProjectTime user1ProjectTime3 = this.projectTimeRepo.save(
                new ProjectTime(
                        LocalDateTime.parse("2023-04-17T09:20:30"),
                        LocalDateTime.parse("2024-08-31T15:20:30")));
                
        this.manageRepo.save(
                new ManageProject(user1Project3, user1ProjectTime3, user1)
        );

        List<ProjectRequestDto> projectRequestList = this.manageRepo.findProjectsByUserId(user1.getUserId());

        // Check if all projects are in the list.
        assertThat(projectRequestList.size()).isEqualTo(3);

        assertThat(projectRequestList.get(0).getProjectName()).isEqualTo(user1Project1.getProjectName());
        assertThat(projectRequestList.get(0).getProjectId()).isEqualTo(user1Project1.getProjectId());

        assertThat(projectRequestList.get(1).getProjectName()).isEqualTo(user1Project2.getProjectName());
        assertThat(projectRequestList.get(1).getProjectId()).isEqualTo(user1Project2.getProjectId());

        assertThat(projectRequestList.get(2).getProjectName()).isEqualTo(user1Project3.getProjectName());
        assertThat(projectRequestList.get(2).getProjectId()).isEqualTo(user1Project3.getProjectId());
        
    }

    @Test
    public void testFindByProjectId() {
        // Given an user and a project.
        User user1 = new User(
                    "Felipe de la Vega",
                    "felipenet",
                    "felipenarede@gmail.com");
        
        this.userRepo.save(user1);
        //
        Project user1Project1 = this.projectRepo.save(
            new Project(
                    "Travel to Barcelona",
                "Save money to buy a car in one year."));

        ProjectTime user1ProjectTime1 = this.projectTimeRepo.save(
                new ProjectTime(
                        LocalDateTime.parse("2023-04-17T09:20:30"),
                        LocalDateTime.parse("2024-08-31T15:20:30")));
                
        this.manageRepo.save(
                new ManageProject(user1Project1, user1ProjectTime1, user1));        

        // When
        ManageDto manageProject = this.manageRepo
                .findManageProjectByProjectId(user1Project1.getProjectId());
        
        // Assert
        assertThat(manageProject).isNotNull();

    }   
    
}
