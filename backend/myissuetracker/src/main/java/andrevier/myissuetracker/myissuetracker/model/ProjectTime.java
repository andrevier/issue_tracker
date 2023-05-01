package andrevier.myissuetracker.myissuetracker.model;

import jakarta.persistence.Entity;
import jakarta.persistence.Id;
import jakarta.persistence.OneToMany;
import jakarta.persistence.Table;
import jakarta.persistence.SequenceGenerator;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.Column;

import java.time.LocalDateTime;
import java.util.List;

import com.fasterxml.jackson.annotation.JsonManagedReference;

@Entity(name = "ProjectTime")
@Table(name = "project_time")
public class ProjectTime {
    @Id
    @SequenceGenerator(
        name = "project_time_sequence", 
        sequenceName = "project_time_sequence", 
        allocationSize = 1
    )
    @GeneratedValue(
        strategy = GenerationType.SEQUENCE, 
        generator = "project_time_sequence"
    )
    @Column(name = "project_time_id", updatable = false)
    private Long projectTimeId;

    @Column(
        name = "starting_date", 
        columnDefinition = "TIMESTAMP", 
        nullable = false
    )
    private LocalDateTime startingDate;
   
    @Column(
        name = "deadline", 
        columnDefinition = "TIMESTAMP"
    )
    private LocalDateTime deadline;
    
    @Column(
        name = "closing_date", 
        columnDefinition = "TIMESTAMP"
    )
    private LocalDateTime closingDate;

    @OneToMany(orphanRemoval=true, mappedBy="projectTime")
    @JsonManagedReference
    private List<ManageProject> manageProjects;

    public ProjectTime(
        LocalDateTime startingDate, 
        LocalDateTime deadline) {
        this.startingDate = startingDate;
        this.deadline = deadline;
        this.closingDate = null;
    }

    public ProjectTime () {
        this.startingDate = LocalDateTime.now();
        this.deadline = null;
        this.closingDate = null;
    }
    
    public Long getProjectTimeId() {
        return this.projectTimeId;
    }

    public void setProjectTimeId(Long projectTimeId) {
        this.projectTimeId = projectTimeId;
    }
    
    public LocalDateTime getStartingDate() {
        return this.startingDate;
    }
    
    public void setStartingDate(LocalDateTime startingDate) {
        this.startingDate = startingDate;
    }

    public LocalDateTime getDeadline() {
        return this.deadline;
    }

    public void setDeadline(LocalDateTime deadline) {
        this.deadline = deadline;
    }

    public LocalDateTime getClosingDate() {
        return this.closingDate;
    }

    public void setClosingDate(LocalDateTime closingDate) {
        this.closingDate = closingDate;
    }

    
    public List<ManageProject> getManageProjects() {
        return manageProjects;
    }

    public void setManageProjects(List<ManageProject> manageProjects) {
        this.manageProjects = manageProjects;
    }

    @Override
    public String toString() {
        return "ProjectTime{" +
                "projectTimeId=" + this.projectTimeId +
                ", startingDate=" + this.startingDate +
                ", deadline=" + this.deadline +
                ", closingDate=" + this.closingDate +
                '}';
    }

    @Override
    public boolean equals(Object o) {
        if (this == o)
            return true;
        if (!(o instanceof ProjectTime))
            return false;
        ProjectTime other = (ProjectTime) o;
        return (other.projectTimeId != null) && (this.projectTimeId == other.projectTimeId);
    }

    @Override
    public int hashCode() {
        return this.projectTimeId.hashCode() * this.startingDate.hashCode()
         * this.closingDate.hashCode() * this.deadline.hashCode();
    }
}
