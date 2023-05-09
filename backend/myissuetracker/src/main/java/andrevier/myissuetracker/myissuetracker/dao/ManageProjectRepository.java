package andrevier.myissuetracker.myissuetracker.dao;

import java.util.List;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;

import andrevier.myissuetracker.myissuetracker.dto.ManageDto;
import andrevier.myissuetracker.myissuetracker.dto.ProjectRequestDto;
import andrevier.myissuetracker.myissuetracker.model.ManageProject;
import andrevier.myissuetracker.myissuetracker.model.User;


public interface ManageProjectRepository extends JpaRepository<ManageProject, Long>{

    
    public List<ManageProject> findByUser(User user);

    @Query(value = "SELECT v.project_id as projectId,"
    + " v.project_name as projectName,"
    + " v.project_description as projectDescription,"
    + " pt.starting_date as startingDate, pt.deadline as deadline"
    + " FROM (SELECT mp.user_id, mp.project_id, p.project_name,"
    + " p.project_description, mp.project_time_id"
    + " FROM manage_project mp"
    + " JOIN project p ON mp.project_id = p.project_id) v"
    + " JOIN project_time pt"
    + " ON pt.project_time_id = v.project_time_id", nativeQuery = true)
    public List<ProjectRequestDto> getProjects();
    
    @Query(value = "SELECT mp.project_id as projectId,"
    + " p.project_name as projectName,"
    + " p.project_description as projectDescription,"
    + " pt.starting_date as startingDate,"
    + " pt.deadline as deadline"
    + " FROM manage_project as mp"
    + " JOIN project p ON mp.project_id = p.project_id"
    + " JOIN project_time pt ON mp.project_time_id = pt.project_time_id"
    + " WHERE mp.user_id = :userId", nativeQuery = true)
    public List<ProjectRequestDto> findProjectsByUserId(Long userId);

    @Query(value = "SELECT mp.manage_project_id as manageId,"
    + " mp.user_id as userId,"
    + " mp.project_id as projectId,"
    + " mp.project_time_id as projectTimeId"
    + " FROM manage_project mp WHERE mp.project_Id = :projectId",
     nativeQuery = true)
    public ManageDto findManageProjectByProjectId(Long projectId);

    @Query(value = "SELECT mp.project_id as projectId,"
    + " p.project_name as projectName,"
    + " p.project_description as projectDescription,"
    + " pt.starting_date as startingDate,"
    + " pt.deadline as deadline"
    + " FROM manage_project as mp"
    + " JOIN project p ON mp.project_id = p.project_id"
    + " JOIN project_time pt ON mp.project_time_id = pt.project_time_id"
    + " WHERE mp.project_id = :projectId", nativeQuery = true)
    public ProjectRequestDto findProjectById(Long projectId);
}
