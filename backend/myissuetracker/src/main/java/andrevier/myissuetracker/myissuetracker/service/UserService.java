package andrevier.myissuetracker.myissuetracker.service;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import andrevier.myissuetracker.myissuetracker.dao.IssueRepository;
import andrevier.myissuetracker.myissuetracker.dao.IssueTimeRepository;
import andrevier.myissuetracker.myissuetracker.dao.ManageIssueRepository;
import andrevier.myissuetracker.myissuetracker.dao.ManageProjectRepository;
import andrevier.myissuetracker.myissuetracker.dao.ProjectRepository;
import andrevier.myissuetracker.myissuetracker.dao.ProjectTimeRepository;
import andrevier.myissuetracker.myissuetracker.dao.UserRepository;
import andrevier.myissuetracker.myissuetracker.dto.IssueRequest;
import andrevier.myissuetracker.myissuetracker.dto.IssueRequestDto;
import andrevier.myissuetracker.myissuetracker.dto.ManageIssueDto;
import andrevier.myissuetracker.myissuetracker.dto.ManageProjectDto;
import andrevier.myissuetracker.myissuetracker.dto.ProjectRequest;
import andrevier.myissuetracker.myissuetracker.dto.ProjectRequestDto;
import andrevier.myissuetracker.myissuetracker.model.Issue;
import andrevier.myissuetracker.myissuetracker.model.IssueTime;
import andrevier.myissuetracker.myissuetracker.model.ManageIssue;
import andrevier.myissuetracker.myissuetracker.model.ManageProject;
import andrevier.myissuetracker.myissuetracker.model.Project;
import andrevier.myissuetracker.myissuetracker.model.ProjectTime;
import andrevier.myissuetracker.myissuetracker.model.User;

@Service
public class UserService {
    
    @Autowired
    private UserRepository userRepository;
    @Autowired
    private ManageProjectRepository manageProjectRepository;
    @Autowired
    private ProjectRepository projectRepository;
    @Autowired
    private ProjectTimeRepository projectTimeRepository;
    
    @Autowired
    private ManageIssueRepository manageIssueRepository;

    @Autowired
    private IssueTimeRepository issueTimeRepository;
    
    @Autowired
    private IssueRepository issueRepository;

    public List<User> getUsers() {
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

    public List<ProjectRequestDto> getProjects() {
        return manageProjectRepository.getProjects();
    }

    public List<ProjectRequestDto> getProjectsWithUserId(Long userId) {
        return manageProjectRepository.findProjectsByUserId(userId);
    }

    public ProjectRequest createProject(ProjectRequest newProject, Long userId) {
        // Insert project data into the repository. The project involves
        // 3 classes: ManageProject, Project, and ProjectTime.
        // From the ProjectRequest class, the information of name and 
        // description goes to Project repository;
        // Dates and times goes to the ProjectTime repository and
        // all those objects are added to the ManageProject repository.
        // All ids are created automatically.
        Project p1 = this.projectRepository.save(
            new Project(
                newProject.getProjectName(),
                newProject.getProjectDescription())
        );

        ProjectTime p1Time = projectTimeRepository.save(
            new ProjectTime(
                    newProject.getStartingDate(),
                    newProject.getDeadline()));
        
        User newUser = userRepository.findByUserId(userId);

        manageProjectRepository.save(
            new ManageProject( p1, p1Time, newUser)
        );
        
        // Add the project Id automatically set by the repository
        // to the Project request before returning.
        newProject.setProjectId(p1.getProjectId());

        return newProject;
    }

    public void updateProject(ProjectRequest updatedProject) {
        // Update one project by its id. 
        // Three entities are involved: Project, ManageProject, and ProjectTime.
        Long projectId = updatedProject.getProjectId();
        
        Project projectItem = this.projectRepository.findById(projectId).get();
        projectItem.setProjectName(updatedProject.getProjectName());

        projectItem.setProjectDescription(
            updatedProject.getProjectDescription());

        this.projectRepository.save(projectItem);

        ManageProjectDto manageProjectItem = 
        this.manageProjectRepository.findByProjectId(projectId);

        ProjectTime projectTimeItem = this.projectTimeRepository
            .findById(manageProjectItem.getProjectTimeId()).get();
        projectTimeItem.setStartingDate(
            updatedProject.getStartingDate());
        projectTimeItem.setDeadline(updatedProject.getDeadline());

        this.projectTimeRepository.save(projectTimeItem);
    }

    public void deleteProject(Long projectId) {
        // Delete a project involves 3 classes: Project, ProjectTime and ManageProject.
        // Deleting a parent also deletes the child. Then, two parents are necessary:
        // Project and ProjectTime.
        ManageProjectDto manageProjectItem = this.manageProjectRepository
            .findByProjectId(projectId);
        
        ProjectTime projectTimeItem = this.projectTimeRepository
            .findById(manageProjectItem.getProjectTimeId()).get();

        // First parent to delete.
        this.projectRepository.deleteById(projectId);
        // Second parent to delete.
        this.projectTimeRepository.deleteById(projectTimeItem.getProjectTimeId());
        
    }

    // public void createIssueWithProjectId
    public List<IssueRequestDto> getIssuesFromProject(Long projectId, Long userId) {
       return this.manageIssueRepository.findIssuesByProjectAndUser(projectId, userId);
    }

    public IssueRequest createIssueInAProject(
        Long projectId,
        Long userId,
        IssueRequest issueRequest) {
        Project project = this.projectRepository.getReferenceById(projectId);
        User user = this.userRepository.getReferenceById(userId);
        IssueTime timeForIssue = this.issueTimeRepository.save(
            new IssueTime(
                    issueRequest.getStartingDate(),
                    issueRequest.getDeadline()));

        Issue issue = this.issueRepository.save(
            new Issue(
                    issueRequest.getIssueName(),
                    issueRequest.getIssueDescription(),
                    issueRequest.getPriorityLabel(),
                    project));
        
        this.manageIssueRepository.save(
            new ManageIssue(issue, timeForIssue, user));
        
        issueRequest.setIssueId(issue.getIssueId());
        return issueRequest;
    }

    public IssueRequest updateIssue(IssueRequest issueRequest) {
        // Update the issue in a project. The only attribute that
        // cannot be updated is the project id.
        Issue updatedIssue = this.issueRepository.getReferenceById(
            issueRequest.getIssueId());
        updatedIssue.setIssueName(issueRequest.getIssueName());
        updatedIssue.setIssueDescription(issueRequest.getIssueDescription());
        updatedIssue.setPriorityLabel(issueRequest.getPriorityLabel());
        this.issueRepository.save(updatedIssue);

        ManageIssueDto manageIssue = this.manageIssueRepository
            .findByIssueId(issueRequest.getIssueId());

        IssueTime issueTime = this.issueTimeRepository.getReferenceById(
            manageIssue.getIssueTimeId());

        issueTime.setStartingDate(issueRequest.getStartingDate());
        issueTime.setDeadline(issueRequest.getDeadline());
        this.issueTimeRepository.save(issueTime);
        return issueRequest;
    }

    public void deleteIssue(long issueId) {
        ManageIssueDto manageIssueItem = this.manageIssueRepository
            .findByIssueId(issueId);
        
        IssueTime issueTime =  this.issueTimeRepository
            .getReferenceById(manageIssueItem.getIssueTimeId());
        
        this.issueRepository.deleteById(issueId);
        this.issueTimeRepository.deleteById(issueTime.getIssueTimeId());
        
    }
}
