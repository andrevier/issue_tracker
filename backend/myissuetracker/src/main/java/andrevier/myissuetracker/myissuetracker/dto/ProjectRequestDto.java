package andrevier.myissuetracker.myissuetracker.dto;

import java.time.LocalDateTime;

public interface ProjectRequestDto {

    Long getProjectId();
    String getProjectName();
    String getProjectDescription();
    LocalDateTime getStartingDate();
    LocalDateTime getDeadline();
}
