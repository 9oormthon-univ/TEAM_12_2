package com.letsRoll.letsRoll.Goal.dto.res;

import com.letsRoll.letsRoll.Goal.entity.Goal;
import com.letsRoll.letsRoll.Project.entity.Project;
import com.letsRoll.letsRoll.Todo.dto.res.TodoListResDto;
import lombok.Builder;
import lombok.Getter;

import java.time.LocalDate;
import java.util.List;

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

