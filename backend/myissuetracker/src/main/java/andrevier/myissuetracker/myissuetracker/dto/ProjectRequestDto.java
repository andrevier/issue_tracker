package andrevier.myissuetracker.myissuetracker.dto;

import java.time.LocalDateTime;

public interface ProjectRequestDto {
    Long getUserId();
    Long getProjectId();
    Long getProjectTimeId();
    String getProjectName();
    String getProjectDescription();
    LocalDateTime getStartingDate();
    LocalDateTime getDeadline();
}
