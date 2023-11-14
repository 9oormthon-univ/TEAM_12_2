package com.letsRoll.letsRoll.Todo.service;

import com.letsRoll.letsRoll.Goal.entity.Goal;
import com.letsRoll.letsRoll.Goal.repository.GoalRepository;
import com.letsRoll.letsRoll.Member.entity.Member;
import com.letsRoll.letsRoll.Member.repository.MemberRepository;
import com.letsRoll.letsRoll.Todo.dto.TodoAssembler;
import com.letsRoll.letsRoll.Todo.dto.req.AddTodoReqDto;
import com.letsRoll.letsRoll.Todo.dto.res.MyTodoResDto;
import com.letsRoll.letsRoll.Todo.dto.res.TodoListResDto;
import com.letsRoll.letsRoll.Todo.entity.Todo;
import com.letsRoll.letsRoll.Todo.entity.TodoEndManager;
import com.letsRoll.letsRoll.Todo.entity.TodoManager;
import com.letsRoll.letsRoll.Todo.repository.TodoEndManagerRepository;
import com.letsRoll.letsRoll.Todo.repository.TodoManagerRepository;
import com.letsRoll.letsRoll.Todo.repository.TodoRepository;
import com.letsRoll.letsRoll.global.exception.BaseException;
import com.letsRoll.letsRoll.global.exception.BaseResponseCode;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

import java.time.LocalDate;
import java.util.ArrayList;
import java.util.List;
import java.util.Optional;

@Service
@RequiredArgsConstructor
public class TodoService {
    private final TodoRepository todoRepository;
    private final TodoManagerRepository todoManagerRepository;
    private final TodoEndManagerRepository todoEndManagerRepository;
    private final GoalRepository goalRepository;
    private final MemberRepository memberRepository;
    private final TodoAssembler todoAssembler;

    // Todo 추가
    public void addTodo(AddTodoReqDto addTodoReqDto) {
        Goal goal = getGoal((addTodoReqDto.getGoalId()));
        Member member = getMember((addTodoReqDto.getMemberId()));
        Optional<TodoManager> todoManagerOptional = todoManagerRepository.findByMember(member);
        if (todoManagerOptional.isEmpty()) {
            todoRepository.save(todoAssembler.toTodoEntity(goal, addTodoManager(member), addTodoReqDto.getContent(), addTodoReqDto.getEndDate()));
        } else {
            todoRepository.save(todoAssembler.toTodoEntity(goal, todoManagerOptional.get(), addTodoReqDto.getContent(), addTodoReqDto.getEndDate()));
        }
    }

    // TodoManager 추가
    public TodoManager addTodoManager(Member member) {
        TodoManager todoManager = todoAssembler.toTodoManagerEntity(member);
        todoManagerRepository.save(todoManager);
        return todoManager;
    }

    // 날짜에 따른 TodoList 조회
    public List<TodoListResDto> getTodoListByDate(LocalDate date) {
        List<TodoListResDto> todoListResDto = new ArrayList<>();
        List<Todo> todoList = todoRepository.findTodosByEndDateGreaterThanEqualOrderByCreatedDateAsc(date);

        for (Todo todo : todoList) {
            todoListResDto.add(todoAssembler.toDateTodoListResDtoEntity(todo));
        }
        return todoListResDto;
    }

    // 완료하지 않은 TodoList 조회
    public List<TodoListResDto> getIncompleteTodoList() {
        List<TodoListResDto> todoListResDto = new ArrayList<>();
        List<Todo> todoList = todoRepository.findTodosByIsCompleteIsFalseOrderByCreatedDateAsc();

        for (Todo todo : todoList) {
            todoListResDto.add(todoAssembler.toDateTodoListResDtoEntity(todo));
        }
        return todoListResDto;
    }

    public void changeTodoComplete(Long todoId, Long memberId) {
        Todo todo = getTodo(todoId);
        todo.setIsComplete(!todo.getIsComplete());
        Member member = getMember(memberId);

        if (todo.getIsComplete()) { // false -> true 변경
            TodoEndManager todoEndManager = todoEndManagerRepository.save(todoAssembler.toTodoEndManagerEntity(member));
            todo.setTodoEndManager(todoEndManager);
            todo.setFinishDate(LocalDate.now());
        } else { //true -> false 변경
            TodoEndManager todoEndManager = todo.getTodoEndManager();
            todo.setTodoEndManager(null);
            todo.setFinishDate(null);
            todoEndManagerRepository.delete(todoEndManager);
        }
        todoRepository.save(todo);
    }

    public Boolean checkOverdueTodo() {
        List<Todo> todoList = todoRepository.findTodosByIsCompleteIsFalseAndEndDateLessThan(LocalDate.now());
        return !todoList.isEmpty();
    }

    public List<MyTodoResDto> getMyTodo(Long memberId) {
        Member member = getMember(memberId);
        TodoManager todoManager = todoManagerRepository.findByMember(member)
                .orElseThrow(()->new BaseException(BaseResponseCode.NOT_FOUND_TODOMANAGER));
        List<Todo> todoList = todoRepository.findTodosByTodoManagerOrderByCreatedDate(todoManager);

        List<MyTodoResDto> myTodoResDtoList = new ArrayList<>();
        for (Todo todo : todoList) {
            myTodoResDtoList.add(todoAssembler.toMyTodoResDtoEntity(todo));
        }
        return myTodoResDtoList;
    }

    public Goal getGoal(Long goalId) {
        return goalRepository.findById(goalId)
                .orElseThrow(() -> new BaseException(BaseResponseCode.NOT_FOUND_GOAL));
    }

    public Todo getTodo(Long todoId) {
        return todoRepository.findTodoById(todoId)
                .orElseThrow(() -> new BaseException(BaseResponseCode.NOT_FOUND_TODO));
    }

    public Member getMember(Long memberId) {
        return memberRepository.findMemberById(memberId)
                .orElseThrow(() -> new BaseException(BaseResponseCode.NOT_FOUND_MEMBER));
    }
}
