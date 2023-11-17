package com.letsRoll.letsRoll.Goal.dto.res;

import com.letsRoll.letsRoll.Goal.entity.Goal;
import com.letsRoll.letsRoll.Goal.entity.GoalAgree;
import com.letsRoll.letsRoll.Todo.dto.res.TodoListResDto;
import com.letsRoll.letsRoll.Todo.entity.Todo;
import lombok.Getter;
import lombok.Setter;

import java.time.LocalDate;
import java.util.List;
import java.util.stream.Collectors;

@Getter
@Setter
public class GoalResDto {
    private Long goalId;
    private Long projectId;
    private String title;
    private String content;
    private LocalDate startDate;
    private LocalDate endDate;
    private boolean isComplete;
    private List<GoalAgreeMemberCheckResDto> goalAgreeList;
    private List<TodoListResDto> todoList;

    private float progress;
}