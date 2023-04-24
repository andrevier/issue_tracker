package andrevier.myissuetracker.myissuetracker.model;

import java.util.List;

import com.fasterxml.jackson.annotation.JsonBackReference;
import com.fasterxml.jackson.annotation.JsonManagedReference;

import jakarta.persistence.CascadeType;
import jakarta.persistence.Column;
import jakarta.persistence.Entity;
import jakarta.persistence.ForeignKey;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.Id;
import jakarta.persistence.JoinColumn;
import jakarta.persistence.ManyToOne;
import jakarta.persistence.OneToMany;
import jakarta.persistence.OneToOne;
import jakarta.persistence.SequenceGenerator;
import jakarta.persistence.Table;

@Entity
@Table(name = "issue_data")
public class Issue {
    @Id
    @SequenceGenerator(
        name = "issue_data_sequence", 
        sequenceName = "issue_data_sequence",
        allocationSize = 1
    )
    @GeneratedValue(
        strategy = GenerationType.SEQUENCE,
        generator = "issue_data_sequence"
    )
    @Column(name = "issue_id", updatable = false)
    private Long issueId;

    @Column(name = "issue_name",nullable = false)
    private String issueName;

    @Column(name = "issue_description", columnDefinition = "TEXT")
    private String issueDescription;

    @Column(name = "priority_label")
    private String priorityLabel;
    
    @ManyToOne(targetEntity = Project.class)
    @JsonBackReference
    @JoinColumn(
        name = "project_id", 
        referencedColumnName = "project_id",
        nullable = false,
        foreignKey = @ForeignKey(name = "project_id_issue_data_FK")
    )
    private Project project;

    @OneToMany(orphanRemoval=true, mappedBy="issue")
    @JsonManagedReference
    private List<ManageIssue> manageIssue;
    
    public Issue(String issueName, String issueDescription, String priorityLabel, Project project) {
        this.issueName = issueName;
        this.issueDescription = issueDescription;
        this.priorityLabel = priorityLabel;
        this.project = project;
    }

    public Long getIssueId() {
        return this.issueId;
    }

    public void setIssueId(Long issueId) {
        this.issueId = issueId;
    }

    public String getIssueName() {
        return this.issueName;
    }

    public void setIssueName(String issueName) {
        this.issueName = issueName;
    }

    public String getIssueDescription() {
        return this.issueDescription;
    }

    public void setIssueDescription(String issueDescription) {
        this.issueDescription = issueDescription;
    }
    
    public String getPriorityLabel() {
        return this.priorityLabel;
    }

    public void setPriorityLabel(String priorityLabel) {
        this.priorityLabel = priorityLabel;
    }
    
    public Project getProject() {
        return project;
    }

    public void setProject(Project project) {
        this.project = project;
    }

    @Override
    public int hashCode() {
        final int prime = 31;
        int result = 1;
        result = prime * result + ((issueId == null) ? 0 : issueId.hashCode());
        result = prime * result + ((issueName == null) ? 0 : issueName.hashCode());
        result = prime * result + ((issueDescription == null) ? 0 : issueDescription.hashCode());
        result = prime * result + ((priorityLabel == null) ? 0 : priorityLabel.hashCode());
        result = prime * result + ((project == null) ? 0 : project.hashCode());
        result = prime * result + ((manageIssue == null) ? 0 : manageIssue.hashCode());
        return result;
    }

    @Override
    public boolean equals(Object obj) {
        if (this == obj)
            return true;
        if (obj == null)
            return false;
        if (getClass() != obj.getClass())
            return false;
        Issue other = (Issue) obj;
        if (issueId == null) {
            if (other.issueId != null)
                return false;
        } else if (!issueId.equals(other.issueId))
            return false;
        if (issueName == null) {
            if (other.issueName != null)
                return false;
        } else if (!issueName.equals(other.issueName))
            return false;
        if (issueDescription == null) {
            if (other.issueDescription != null)
                return false;
        } else if (!issueDescription.equals(other.issueDescription))
            return false;
        if (priorityLabel == null) {
            if (other.priorityLabel != null)
                return false;
        } else if (!priorityLabel.equals(other.priorityLabel))
            return false;
        if (project == null) {
            if (other.project != null)
                return false;
        } else if (!project.equals(other.project))
            return false;
        if (manageIssue == null) {
            if (other.manageIssue != null)
                return false;
        } else if (!manageIssue.equals(other.manageIssue))
            return false;
        return true;
    }



    @Override
    public String toString() {
        return "Issue{id=" + this.issueId + ", name=" + this.issueName 
            + ", description=" + this.issueDescription 
            + ", project name=" + this.project.getProjectName() 
            + ", project id=" + this.project.getProjectId() + "}";
    }

}
