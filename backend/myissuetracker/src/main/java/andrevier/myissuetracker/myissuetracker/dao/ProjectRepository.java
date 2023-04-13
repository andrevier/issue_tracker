package andrevier.myissuetracker.myissuetracker.dao;

import org.springframework.data.jpa.repository.JpaRepository;

import andrevier.myissuetracker.myissuetracker.model.Project;

public interface ProjectRepository extends JpaRepository<Project, Long>{
    
}
