package com.letsRoll.letsRoll.Todo.dto.res;

import lombok.Builder;
import lombok.Getter;

import java.time.LocalDate;
import java.time.YearMonth;

@Getter
public class TodoListResDto {
    private Long goalId;
    private String goalContent;
    private Long todoId;
    private String todoContent;
    private Long todoManagerMemberId;
    private String todoManagerNickName;
    private Boolean isComplete;
    private LocalDate todoEndDate;

    @Builder
    public TodoListResDto(Long goalId, String goalContent, Long todoId, String todoContent, Long todoManagerMemberId, String todoManagerNickName, Boolean isComplete, LocalDate todoEndDate) {
        this.goalId = goalId;
        this.goalContent = goalContent;
        this.todoId = todoId;
        this.todoContent = todoContent;
        this.todoManagerMemberId = todoManagerMemberId;
        this.todoManagerNickName = todoManagerNickName;
        this.isComplete = isComplete;
        this.todoEndDate = todoEndDate;
    }
}
