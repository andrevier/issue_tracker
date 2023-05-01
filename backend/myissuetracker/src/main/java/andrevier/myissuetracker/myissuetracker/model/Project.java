package andrevier.myissuetracker.myissuetracker.model;

import jakarta.persistence.Entity;
import jakarta.persistence.Table;
import jakarta.persistence.Id;
import jakarta.persistence.OneToMany;
import jakarta.persistence.SequenceGenerator;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;

import java.util.List;

import com.fasterxml.jackson.annotation.JsonManagedReference;

import jakarta.persistence.Column;
import jakarta.persistence.UniqueConstraint;

@Entity(name = "Project")
@Table(name = "project", uniqueConstraints = {
        @UniqueConstraint(name = "project_name_unique", columnNames = "project_name") })
public class Project {
    @Id
    @SequenceGenerator(
        name = "project_sequence", 
        sequenceName = "project_sequence", 
        allocationSize = 1
    )
    @GeneratedValue(
        strategy = GenerationType.SEQUENCE, 
        generator = "project_sequence"
    )
    @Column(name = "project_id", updatable = false)
    private Long projectId;

    @Column(name = "project_name", nullable = false)
    private String projectName;

    @Column(name = "project_description", columnDefinition = "TEXT")
    private String projectDescription;

    @OneToMany(orphanRemoval = true, mappedBy = "project")
    @JsonManagedReference
    private List<ManageProject> manageProjects;
    
    public Project(
            String projectName,
            String projectDescription) {
        this.projectName = projectName;
        this.projectDescription = projectDescription;
    }

    public Project () {
        this.projectName = "Example";
        this.projectDescription = "This project is about...";
    }

    public Long getProjectId() {
        return this.projectId;
    }

    public void setProjectId(Long id) {
        this.projectId = id;
    }

    public String getProjectName() {
        return this.projectName;
    }

    public void setProjectName(String name) {
        this.projectName = name;
    }

    public String getProjectDescription() {
        return this.projectDescription;
    }

    public void setProjectDescription(String desc) {
        this.projectDescription = desc;
    }

    public List<ManageProject> getManageProjects() {
        return manageProjects;
    }

    public void setManageProjects(List<ManageProject> manageProjects) {
        this.manageProjects = manageProjects;
    }

    @Override
    public String toString() {
        return "Project {projecId=" + this.projectId
                + ", projectName=" + this.projectName
                + ", description=" + this.projectDescription + "}";
    }

    @Override
    public boolean equals(Object o) {
        if (this == o)
            return true;
        if (!(o instanceof Project))
            return false;
        Project other = (Project) o;
        return (this.projectId == other.projectId)
            && (this.projectName == other.projectName);
    }

    @Override
    public int hashCode() {
        return this.projectId.hashCode() 
        + this.projectName.hashCode() 
        + this.projectDescription.hashCode();
    }
}
