package com.letsRoll.letsRoll.Todo.dto;

import com.letsRoll.letsRoll.Goal.entity.Goal;
import com.letsRoll.letsRoll.Member.entity.Member;
import com.letsRoll.letsRoll.Todo.dto.res.MyTodoResDto;
import com.letsRoll.letsRoll.Todo.dto.res.TodoListResDto;
import com.letsRoll.letsRoll.Todo.dto.res.TodoResDto;
import com.letsRoll.letsRoll.Todo.entity.Todo;
import com.letsRoll.letsRoll.Todo.entity.TodoEndManager;
import com.letsRoll.letsRoll.Todo.entity.TodoManager;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Component;

import java.time.LocalDate;

@Component
@RequiredArgsConstructor
public class TodoAssembler {

    public Todo toTodoEntity(Goal goal, TodoManager todoManager, String content, LocalDate endDate) {

        return Todo.builder()
                .goal(goal)
                .todoManager(todoManager)
                .content(content)
                .endDate(endDate)
                .build();
    }

    public TodoResDto toTodoResDto(Todo todo) {
        return TodoResDto.builder()
                .goalId(todo.getGoal().getId())
                .content(todo.getContent())
                .endDate(todo.getEndDate())
                .managerId(todo.getTodoManager().getMember().getId())
                .endManagerId(todo.getTodoEndManager().getMember().getId())
                .build();
    }

    public TodoManager toTodoManagerEntity(Member member) {
        return TodoManager.builder()
                .member(member)
                .build();
    }
    public TodoEndManager toTodoEndManagerEntity(Member member) {
        return TodoEndManager.builder()
                .member(member)
                .build();
    }

    public TodoListResDto toDateTodoListResDtoEntity(Todo todo) {
        return TodoListResDto.builder()
                .groupId(todo.getGoal().getId())
                .goalContent(todo.getGoal().getContent())
                .todoId(todo.getId())
                .todoContent(todo.getContent())
                .todoManagerId(todo.getTodoManager().getMember().getId())
                .todoManagerNickName(todo.getTodoManager().getMember().getNickname())
                .isComplete(todo.getIsComplete())
                .build();
    }

    public MyTodoResDto toMyTodoResDtoEntity(Todo todo) {
        return MyTodoResDto.builder()
                .goalId(todo.getGoal().getId())
                .goalContent(todo.getGoal().getContent())
                .todoId(todo.getId())
                .todoContent(todo.getContent())
                .todoManagerId(todo.getTodoManager().getMember().getId())
                .todoManagerNickName(todo.getTodoManager().getMember().getNickname())
                .isComplete(todo.getIsComplete())
                .build();
    }
}
