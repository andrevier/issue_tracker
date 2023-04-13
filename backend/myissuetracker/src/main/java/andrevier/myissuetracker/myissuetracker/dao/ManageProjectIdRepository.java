package andrevier.myissuetracker.myissuetracker.dao;

import org.springframework.data.jpa.repository.JpaRepository;

import andrevier.myissuetracker.myissuetracker.model.ManageProjectId;

public interface ManageProjectIdRepository extends JpaRepository<ManageProjectId, Long>{
    
}
