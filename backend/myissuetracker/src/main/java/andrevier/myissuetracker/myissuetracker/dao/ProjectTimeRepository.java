package andrevier.myissuetracker.myissuetracker.dao;

import org.springframework.data.jpa.repository.JpaRepository;

import andrevier.myissuetracker.myissuetracker.model.ProjectTime;

public interface ProjectTimeRepository extends JpaRepository<ProjectTime, Long>{
    
}
