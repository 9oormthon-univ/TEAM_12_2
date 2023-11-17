package com.letsRoll.letsRoll.Todo.dto.res;

import com.letsRoll.letsRoll.Todo.dto.TodoAssembler;
import com.letsRoll.letsRoll.Todo.entity.Todo;
import lombok.Builder;
import lombok.Getter;

import java.time.LocalDate;
import java.time.YearMonth;

import java.util.List;
import java.util.stream.Collectors;


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
    public TodoListResDto(Long goalId, String goalContent, Long todoId, String todoContent, Long todoManagerMemberId,
                          String todoManagerNickName, Boolean isComplete, LocalDate todoEndDate) {
        this.goalId = goalId;
        this.goalContent = goalContent;
        this.todoId = todoId;
        this.todoContent = todoContent;
        this.todoManagerMemberId = todoManagerMemberId;
        this.todoManagerNickName = todoManagerNickName;
        this.isComplete = isComplete;
        this.todoEndDate = todoEndDate;
    }


    public static List<TodoListResDto> fromEntities(List<Todo> todoList) {
        return todoList.stream()
                .map(TodoListResDto::fromEntity)
                .collect(Collectors.toList());
    }

    public static TodoListResDto fromEntity(Todo todo) {
        TodoAssembler todoAssembler = new TodoAssembler();
        return todoAssembler.toDateTodoListResDtoEntity(todo);
    }
}
