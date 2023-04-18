package andrevier.myissuetracker.myissuetracker.service;

import org.springframework.stereotype.Service;
import org.springframework.beans.factory.annotation.Autowired;
import andrevier.myissuetracker.myissuetracker.dao.ManageProjectRepository;
import andrevier.myissuetracker.myissuetracker.dao.ProjectRepository;

//@Service
public class ProjectService {
    
    private ProjectRepository projectRepository;
    private ManageProjectRepository manageProjectRepository;

    @Autowired
    public ProjectService(
        ProjectRepository projectRepository,
        ManageProjectRepository manageProjectRepository
    ) {
        this.projectRepository = projectRepository;
        this.manageProjectRepository = manageProjectRepository;
    }

    // public List<Project> getUserProjects(User user) {
        
    // }
    

}
