package andrevier.myissuetracker.myissuetracker.dto;

import java.time.LocalDateTime;

public interface IssueRequestDto {
    Long getIssueId();
    String getIssueName();
    String getIssueDescription();
    String getPriorityLabel();
    LocalDateTime getStartingDate();
    LocalDateTime getDeadline();
}
