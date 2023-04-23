package andrevier.myissuetracker.myissuetracker.model;

import java.util.List;

import com.fasterxml.jackson.annotation.JsonBackReference;
import com.fasterxml.jackson.annotation.JsonManagedReference;

import jakarta.persistence.Column;
import jakarta.persistence.Entity;
import jakarta.persistence.ForeignKey;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.Id;
import jakarta.persistence.JoinColumn;
import jakarta.persistence.ManyToOne;
import jakarta.persistence.OneToMany;
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

    @ManyToOne(targetEntity = PriorityLabel.class)
    @JsonBackReference
    @JoinColumn(
        name = "priority_label_id", 
        referencedColumnName = "priority_label_id",
        nullable = false,
        foreignKey = @ForeignKey(name = "priority_label_id_issue_data_FK")
    )
    private PriorityLabel priorityLabel;
    
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
    
    public Issue(String issueName, String issueDescription, PriorityLabel priorityLabel, Project project) {
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
    
    public Long getPriorityId() {
        return this.priorityLabel.getPriorityId();
    }

    public void setPriorityId(Long priorityId) {
        this.priorityLabel.setPriorityId(priorityId);
    }

    public Long getProjectId() {
        return this.project.getProjectId();
    }

    public void setProjectId(Long projectId) {
        this.project.setProjectId(projectId);
    }

    @Override
    public boolean equals(Object o) {
        if (this == o)
            return true;
        if (!(o instanceof Issue))
            return false;
        Issue other = (Issue) o;
        return (other.getIssueId() != null) && (this.issueId == other.getIssueId());
    }

    @Override
    public int hashCode() {
        return this.issueId.hashCode() * this.issueName.hashCode();
    }

    @Override
    public String toString() {
        return "Issue{id=" + this.issueId + ", name=" + this.issueName 
            + ", description=" + this.issueDescription 
            + ", project name=" + this.project.getProjectName() 
            + ", project id=" + this.project.getProjectId() + "}";
    }

}
