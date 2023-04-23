package andrevier.myissuetracker.myissuetracker.model;

import java.time.LocalDateTime;
import java.util.List;

import com.fasterxml.jackson.annotation.JsonManagedReference;

import jakarta.persistence.Column;
import jakarta.persistence.Entity;
import jakarta.persistence.Id;
import jakarta.persistence.OneToMany;
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
        columnDefinition = "TIMESTAMP", 
        nullable = false
    )
    private LocalDateTime startingDate;

    @Column(
        name = "closing_date", 
        columnDefinition = "TIMESTAMP"
    )
    private LocalDateTime closingDate;

    @Column(
        name = "deadline", 
        columnDefinition = "TIMESTAMP"
    )
    private LocalDateTime deadline;

    @OneToMany(orphanRemoval=true, mappedBy="issueTime")
    @JsonManagedReference
    private List<ManageIssue> manageIssues;

    public IssueTime(
        LocalDateTime startingDate,
        LocalDateTime deadline) {
            this.startingDate = startingDate;
            this.deadline = deadline;
    }

    public IssueTime() {
        this.startingDate = LocalDateTime.now();
        this.closingDate = null;
        this.deadline = null;
    }

    public Long getIssueTimeId() {
        return this.issueTimeId;
    }

    public void setIssueTimeId(Long issueTimeId) {
        this.issueTimeId = issueTimeId;
    }

    public LocalDateTime getStartingDate() {
        return this.startingDate;
    }

    public void setStartingDate(LocalDateTime startingDate) {
        this.startingDate = startingDate;
    }

    public LocalDateTime getClosingDate() {
        return this.closingDate;
    }

    public void setClosingDate(LocalDateTime closingDate) {
        this.closingDate = closingDate;
    }

    public LocalDateTime getDeadline() {
        return this.deadline;
    }

    public void setDeadline(LocalDateTime deadline) {
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
