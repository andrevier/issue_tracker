package andrevier.myissuetracker.myissuetracker.model;

import jakarta.persistence.Entity;
import jakarta.persistence.Id;
import jakarta.persistence.Table;
import jakarta.persistence.SequenceGenerator;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.Column;
import java.time.OffsetDateTime;

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

    public ProjectTime(
        OffsetDateTime startingDate,
        OffsetDateTime deadline,
        OffsetDateTime closingDate) {
        this.startingDate = startingDate;
        this.deadline = deadline;
        this.closingDate = closingDate;
    }

    public ProjectTime () {
        this.startingDate = OffsetDateTime.now();
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