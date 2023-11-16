package com.letsRoll.letsRoll.Todo.dto.res;

import lombok.Builder;
import lombok.Getter;

@Getter
public class TodoListResDto {
    private Long groupId;
    private String goalContent;
    private Long todoId;
    private String todoContent;
    private Long todoManagerMemberId;
    private String todoManagerNickName;
    private Boolean isComplete;

    @Builder
    public TodoListResDto(Long groupId, String goalContent, Long todoId, String todoContent, Long todoManagerMemberId, String todoManagerNickName, Boolean isComplete) {
        this.groupId = groupId;
        this.goalContent = goalContent;
        this.todoId = todoId;
        this.todoContent = todoContent;
        this.todoManagerMemberId = todoManagerMemberId;
        this.todoManagerNickName = todoManagerNickName;
        this.isComplete = isComplete;
    }
}
