package andrevier.myissuetracker.myissuetracker.model;

import org.junit.jupiter.api.Test;
import org.springframework.boot.test.context.SpringBootTest;

@SpringBootTest
public class AuthorityTest {
    @Test
    void testProjectAdminAuthority() {
        // Create a user via requests. 
        // Create a project via requests.
        // Test if another user can access the project without authority.
        // Test the CRUD of the project for the authority 
        // PROJECT_ADMIN:<projectId>.
    }

    @Test
    void testProjectAuthority() {
        // Create a user via requests. 
        // Create a project via requests.
        // Test if another user can access the project without authority.
        // Test the CRUD of the project for the authority PROJECT:<projectId>.
    }

    @Test
    void testIssueAdminAuthority() {
        // Create a user via requests. 
        // Create a project via requests.
        // Test if another user can access the issue without authority.
        // Test the CRUD of the project for the authority 
        // ISSUE_ADMIN:<issueId>.
    }

}
