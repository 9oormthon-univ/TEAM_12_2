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


    public static GoalResDto fromEntity(Goal goal) {
        GoalResDto goalDto = new GoalResDto();
        goalDto.setProjectId(goal.getProject().getId());
        goalDto.setGoalId(goal.getId());
        goalDto.setTitle(goal.getTitle());
        goalDto.setContent(goal.getContent());
        goalDto.setStartDate(goal.getStartDate());
        goalDto.setEndDate(goal.getEndDate());
        goalDto.setComplete(goal.getIsComplete());

        List<GoalAgree> goalAgreeList = goal.getGoalAgreeList();
        if (goalAgreeList != null && !goalAgreeList.isEmpty()) {
            goalDto.setGoalAgreeList(GoalAgreeMemberCheckResDto.fromEntities(goalAgreeList));
        }

        List<Todo> todoList = goal.getTodoList();
        if (todoList != null && !todoList.isEmpty()) {
            goalDto.setTodoList(TodoListResDto.fromEntities(todoList));
        }

        return goalDto;
    }

    public static List<GoalResDto> fromEntities(List<Goal> goals) {
        return goals.stream()
                .map(GoalResDto::fromEntity)
                .collect(Collectors.toList());
    }
}