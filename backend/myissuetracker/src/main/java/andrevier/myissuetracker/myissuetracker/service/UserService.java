package andrevier.myissuetracker.myissuetracker.service;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import andrevier.myissuetracker.myissuetracker.dao.UserRepository;
import andrevier.myissuetracker.myissuetracker.model.User;

@Service
public class UserService {
    
    private UserRepository userRepository;
    
    @Autowired
    public UserService(UserRepository userRepository) {
        this.userRepository = userRepository;
    }

    public List<User> getAllUsers() {
        return userRepository.findAll();
    }

    public User registerUser(User user) {
        User existsUser = userRepository.findByEmail(user.getEmail());
        
        if (existsUser != null) {
            throw new Error("User already exists.");
        }

        User createdUser = userRepository.save(user);

        return createdUser;
    }

    public User login(User user) {
        //find user with email and password.
        User loginUser = userRepository.findByEmail(user.getEmail());
        if (loginUser == null){
            throw new Error("email not found.");
        } else if (!loginUser.getPassword().equals(user.getPassword())) {
            throw new Error("password mismatch.");
        }            
        
        return loginUser;
    }
    
}
