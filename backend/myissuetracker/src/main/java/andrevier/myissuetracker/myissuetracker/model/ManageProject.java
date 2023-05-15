package andrevier.myissuetracker.myissuetracker.model;

import com.fasterxml.jackson.annotation.JsonBackReference;

import jakarta.persistence.Column;
import jakarta.persistence.Entity;
import jakarta.persistence.ForeignKey;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.Id;
import jakarta.persistence.Table;
import jakarta.persistence.JoinColumn;
import jakarta.persistence.ManyToOne;
import jakarta.persistence.SequenceGenerator;

@Entity(name = "ManageProject")
@Table(name = "manage_project")
public class ManageProject {
    @Id
    @SequenceGenerator(
        name = "manage_project_sequence", 
        sequenceName = "manage_project_sequence", 
        allocationSize = 1
    )
    @GeneratedValue(
        strategy = GenerationType.SEQUENCE, 
        generator = "manage_project_sequence"
    )
    @Column(name = "manage_project_id", updatable = false)
    private Long manageProjectId;

    @ManyToOne(targetEntity = Project.class)
    @JsonBackReference
    @JoinColumn(
        name="project_id", 
        referencedColumnName = "project_id",
        foreignKey = @ForeignKey(name = "project_id_manage_project_FK")
    )
    private Project project;
    
    @ManyToOne(targetEntity = ProjectTime.class)
    @JsonBackReference
    @JoinColumn(
        name = "project_time_id", 
        referencedColumnName = "project_time_id",
        foreignKey = @ForeignKey(name = "project_time_id_manage_project_FK")
    )
    private ProjectTime projectTime;

    @ManyToOne(targetEntity = UserData.class)
    @JsonBackReference
    @JoinColumn(
        name="user_id", 
        nullable = false,
        foreignKey = @ForeignKey(name = "user_id_manage_project_FK")
        )
    private UserData user;
    
    public ManageProject() {}

    public ManageProject(
        Project project,
        ProjectTime projectTime,
        UserData user){
            this.project = project;
            this.projectTime = projectTime;
            this.user = user;
        }

    public Long getManageProjectId() {
        return manageProjectId;
    }

    public void setManageProjectId(Long manageProjectId) {
        this.manageProjectId = manageProjectId;
    }

    public Project getProject() {
        return project;
    }

    public void setProject(Project project) {
        this.project = project;
    }

    public ProjectTime getProjectTime() {
        return projectTime;
    }

    public void setProjectTime(ProjectTime projectTime) {
        this.projectTime = projectTime;
    }

    public UserData getUser() {
        return user;
    }

    public void setUser(UserData user) {
        this.user = user;
    }
    
    public Long getProjectId() {
        return this.project.getProjectId();
    }

    public Long getProjectTimeId() {
        return this.projectTime.getProjectTimeId();
    }

    public Long getUserId() {
        return this.user.getUserId();
    }

    @Override
    public boolean equals(Object o) {
        if (this == o)
            return true;
        if (!(o instanceof ManageProject))
            return false;
        ManageProject other = (ManageProject) o;
        return (this.manageProjectId == other.manageProjectId) 
            && (this.project.getProjectId() == other.project.getProjectId())
            && (this.projectTime.getProjectTimeId() == other.projectTime.getProjectTimeId());
    }

    @Override
    public int hashCode() {
        return this.project.hashCode() * this.projectTime.hashCode();
    }

    @Override
    public String toString() {
        return "ManageProject{ manageProjectId=" + this.manageProjectId
            + "projectId=" + this.project.getProjectId()
            + "projectTimeId=" + this.projectTime.getProjectTimeId()
            + "userId=" + this.user.getUserId() + "}";
    }

}
