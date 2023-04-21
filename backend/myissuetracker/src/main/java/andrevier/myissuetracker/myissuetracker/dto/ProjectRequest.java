package andrevier.myissuetracker.myissuetracker.dto;

import java.time.OffsetDateTime;

public class ProjectRequest {
    
    private Long userId;
    private Long projectId;
    private String projectName;
    private String projectDescription;
    private OffsetDateTime startingDate;
    private OffsetDateTime deadline;

    public ProjectRequest() {
        this.userId = null;
        this.projectId = null;
        this.projectName = null;
        this.projectDescription = null;
        this.startingDate = OffsetDateTime.now();
        this.deadline = this.startingDate.plusMonths(6);
    }

    public ProjectRequest(
        Long userId,
        Long projectId, 
        String projectName,
        String projectDescription,
        OffsetDateTime startingDate,
        OffsetDateTime deadline) {
            this.userId = userId;
            this.projectId = projectId;
            this.projectName = projectName;
            this.projectDescription = projectDescription;
            this.startingDate = startingDate;
            this.deadline = deadline;
        }

    public Long getUserId() {
        return userId;
    }
    public void setUserId(Long userId) {
        this.userId = userId;
    }
    public Long getProjectId() {
        return projectId;
    }
    public void setProjectId(Long projectId) {
        this.projectId = projectId;
    }
    public String getProjectName() {
        return projectName;
    }
    public void setProjectName(String projectName) {
        this.projectName = projectName;
    }
    public String getProjectDescription() {
        return projectDescription;
    }
    public void setProjectDescription(String projectDescription) {
        this.projectDescription = projectDescription;
    }
    public OffsetDateTime getStartingDate() {
        return startingDate;
    }
    public void setStartingDate(OffsetDateTime startingDate) {
        this.startingDate = startingDate;
    }
    public OffsetDateTime getDeadline() {
        return deadline;
    }
    public void setDeadline(OffsetDateTime deadline) {
        this.deadline = deadline;
    }
    @Override
    public int hashCode() {
        final int prime = 31;
        int result = 1;
        result = prime * result + ((userId == null) ? 0 : userId.hashCode());
        result = prime * result + ((projectId == null) ? 0 : projectId.hashCode());
        result = prime * result + ((projectName == null) ? 0 : projectName.hashCode());
        result = prime * result + ((projectDescription == null) ? 0 : projectDescription.hashCode());
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
        ProjectRequest other = (ProjectRequest) obj;
        if (userId == null) {
            if (other.userId != null)
                return false;
        } else if (!userId.equals(other.userId))
            return false;
        if (projectId == null) {
            if (other.projectId != null)
                return false;
        } else if (!projectId.equals(other.projectId))
            return false;
        if (projectName == null) {
            if (other.projectName != null)
                return false;
        } else if (!projectName.equals(other.projectName))
            return false;
        if (projectDescription == null) {
            if (other.projectDescription != null)
                return false;
        } else if (!projectDescription.equals(other.projectDescription))
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
    @Override
    public String toString() {
        return "TableRequest [userId=" + userId + ", projectId=" + projectId + ", projectName=" + projectName
                + ", projectDescription=" + projectDescription + ", startingDate=" + startingDate + ", deadline="
                + deadline + "]";
    }
    

}
