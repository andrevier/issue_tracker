package andrevier.myissuetracker.myissuetracker.model;

import jakarta.persistence.Entity;
import jakarta.persistence.Id;
import jakarta.persistence.OneToMany;
import jakarta.persistence.Table;
import jakarta.persistence.UniqueConstraint;
import jakarta.persistence.SequenceGenerator;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.Column;
import java.time.OffsetDateTime;
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
        columnDefinition = "TIMESTAMP WITH TIME ZONE", 
        nullable = false
    )
    private OffsetDateTime startingDate;
    
    @Column(
        name = "deadline", 
        columnDefinition = "TIMESTAMP WITH TIME ZONE"
    )
    private OffsetDateTime deadline;
    
    @Column(
        name = "closing_date", 
        columnDefinition = "TIMESTAMP WITH TIME ZONE"
    )
    private OffsetDateTime closingDate;

    @OneToMany(orphanRemoval=true, mappedBy="projectTime")
    @JsonManagedReference
    private List<ManageProject> manageProjects;

    public ProjectTime(
        OffsetDateTime startingDate, 
        OffsetDateTime deadline) {
        this.startingDate = startingDate;
        this.deadline = deadline;
        this.closingDate = null;
    }

    public ProjectTime () {
        this.startingDate = OffsetDateTime.now();
        this.deadline = null;
        this.closingDate = null;
    }
    
    public Long getProjectTimeId() {
        return this.projectTimeId;
    }

    public void setProjectTimeId(Long projectTimeId) {
        this.projectTimeId = projectTimeId;
    }
    
    public OffsetDateTime getStartingDate() {
        return this.startingDate;
    }
    
    public void setStartingDate(OffsetDateTime startingDate) {
        this.startingDate = startingDate;
    }

    public OffsetDateTime getDeadline() {
        return this.deadline;
    }

    public void setDeadline(OffsetDateTime deadline) {
        this.deadline = deadline;
    }

    public OffsetDateTime getClosingDate() {
        return this.closingDate;
    }

    public void setClosingDate(OffsetDateTime closingDate) {
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
