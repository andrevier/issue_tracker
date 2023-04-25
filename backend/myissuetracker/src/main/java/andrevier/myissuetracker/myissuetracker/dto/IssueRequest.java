package andrevier.myissuetracker.myissuetracker.dto;

import java.time.LocalDateTime;

public class IssueRequest implements IssueRequestDto {
    private Long issueId;
    private String issueName;
    private String issueDescription;
    private String priorityLabel;
    private LocalDateTime startingDate;
    private LocalDateTime deadline;
    
    public IssueRequest() {
        this.issueId = null;
        this.issueName = "";
        this.issueDescription = "";
        this.startingDate = LocalDateTime.now();
        this.deadline = null;
    }

    public IssueRequest(String issueName, String issueDescription, String priorityLabel, LocalDateTime startingDate,
            LocalDateTime deadline) {
        this.issueId = null;
        this.issueName = issueName;
        this.issueDescription = issueDescription;
        this.priorityLabel = priorityLabel;
        this.startingDate = startingDate;
        this.deadline = deadline;
    }

    @Override
    public Long getIssueId() {
        return this.issueId;
    }

    @Override
    public String getIssueName() {
        return this.issueName;
    }

    @Override
    public String getIssueDescription() {
        return this.issueDescription;
    }

    @Override
    public String getPriorityLabel() {
        return this.priorityLabel;
    }

    @Override
    public LocalDateTime getStartingDate() {
        return this.startingDate;
    }

    @Override
    public LocalDateTime getDeadline() {
        return this.deadline;
    }

    public void setIssueId(Long issueId) {
        this.issueId = issueId;
    }

    public void setIssueName(String issueName) {
        this.issueName = issueName;
    }

    public void setIssueDescription(String issueDescription) {
        this.issueDescription = issueDescription;
    }

    public void setPriorityLabel(String priorityLabel) {
        this.priorityLabel = priorityLabel;
    }

    public void setStartingDate(LocalDateTime startingDate) {
        this.startingDate = startingDate;
    }

    public void setDeadline(LocalDateTime deadline) {
        this.deadline = deadline;
    }

    @Override
    public int hashCode() {
        final int prime = 31;
        int result = 1;
        result = prime * result + ((issueId == null) ? 0 : issueId.hashCode());
        result = prime * result + ((issueName == null) ? 0 : issueName.hashCode());
        result = prime * result + ((issueDescription == null) ? 0 : issueDescription.hashCode());
        result = prime * result + ((priorityLabel == null) ? 0 : priorityLabel.hashCode());
        result = prime * result + ((startingDate == null) ? 0 : startingDate.hashCode());
        result = prime * result + ((deadline == null) ? 0 : deadline.hashCode());
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
        IssueRequest other = (IssueRequest) obj;
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
        if (startingDate == null) {
            if (other.startingDate != null)
                return false;
        } else if (!startingDate.equals(other.startingDate))
            return false;
        if (deadline == null) {
            if (other.deadline != null)
                return false;
        } else if (!deadline.equals(other.deadline))
            return false;
        return true;
    }
    
}
