package andrevier.myissuetracker.myissuetracker.dto;

import java.time.Instant;

public interface ProjectRequestDto {
    Long getUserId();
    Long getProjectId();
    Long getProjectTimeId();
    String getProjectName();
    String getProjectDescription();
    Instant getStartingDate();
    Instant getDeadline();
}
