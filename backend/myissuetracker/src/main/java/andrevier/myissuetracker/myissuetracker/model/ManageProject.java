package andrevier.myissuetracker.myissuetracker.model;

import jakarta.persistence.EmbeddedId;
import jakarta.persistence.Entity;
import jakarta.persistence.ForeignKey;
import jakarta.persistence.Table;
import jakarta.persistence.JoinColumn;
import jakarta.persistence.ManyToOne;

@Entity(name = "ManageProject")
@Table(name = "manage_project")
public class ManageProject {
    
    @EmbeddedId
    private ManageProjectId manageProjectId;

    @ManyToOne(targetEntity = User.class)
    @JoinColumn(
        name="user_id", 
        nullable = false,
        foreignKey = @ForeignKey(name = "user_id_manage_project_FK")
        )
    private User user;
    
    public ManageProject() {

    }

    public ManageProject(ManageProjectId manageProjectId, User user) {
        this.manageProjectId = manageProjectId;
        this.user = user;
    }

    public void setProjectId(Long projectId) {
        this.manageProjectId.setProjectId(projectId);
    }

    public Long getProjectId() {
        return this.manageProjectId.getProjectId();
    }

    public Long getProjectTimeId() {
        return this.manageProjectId.getProjectTimeId();
    }

    public void setProjectTimeId(Long projectTimeId) {
        this.manageProjectId.setProjectTimeId(projectTimeId);
    }    

    public Long getUserId() {
        return this.user.getUserId();
    }

    public void setUserId(Long userId) {
        this.user.setUserId(userId);
    }

    @Override
    public boolean equals(Object o) {
        if (this == o)
            return true;
        if (!(o instanceof ManageProject))
            return false;
        ManageProject other = (ManageProject) o;
        return (this.manageProjectId.getProjectId() == other.manageProjectId.getProjectId()) 
            && (this.manageProjectId.getProjectTimeId() == other.manageProjectId.getProjectTimeId())
            && (this.user.getUserId() == other.user.getUserId());
    }

    @Override
    public int hashCode() {
        return this.manageProjectId.hashCode() * this.user.hashCode();
    }

    @Override
    public String toString() {
        return "ManageProject{ projectId=" + this.manageProjectId.getProjectId()
            + "projectTimeId=" + this.manageProjectId.getProjectTimeId()
            + "userId=" + this.user.getUserId() + "}";
    }
}
