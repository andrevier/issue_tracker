package andrevier.myissuetracker.myissuetracker.dao;

import java.util.List;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import andrevier.myissuetracker.myissuetracker.dto.IssueRequestDto;
import andrevier.myissuetracker.myissuetracker.model.ManageIssue;

public interface ManageIssueRepository extends JpaRepository<ManageIssue, Long>{
    @Query(value = "SELECT"
    + " i.issue_id as issueId, i.issue_name as issueName,"
    + " i.issue_description as issueDescription,"
    + " it.starting_date as startingDate,"
    + " it.deadline as deadline,"
    + " i.priority_label as priorityLabel"
    + " FROM manage_issue mi"
    + " JOIN issue_data i ON mi.issue_id = i.issue_id"
    + " JOIN issue_time it ON mi.issue_time_id = it.issue_time_id"
    + " WHERE mi.user_id = :userId AND i.project_id = :projectId", nativeQuery = true)
    public List<IssueRequestDto> findIssuesByProjectAndUser(Long projectId, Long userId);
    
}
