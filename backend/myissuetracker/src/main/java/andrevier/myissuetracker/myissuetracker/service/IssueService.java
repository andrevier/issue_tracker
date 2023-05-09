package andrevier.myissuetracker.myissuetracker.service;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import andrevier.myissuetracker.myissuetracker.dao.IssueRepository;
import andrevier.myissuetracker.myissuetracker.dao.IssueTimeRepository;
import andrevier.myissuetracker.myissuetracker.dao.ManageIssueRepository;
import andrevier.myissuetracker.myissuetracker.dao.ProjectRepository;
import andrevier.myissuetracker.myissuetracker.dao.UserRepository;
import andrevier.myissuetracker.myissuetracker.dto.IssueRequest;
import andrevier.myissuetracker.myissuetracker.dto.IssueRequestDto;
import andrevier.myissuetracker.myissuetracker.dto.ManageIssueDto;
import andrevier.myissuetracker.myissuetracker.model.Issue;
import andrevier.myissuetracker.myissuetracker.model.IssueTime;
import andrevier.myissuetracker.myissuetracker.model.ManageIssue;
import andrevier.myissuetracker.myissuetracker.model.Project;
import andrevier.myissuetracker.myissuetracker.model.User;

@Service
public class IssueService {
    @Autowired
    private ManageIssueRepository manageIssueRepository;
    @Autowired
    private ProjectRepository projectRepository;
    @Autowired
    private UserRepository userRepository;
    @Autowired
    private IssueTimeRepository issueTimeRepository;
    @Autowired
    private IssueRepository issueRepository;

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
 
     public void updateIssue(IssueRequest issueRequest) {
         // Update the issue in a project. The only attribute that
         // cannot be updated is the project id.
         Long issueId = issueRequest.getIssueId();

         Issue updatedIssue = this.issueRepository.findById(issueId).get();//.getReferenceById(issueId);

         updatedIssue.setIssueName(issueRequest.getIssueName());

         updatedIssue.setIssueDescription(issueRequest.getIssueDescription());

         updatedIssue.setPriorityLabel(issueRequest.getPriorityLabel());

         this.issueRepository.save(updatedIssue);
 
         ManageIssueDto manageIssue = this.manageIssueRepository
            .findByIssueId(issueId);
 
         IssueTime issueTime = this.issueTimeRepository.findById(
             manageIssue.getIssueTimeId()).get();
 
         issueTime.setStartingDate(issueRequest.getStartingDate());

         issueTime.setDeadline(issueRequest.getDeadline());
         
         this.issueTimeRepository.save(issueTime);
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
