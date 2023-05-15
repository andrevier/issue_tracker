package andrevier.myissuetracker.myissuetracker.model;

import com.fasterxml.jackson.annotation.JsonBackReference;

import jakarta.persistence.Column;
import jakarta.persistence.Entity;
import jakarta.persistence.ForeignKey;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.Id;
import jakarta.persistence.Table;
import jakarta.persistence.ManyToOne;
import jakarta.persistence.SequenceGenerator;
import jakarta.persistence.JoinColumn;
import jakarta.persistence.UniqueConstraint;

@Entity
@Table(name = "manage_issue", uniqueConstraints = {
    @UniqueConstraint(name = "manage_issue_foreign_key", 
    columnNames = {"issue_id", "issue_time_id"})}
) 
public class ManageIssue {

    @Id
    @SequenceGenerator(
        name = "manage_issue_sequence", 
        sequenceName = "manage_issue_sequence", 
        allocationSize = 1
    )
    @GeneratedValue(
        strategy = GenerationType.SEQUENCE, 
        generator = "manage_issue_sequence"
    )
    @Column(name = "manage_issue_id", updatable = false)
    private Long manageIssueId;

    @ManyToOne(targetEntity = Issue.class)
    @JsonBackReference
    @JoinColumn(
        name="issue_id", 
        referencedColumnName="issue_id",
        foreignKey = @ForeignKey(name = "issue_id_manage_issue_FK")
    )
    private Issue issue;

    @ManyToOne(targetEntity = IssueTime.class)
    @JsonBackReference
    @JoinColumn(
        name = "issue_time_id", 
        referencedColumnName="issue_time_id",
        foreignKey = @ForeignKey(name = "issue_time_id_manage_issue_FK")
    )
    private IssueTime issueTime;

    @ManyToOne(targetEntity = UserData.class)
    @JsonBackReference
    @JoinColumn(
        name="user_id", referencedColumnName = "user_id",
        nullable = false, 
        foreignKey = @ForeignKey(name = "user_id_manage_issue_FK")
    )
    private UserData user;

    public ManageIssue(Issue issue, IssueTime issueTime, UserData user) {
        this.issue = issue;
        this.issueTime = issueTime;
        this.user = user;
    }

    public ManageIssue() {

    }

    public void setIssueId(Long issueId) {
        this.manageIssueId = issueId;
    }

    public Long getIssueId() {
        return this.manageIssueId;
    }

    public IssueTime getIssueTime() {
        return issueTime;
    }

    public void setIssueTime(IssueTime issueTime) {
        this.issueTime = issueTime;
    }
    
    public UserData getUser() {
        return user;
    }

    public void setUser(UserData user) {
        this.user = user;
    }
    
    @Override
    public boolean equals(Object o) {
        if (this == o)
            return true;
        if (!(o instanceof ManageIssue))
            return false;
        ManageIssue other = (ManageIssue) o;
        return (this.manageIssueId == other.manageIssueId) 
            && (this.issueTime.getIssueTimeId() == other.issueTime.getIssueTimeId())
            && (this.user.getUserId() == other.user.getUserId());
    }

    @Override
    public int hashCode() {
        return this.manageIssueId.hashCode() * this.user.hashCode() * this.issueTime.hashCode();
    }

    @Override
    public String toString() {
        return "ManageIssue{issueId=" + this.manageIssueId
            + ", issueTimeId=" + this.issueTime.getIssueTimeId()
            + ", userId=" + this.user.getUserId() + "}";
    }

    
}
