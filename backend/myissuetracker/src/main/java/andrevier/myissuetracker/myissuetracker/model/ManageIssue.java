package andrevier.myissuetracker.myissuetracker.model;

import jakarta.persistence.EmbeddedId;
import jakarta.persistence.Entity;
import jakarta.persistence.ForeignKey;
import jakarta.persistence.Table;
import jakarta.persistence.ManyToOne;
import jakarta.persistence.JoinColumn;
import jakarta.persistence.UniqueConstraint;

@Entity
@Table(name = "manage_issue", uniqueConstraints = {
    @UniqueConstraint(name = "manage_issue_foreign_key", 
    columnNames = {"issue_id", "issue_time_id"})}
) 
public class ManageIssue {
    @EmbeddedId
    private ManageIssueId manageIssueId;

    @ManyToOne(targetEntity = User.class)
    @JoinColumn(
        name="user_id", referencedColumnName = "user_id",
        nullable = false, 
        foreignKey = @ForeignKey(name = "user_id_manage_issue_FK")
    )
    private User user;

    public ManageIssue() {

    }

    public void setIssueId(Long IssueId) {
        this.manageIssueId.setIssueId(IssueId);
    }

    public Long getIssueId() {
        return this.manageIssueId.getIssueId();
    }

    public Long getIssueTimeId() {
        return this.manageIssueId.getIssueTimeId();
    }

    public void setIssueTimeId(Long IssueTimeId) {
        this.manageIssueId.setIssueTimeId(IssueTimeId);
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
        if (!(o instanceof ManageIssue))
            return false;
        ManageIssue other = (ManageIssue) o;
        return (this.manageIssueId.getIssueId() == other.manageIssueId.getIssueId()) 
            && (this.manageIssueId.getIssueTimeId() == other.manageIssueId.getIssueTimeId())
            && (this.user.getUserId() == other.user.getUserId());
    }

    @Override
    public int hashCode() {
        return this.manageIssueId.hashCode() * this.user.hashCode();
    }

    @Override
    public String toString() {
        return "ManageIssue{issueId=" + this.manageIssueId.getIssueId()
            + ", issueTimeId=" + this.manageIssueId.getIssueTimeId()
            + ", userId=" + this.user.getUserId() + "}";
    }

    
}
