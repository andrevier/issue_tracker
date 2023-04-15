package andrevier.myissuetracker.myissuetracker.service;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import andrevier.myissuetracker.myissuetracker.dao.UserRepository;
import andrevier.myissuetracker.myissuetracker.model.User;

@Service
public class ApiService {
    
    private UserRepository userRepository;
    
    @Autowired
    public ApiService(UserRepository userRepository) {
        this.userRepository = userRepository;
    }

    public List<User> getAllUsers() {
        return userRepository.findAll();
    }

    public void registerUser(User user) {
        userRepository.save(user);
    }

    public boolean login(User user) {
        //find user with email and password.
        return true;
    }
    
}
