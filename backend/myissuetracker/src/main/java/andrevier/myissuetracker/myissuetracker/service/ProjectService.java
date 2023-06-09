package andrevier.myissuetracker.myissuetracker.service;

import org.springframework.stereotype.Service;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;

import andrevier.myissuetracker.myissuetracker.dao.AuthorityRepository;
import andrevier.myissuetracker.myissuetracker.dao.IssueRepository;
import andrevier.myissuetracker.myissuetracker.dao.ManageProjectRepository;
import andrevier.myissuetracker.myissuetracker.dao.ProjectRepository;
import andrevier.myissuetracker.myissuetracker.dao.ProjectTimeRepository;
import andrevier.myissuetracker.myissuetracker.dao.UserRepository;
import andrevier.myissuetracker.myissuetracker.dto.ManageDto;
import andrevier.myissuetracker.myissuetracker.dto.ProjectRequest;
import andrevier.myissuetracker.myissuetracker.dto.ProjectRequestDto;
import andrevier.myissuetracker.myissuetracker.model.Authority;
import andrevier.myissuetracker.myissuetracker.model.ManageProject;
import andrevier.myissuetracker.myissuetracker.model.Project;
import andrevier.myissuetracker.myissuetracker.model.ProjectTime;
import andrevier.myissuetracker.myissuetracker.model.UserData;

@Service
public class ProjectService {
    @Autowired
    private ProjectRepository projectRepository;
    @Autowired
    private ManageProjectRepository manageProjectRepository;
    @Autowired
    private ProjectTimeRepository projectTimeRepository;
    @Autowired
    private UserRepository userRepository;
    @Autowired
    private IssueRepository issueRepository;
    @Autowired
    private IssueService issueService;
    @Autowired
    private AuthorityRepository authorityRepository;

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

        ProjectTime p1Time = this.projectTimeRepository.save(
            new ProjectTime(
                    newProject.getStartingDate(),
                    newProject.getDeadline()));
        
        UserData newUser = userRepository.findByUserId(userId);

        manageProjectRepository.save(
            new ManageProject( p1, p1Time, newUser)
        );
        
        // Add the project Id automatically set by the repository
        // to the Project request before returning.
        newProject.setProjectId(p1.getProjectId());

        // Add authority to the user.
        this.authorityRepository.save(
            new Authority(p1.getProjectAdminAuthority(), newUser));
        
        return newProject;
    }

    public void updateProject(ProjectRequest updatedProject) {
        // Update one project with information in the ProjectRequest object. 
        // Three entities are involved: Project, ManageProject, and ProjectTime.
        Long projectId = updatedProject.getProjectId();
        // Access ManageProject object to find the time for the project.
        ManageDto manageProjectItem = 
            this.manageProjectRepository
            .findManageProjectByProjectId(projectId);

        ProjectTime projectTimeItem = this.projectTimeRepository
            .findById(manageProjectItem.getProjectTimeId()).get();

        // Updating the time instances.
        projectTimeItem.setStartingDate(updatedProject.getStartingDate());
        
        projectTimeItem.setDeadline(updatedProject.getDeadline());

        this.projectTimeRepository.save(projectTimeItem);
        
        // Updating the name and description.
        Project projectItem = this.projectRepository.findById(projectId).get();

        projectItem.setProjectName(updatedProject.getProjectName());

        projectItem.setProjectDescription(
            updatedProject.getProjectDescription());

        this.projectRepository.save(projectItem);        

    }

    public void deleteProject(Long projectId) {
        // Delete a project involves 3 classes: Project, ProjectTime and 
        // ManageProject. If a project has issues, then, the issues must 
        // be deleted first.
        if (this.manageProjectRepository
                .countIssuesInAProject(projectId) > 0) {
            List<Long> issueIds = this.issueRepository
                .findAllIssueIdsWithProjectId(projectId);

            for (Long id: issueIds) {
                this.issueService.deleteIssue(id);
            }
        } 

        ManageDto manageProjectItem = this.manageProjectRepository
        .findManageProjectByProjectId(projectId);
        
        ProjectTime projectTimeItem = this.projectTimeRepository
            .findById(manageProjectItem.getProjectTimeId()).get();

        // First parent to delete.
        this.projectRepository.deleteById(projectId);
        
        // Second parent to delete.
        this.projectTimeRepository.deleteById(projectTimeItem.getProjectTimeId());        
        
        // Delete the authority PROJECT_ADMIN:<projectId> from All users.
        List<Authority> listAuthorities = this.authorityRepository
            .findAllAuthoritiesWithRole("PROJECT_ADMIN:" + projectId.toString());
        
        for (Authority a : listAuthorities) {
            this.authorityRepository.deleteById(a.getAuthorityId());
        }
    }
}
