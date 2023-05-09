package andrevier.myissuetracker.myissuetracker.dao;

import java.util.List;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;

import andrevier.myissuetracker.myissuetracker.model.Issue;

public interface IssueRepository extends JpaRepository<Issue, Long>{
    @Query(value = "SELECT i.issue_id FROM issue_data i"
    + " WHERE i.project_id = :projectId", nativeQuery = true)
    List<Long> findAllIssueIdsWithProjectId(Long projectId);
    
}
