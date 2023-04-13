package andrevier.myissuetracker.myissuetracker.model;

import java.io.Serializable;
import jakarta.persistence.Embeddable;
import jakarta.persistence.JoinColumn;
import jakarta.persistence.ManyToOne;
import jakarta.persistence.ForeignKey;

@Embeddable
public class ManageIssueId implements Serializable{
    @ManyToOne(targetEntity = Issue.class)
    @JoinColumn(
        name="issue_id", 
        referencedColumnName="issue_id",
        foreignKey = @ForeignKey(name = "issue_id_manage_issue_FK")
    )
    private Issue issue;

    @ManyToOne(targetEntity = IssueTime.class)
    @JoinColumn(
        name = "issue_time_id", 
        referencedColumnName="issue_time_id",
        foreignKey = @ForeignKey(name = "issue_time_id_manage_issue_FK")
    )
    private IssueTime issueTime;

    public ManageIssueId(Long issueId, Long issueTimeId) {
        this.issue.setIssueId(issueId);
        this.issueTime.setIssueTimeId(issueTimeId);
    }

    public ManageIssueId() {}

    public Long getIssueId() {
        return this.issue.getIssueId();
    }

    public void setIssueId(Long issueId) {
        this.issue.setIssueId(issueId);
    }

    public Long getIssueTimeId() {
        return this.issueTime.getIssueTimeId();
    }

    public void setIssueTimeId(Long issueTimeId) {
        this.issueTime.setIssueTimeId(issueTimeId);
    }

    @Override
    public boolean equals(Object o) {
        if(this == o)
            return true;
        if(!(o instanceof ManageIssueId))
            return false;
        ManageIssueId other = (ManageIssueId) o;
        return (other.getIssueId() != null && other.getIssueId() == this.getIssueId())
            && (other.getIssueTimeId() != null && other.getIssueTimeId() == this.getIssueTimeId());
    }

    @Override
    public int hashCode() {
        return this.getIssueId().hashCode() * this.getIssueTimeId().hashCode();
    }


    
}
