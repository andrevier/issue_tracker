package andrevier.myissuetracker.myissuetracker.dao;

import org.springframework.data.jpa.repository.JpaRepository;

import andrevier.myissuetracker.myissuetracker.model.ManageProject;
import andrevier.myissuetracker.myissuetracker.model.User;

public interface ManageProjectRepository extends JpaRepository<ManageProject, Long>{
    
}
