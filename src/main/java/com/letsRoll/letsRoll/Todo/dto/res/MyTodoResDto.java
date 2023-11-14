package com.letsRoll.letsRoll.Todo.dto.res;

import lombok.Builder;
import lombok.Getter;

@Getter
@Builder
public class MyTodoResDto {
    private Long goalId;
    private String goalContent;
    private Long todoId;
    private String todoContent;
    private Long todoManagerId;
    private String todoManagerNickName;
    private Boolean isComplete;
}
