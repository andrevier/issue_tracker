package andrevier.myissuetracker.myissuetracker.dao;

import java.time.LocalDateTime;
import java.util.List;

import static org.assertj.core.api.Assertions.*;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;

import andrevier.myissuetracker.myissuetracker.model.Authority;
import andrevier.myissuetracker.myissuetracker.model.ManageProject;
import andrevier.myissuetracker.myissuetracker.model.Project;
import andrevier.myissuetracker.myissuetracker.model.ProjectTime;
import andrevier.myissuetracker.myissuetracker.model.UserData;

@SpringBootTest
public class AuthorityRepositoryTest {
    @Autowired
    private UserRepository userRepository;

    @Autowired
    private ProjectRepository projectRepository;
    
    @Autowired
    private ProjectTimeRepository projectTimeRepository;

    @Autowired
    private AuthorityRepository authorityRepository;
    
    @Autowired
    private ManageProjectRepository manageProjectRepository;

    @Test
    void testFindAllAuthoritiesWithRole() {
        // Test is the query of the database can retrieve all authorities
        // in the database.
        UserData user1 = this.userRepository.save(
            new UserData(
                    "Camillo Santiago",
                    "camillosan",
                    "camillosan@gmail.com"));
        
        Project user1p1 = this.projectRepository.save(        
            new Project(
                    "lean to fly a small plane",
                    "Take flight lessons in one year."));

        ProjectTime user1p1Time = projectTimeRepository.save(
            new ProjectTime(
                    LocalDateTime.parse("2023-04-17T09:20:30"),
                    LocalDateTime.parse("2024-08-31T15:20:30")));
        
        this.manageProjectRepository.save(
            new ManageProject( user1p1, user1p1Time, user1));
        
        this.authorityRepository.save(
            new Authority(user1p1.getProjectAdminAuthority(), user1)
        );

        // When
        List<Authority> authorityList = this.authorityRepository
            .findAllAuthoritiesWithRole(user1p1.getProjectAdminAuthority());
        
        // Asserts
        assertThat(authorityList.size()).isEqualTo(1);

        assertThat(authorityList.get(0).getAuthority())
            .isEqualTo(user1p1.getProjectAdminAuthority());
    }
}
