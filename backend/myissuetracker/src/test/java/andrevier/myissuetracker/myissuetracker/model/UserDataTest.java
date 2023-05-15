package andrevier.myissuetracker.myissuetracker.model;

import org.junit.jupiter.api.BeforeAll;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;

import static org.assertj.core.api.Assertions.*;

import andrevier.myissuetracker.myissuetracker.dao.UserRepository;

@SpringBootTest
public class UserDataTest {
    @Autowired
    private UserRepository userRepository;

    @Test
    void testIsAccountNonExpired () {
        UserData user = this.userRepository.save(
            new UserData(
                "Marcus B. Aurelius",
                "marcus247",
                "mabau@gmail.com"));
        
        // When
        user.setAccountNonExpired(false);

        // Asserts
        assertThat(user.isAccountNonExpired()).isEqualTo(false);
    }

    @Test
    void testIsAccountNonLocked() {
        UserData user = this.userRepository.save(
            new UserData(
                "Dado B. Aurelius",
                "dadob247",
                "dabau@gmail.com"));
        
        // When 
        user.setAccountNonLocked(false);

        // Asserts
        assertThat(user.isAccountNonLocked()).isEqualTo(false);
    }
        
}
