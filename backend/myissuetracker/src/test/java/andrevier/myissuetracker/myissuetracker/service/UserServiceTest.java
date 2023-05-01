package andrevier.myissuetracker.myissuetracker.service;

import java.util.List;

import static org.assertj.core.api.Assertions.*;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;

import andrevier.myissuetracker.myissuetracker.dao.UserRepository;
import andrevier.myissuetracker.myissuetracker.dto.UserRequest;
import andrevier.myissuetracker.myissuetracker.dto.UserRequestDto;
import andrevier.myissuetracker.myissuetracker.model.User;

@SpringBootTest
public class UserServiceTest {
    @Autowired
    private UserService userService;
    
    @Autowired
    private UserRepository userRepo;

    @Test
    void testLogin() {
        // Test the login method for a user.
        User user1 = this.userRepo.save(
            new User(
            "Mauricio de Castro", "macadorio", "maumau@gmail.com"));
        
        UserRequest userRequested = new UserRequest(
            user1.getEmail(), user1.getPassword());

        // When.
        UserRequest userReceived = this.userService.login(userRequested);
        
        // Asserts
        assertThat(userReceived.getUserName()).isEqualTo(user1.getUserName());
        assertThat(userReceived.getUserId()).isNotNull(); 

    }

    @Test
    void testRegisterUser() {
        // When the client request a new user with all necessary data.
        UserRequest userRequest = new UserRequest(
            "maurilloAbelar@gmail.com", "souMaurillo");
        userRequest.setUserName("Maurillo Abelar");

        UserRequest userRegistered = this.userService.registerUser(userRequest);

        // Asserts...
        assertThat(userRegistered.getUserName()).isEqualTo(userRequest.getUserName());

    }
}
