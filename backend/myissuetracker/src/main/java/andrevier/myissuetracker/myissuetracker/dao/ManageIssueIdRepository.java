package andrevier.myissuetracker.myissuetracker.dao;

import org.springframework.data.jpa.repository.JpaRepository;

import andrevier.myissuetracker.myissuetracker.model.ManageIssueId;

public interface ManageIssueIdRepository extends JpaRepository<ManageIssueId, Long>{
    
}
