package andrevier.myissuetracker.myissuetracker.model;

import java.io.Serializable;

import jakarta.persistence.Embeddable;
import jakarta.persistence.ForeignKey;
import jakarta.persistence.JoinColumn;
import jakarta.persistence.ManyToOne;

@Embeddable
public class ManageProjectId implements Serializable{
    @ManyToOne(targetEntity = Project.class)
    @JoinColumn(
        name="project_id", 
        referencedColumnName = "project_id",
        foreignKey = @ForeignKey(name = "project_id_manage_project_FK")
    )
    private Project project;
    
    @ManyToOne(targetEntity = ProjectTime.class)
    @JoinColumn(
        name = "project_time_id", 
        referencedColumnName = "project_time_id",
        foreignKey = @ForeignKey(name = "project_time_id_manage_project_FK")
    )
    private ProjectTime projectTime;

    public ManageProjectId(Long projectId, Long projectTimeId){
        this.project.setProjectId(projectId);
        this.projectTime.setProjectTimeId(projectTimeId);
    }

    public ManageProjectId() {}

    public Long getProjectId() {
        return this.project.getProjectId();
    }

    public void setProjectId(Long projectId) {
        this.project.setProjectId(projectId);
    }

    public Long getProjectTimeId() {
        return this.projectTime.getProjectTimeId();
    }

    public void setProjectTimeId(Long projectTimeId) {
        this.projectTime.setProjectTimeId(projectTimeId);
    }
    
    @Override
    public boolean equals(Object o) {
        if (this == o)
            return true;
        if (!(o instanceof ManageProjectId))
            return false;
        ManageProjectId other = (ManageProjectId) o;
        return (this.getProjectId() == other.getProjectId()) 
            && (this.getProjectTimeId() == other.getProjectTimeId());
    }

    @Override
    public int hashCode() {
        return this.getProjectId().hashCode() * this.getProjectTimeId().hashCode();
    }
    
}
