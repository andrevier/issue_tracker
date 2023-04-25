package andrevier.myissuetracker.myissuetracker.dto;

import java.time.LocalDateTime;

public class ProjectRequest implements ProjectRequestDto {
    
    private Long projectId;
    private String projectName;
    private String projectDescription;
    private LocalDateTime startingDate;
    private LocalDateTime deadline;
    
    public ProjectRequest() {
        this.projectId = null;
        this.projectName = "";
        this.projectDescription = "";
        this.startingDate = LocalDateTime.now();
        this.deadline = null;
    }

    public ProjectRequest(String projectName, String projectDescription, LocalDateTime startingDate,
            LocalDateTime deadline) {
        this.projectId = null;
        this.projectName = projectName;
        this.projectDescription = projectDescription;
        this.startingDate = startingDate;
        this.deadline = deadline;
    }

    @Override
    public Long getProjectId() {
        return this.projectId;
    }
    
    public void setProjectId(Long projectId) {
        this.projectId = projectId;
    }

    @Override
    public String getProjectName() {
        return this.projectName;
    }

    public void setProjectName(String projectName) {
        this.projectName = projectName;
    }

    @Override
    public String getProjectDescription() {
        return this.projectDescription;
    }

    public void setProjectDescription(String projectDescription) {
        this.projectDescription = projectDescription;
    }

    @Override
    public LocalDateTime getStartingDate() {
        return this.startingDate;
    }

    public void setStartingDate(LocalDateTime startingDate) {
        this.startingDate = startingDate;
    }

    @Override
    public LocalDateTime getDeadline() {
        return this.deadline;
    }

    public void setDeadline(LocalDateTime deadline) {
        this.deadline = deadline;
    }

    @Override
    public int hashCode() {
        final int prime = 31;
        int result = 1;
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
        return "ProjectRequest [projectId=" + projectId + ", projectName=" + projectName + ", projectDescription="
                + projectDescription + ", startingDate=" + startingDate + ", deadline=" + deadline + "]";
    }
    
}
