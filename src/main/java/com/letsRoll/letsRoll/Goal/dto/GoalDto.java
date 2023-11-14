package com.letsRoll.letsRoll.Goal.dto;

import com.letsRoll.letsRoll.Goal.entity.Goal;
import lombok.Getter;
import lombok.Setter;

import java.time.LocalDate;

@Getter
@Setter
public class GoalDto {
    private Long goalId;
    private Long projectId;
    private String title;
    private String content;
    private LocalDate startDate;
    private LocalDate endDate;
    private boolean isComplete;

    public static GoalDto fromEntity(Goal goal) {
        GoalDto goalDto = new GoalDto();
        goalDto.setProjectId(goal.getProject().getId());
        goalDto.setGoalId(goal.getId());
        goalDto.setTitle(goal.getTitle());
        goalDto.setContent(goal.getContent());
        goalDto.setStartDate(goal.getStartDate());
        goalDto.setEndDate(goal.getEndDate());
        goalDto.setComplete(goal.getIsComplete());
        return goalDto;
    }
}