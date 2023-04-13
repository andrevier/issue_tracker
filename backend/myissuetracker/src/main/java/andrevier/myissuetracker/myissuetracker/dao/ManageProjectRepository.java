package andrevier.myissuetracker.myissuetracker.dao;

import org.springframework.data.jpa.repository.JpaRepository;

import andrevier.myissuetracker.myissuetracker.model.ManageProject;

public interface ManageProjectRepository extends JpaRepository<ManageProject, Long>{
    
}
