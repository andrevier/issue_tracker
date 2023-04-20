package andrevier.myissuetracker.myissuetracker.service;

import java.util.ArrayList;
import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import andrevier.myissuetracker.myissuetracker.dao.ManageProjectRepository;
import andrevier.myissuetracker.myissuetracker.dao.UserRepository;
import andrevier.myissuetracker.myissuetracker.model.ManageProject;
import andrevier.myissuetracker.myissuetracker.model.Project;
import andrevier.myissuetracker.myissuetracker.model.User;

@Service
public class UserService {
    
    private UserRepository userRepository;
    private ManageProjectRepository manageProjectRepository;
    
    @Autowired
    public UserService(
        UserRepository userRepository,
        ManageProjectRepository manageProjectRepository) {
        this.userRepository = userRepository;
        this.manageProjectRepository = manageProjectRepository;
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

    public List<Project> getProjects(User user) {
        List<Project> projects = new ArrayList<Project>();
        
        List<ManageProject> manageProjectList = this.manageProjectRepository.findByUser(user);
        System.out.println(manageProjectList);
        for (ManageProject manageProject: manageProjectList){
            Project p  = manageProject.getProject();
            projects.add(p);
        }
        return projects;
    }
    
}
