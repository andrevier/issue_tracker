package andrevier.myissuetracker.myissuetracker.dao;

import org.springframework.data.jpa.repository.JpaRepository;

import andrevier.myissuetracker.myissuetracker.model.ManageIssue;

public interface ManageIssueRepository extends JpaRepository<ManageIssue, Long>{
    
}
