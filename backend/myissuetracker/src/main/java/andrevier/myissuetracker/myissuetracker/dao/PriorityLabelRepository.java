package andrevier.myissuetracker.myissuetracker.dao;

import org.springframework.data.jpa.repository.JpaRepository;

import andrevier.myissuetracker.myissuetracker.model.PriorityLabel;

public interface PriorityLabelRepository extends JpaRepository<PriorityLabel, Long>{
    
}
