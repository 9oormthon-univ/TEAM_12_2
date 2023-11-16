package com.letsRoll.letsRoll.Todo.dto.res;

import com.letsRoll.letsRoll.Todo.dto.TodoAssembler;
import com.letsRoll.letsRoll.Todo.entity.Todo;
import lombok.Builder;
import lombok.Getter;

import java.util.List;
import java.util.stream.Collectors;

@Getter
public class TodoListResDto {
    private Long groupId;
    private String goalContent;
    private Long todoId;
    private String todoContent;
    private Long todoManagerId;
    private String todoManagerNickName;
    private Boolean isComplete;

    @Builder
    public TodoListResDto(Long groupId, String goalContent, Long todoId, String todoContent, Long todoManagerId, String todoManagerNickName, Boolean isComplete) {
        this.groupId = groupId;
        this.goalContent = goalContent;
        this.todoId = todoId;
        this.todoContent = todoContent;
        this.todoManagerId = todoManagerId;
        this.todoManagerNickName = todoManagerNickName;
        this.isComplete = isComplete;
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
