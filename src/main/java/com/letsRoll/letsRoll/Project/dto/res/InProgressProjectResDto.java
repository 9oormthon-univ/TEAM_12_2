package com.letsRoll.letsRoll.Project.dto.res;

import lombok.Builder;
import lombok.Getter;

@Getter
@Builder
public class InProgressProjectResDto {
    private Long projectId;
    private String projectTitle;
    private String description;
    private Float progress;
}
