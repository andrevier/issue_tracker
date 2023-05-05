package andrevier.myissuetracker.myissuetracker.service;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import andrevier.myissuetracker.myissuetracker.dao.UserRepository;
import andrevier.myissuetracker.myissuetracker.dto.UserRequest;
import andrevier.myissuetracker.myissuetracker.dto.UserRequestDto;
import andrevier.myissuetracker.myissuetracker.model.User;

@Service
public class UserService {
    
    @Autowired
    private UserRepository userRepository;

    public List<UserRequestDto> getUsers() {
        return userRepository.findAllUsers();
    }

    public UserRequest registerUser(UserRequest user) {
        User existsUser = userRepository.findByEmail(user.getEmail());
        
        if (existsUser != null) {
            throw new Error("User already exists.");
        }

        User newUser = userRepository.save(
            new User(user.getUserName(), user.getPassword(),user.getEmail()));
        
        user.setUserId(newUser.getUserId());
        return user;
    }

    public UserRequest login(UserRequest user) {
        //find user with email and password.
        User loginUser = userRepository.findByEmail(user.getEmail());
        if (loginUser == null){
            throw new Error("email not found.");
        } else if (!loginUser.getPassword().equals(user.getPassword())) {
            throw new Error("password mismatch.");
        }            
        user.setUserId(loginUser.getUserId());
        user.setUserName(loginUser.getUserName());
        return user;
    }

}
