package com.letsRoll.letsRoll.Todo.dto;

import com.letsRoll.letsRoll.Goal.entity.Goal;
import com.letsRoll.letsRoll.Member.entity.Member;
import com.letsRoll.letsRoll.Todo.dto.res.MonthlyCheckTodoListResDto;
import com.letsRoll.letsRoll.Todo.dto.res.MyTodoResDto;
import com.letsRoll.letsRoll.Todo.dto.res.TodoListResDto;
import com.letsRoll.letsRoll.Todo.entity.Todo;
import com.letsRoll.letsRoll.Todo.entity.TodoEndManager;
import com.letsRoll.letsRoll.Todo.entity.TodoManager;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Component;

import java.time.LocalDate;
import java.util.List;

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
                .goalId(todo.getGoal().getId())
                .goalContent(todo.getGoal().getContent())
                .todoId(todo.getId())
                .todoContent(todo.getContent())
                .todoManagerMemberId(todo.getTodoManager().getMember().getId())
                .todoManagerNickName(todo.getTodoManager().getMember().getNickname())
                .isComplete(todo.getIsComplete())
                .todoEndDate(todo.getEndDate())
                .build();
    }

    public MyTodoResDto toMyTodoResDtoEntity(Todo todo) {
        return MyTodoResDto.builder()
                .goalId(todo.getGoal().getId())
                .goalContent(todo.getGoal().getContent())
                .todoId(todo.getId())
                .todoContent(todo.getContent())
                .todoManagerMemberId(todo.getTodoManager().getMember().getId())
                .todoManagerNickName(todo.getTodoManager().getMember().getNickname())
                .isComplete(todo.getIsComplete())
                .build();
    }

    public MonthlyCheckTodoListResDto toMonthlyCheckEntity(List<LocalDate> completeDates, List<LocalDate> inCompleteDates) {
        return MonthlyCheckTodoListResDto.builder()
                .allCompleteDateList(completeDates)
                .inCompleteDateList(inCompleteDates)
                .build();
    }
}
