package andrevier.myissuetracker.myissuetracker.model;

import java.time.OffsetDateTime;

import jakarta.persistence.Column;
import jakarta.persistence.Entity;
import jakarta.persistence.Id;
import jakarta.persistence.Table;
import jakarta.persistence.SequenceGenerator;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;

@Entity
@Table(name = "issue_time")
public class IssueTime {
    @Id
    @SequenceGenerator(
        name = "issue_time_sequence", 
        sequenceName = "issue_time_sequence", 
        allocationSize = 1
    )
    @GeneratedValue(
        strategy = GenerationType.SEQUENCE, 
        generator = "issue_time_sequence"
    )
    @Column(name = "issue_time_id", updatable = false)
    private Long issueTimeId;

    @Column(
        name = "starting_date", 
        columnDefinition = "TIMESTAMP WITH TIME ZONE", 
        nullable = false
    )
    private OffsetDateTime startingDate;

    @Column(
        name = "closing_date", 
        columnDefinition = "TIMESTAMP WITH TIME ZONE"
    )
    private OffsetDateTime closingDate;

    @Column(
        name = "deadline", 
        columnDefinition = "TIMESTAMP WITH TIME ZONE"
    )
    private OffsetDateTime deadline;

    public IssueTime(
        OffsetDateTime startingDate,
        OffsetDateTime deadline,
        OffsetDateTime closingDate) {
            this.startingDate = startingDate;
            this.closingDate = closingDate;
            this.deadline = deadline;
    }

    public IssueTime() {
        this.startingDate = OffsetDateTime.now();
    }

    public Long getIssueTimeId() {
        return this.issueTimeId;
    }

    public void setIssueTimeId(Long issueTimeId) {
        this.issueTimeId = issueTimeId;
    }

    public OffsetDateTime getStartingDate() {
        return this.startingDate;
    }

    public void setStartingDate(OffsetDateTime startingDate) {
        this.startingDate = startingDate;
    }

    public OffsetDateTime getClosingDate() {
        return this.closingDate;
    }

    public void setClosingDate(OffsetDateTime closingDate) {
        this.closingDate = closingDate;
    }

    public OffsetDateTime getDeadline() {
        return this.deadline;
    }

    public void setDeadline(OffsetDateTime deadline) {
        this.deadline = deadline;
    }

    @Override
    public boolean equals(Object o) {
        if (this == o)
            return true;
        if (!(o instanceof IssueTime))
            return false;
        IssueTime other = (IssueTime) o;
        return (other.getIssueTimeId() != null && other.getIssueTimeId() == this.issueTimeId)
            && (other.getStartingDate() == this.startingDate);
    }

    @Override
    public int hashCode() {
        return this.issueTimeId.hashCode() * this.startingDate.hashCode();
    }

    @Override
    public String toString() {
        return "IssueTime{ issueTimeId=" + this.issueTimeId + ", startingDate=" 
        + this.startingDate + ", closingDate=" + this.closingDate + "}";
    }



    
}
