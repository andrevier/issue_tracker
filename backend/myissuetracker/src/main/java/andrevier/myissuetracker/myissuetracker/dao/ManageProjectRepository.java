package andrevier.myissuetracker.myissuetracker.dao;

import java.util.List;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;

import andrevier.myissuetracker.myissuetracker.model.ManageProject;
import andrevier.myissuetracker.myissuetracker.model.Project;
import andrevier.myissuetracker.myissuetracker.model.User;


public interface ManageProjectRepository extends JpaRepository<ManageProject, Long>{

    // @Query("SELECT mp FROM ManageProject mp WHERE mp.user_id = :id")
    // public List<ManageProject> findByUserId(@Param("id") Long userId);

    public List<ManageProject> findByUser(User user);

    // @Query("SELECT mp FROM ManageProject mp WHERE mp.user_id = :user_id")
    // public List<ManageProject> findProjectsByUserId(@Param("userId") Long userId);
    
}
