package com.letsRoll.letsRoll.Goal.dto.res;

import lombok.Builder;
import lombok.Getter;

import java.time.LocalDate;

@Getter
@Builder
public class ReportGoalResDto {
    private Long goalId;
    private Long projectId;
    private String title;
    private LocalDate startDate;
    private LocalDate endDate;
    private LocalDate finishDate;
    private boolean isComplete;
}

