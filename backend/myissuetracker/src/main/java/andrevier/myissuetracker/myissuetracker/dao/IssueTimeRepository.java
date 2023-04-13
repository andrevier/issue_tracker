package andrevier.myissuetracker.myissuetracker.dao;

import org.springframework.data.jpa.repository.JpaRepository;

import andrevier.myissuetracker.myissuetracker.model.IssueTime;

public interface IssueTimeRepository extends JpaRepository<IssueTime, Long>{
    
}
