package com.letsRoll.letsRoll.Project.dto.res;

import lombok.Builder;
import lombok.Getter;

@Getter
@Builder
public class FinishProjectResDto {
    private Long projectId;
    private String projectTitle;
    private String description;
}
