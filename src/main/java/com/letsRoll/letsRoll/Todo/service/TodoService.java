package com.letsRoll.letsRoll.Todo.service;

import com.letsRoll.letsRoll.Goal.entity.Goal;
import com.letsRoll.letsRoll.Goal.repository.GoalRepository;
import com.letsRoll.letsRoll.Member.entity.Member;
import com.letsRoll.letsRoll.Member.repository.MemberRepository;
import com.letsRoll.letsRoll.Project.entity.Project;
import com.letsRoll.letsRoll.Project.repository.ProjectRepository;
import com.letsRoll.letsRoll.Todo.dto.TodoAssembler;
import com.letsRoll.letsRoll.Todo.dto.req.AddTodoReqDto;
import com.letsRoll.letsRoll.Todo.dto.res.*;
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
import java.time.LocalDateTime;
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
    private final ProjectRepository projectRepository;

    // Todo 추가
    public void addTodo(AddTodoReqDto addTodoReqDto) {
        Goal goal = getGoal((addTodoReqDto.getGoalId()));
        Member member = getMember((addTodoReqDto.getMemberId()));

        if (!goal.getProject().getMembers().contains(member)) {
            throw new BaseException(BaseResponseCode.NOT_PROJECT_MEMBER);
        }

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

        return todoManagerRepository.save(todoManager);
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

    // todo 완료 여부 벼경
    public void changeTodoComplete(Long todoId, Long memberId) {
        Todo todo = getTodo(todoId);
        todo.setIsComplete(!todo.getIsComplete());

        Member member = getMember(memberId);

        if (todo.getIsComplete()) { // false -> true 변경
            Optional<TodoEndManager> todoEndManager = todoEndManagerRepository.findByMember(member);

            if (todoEndManager.isEmpty()) { // todoEndManager가 없을 경우
                List<Todo> todoList = new ArrayList<>();
                todoList.add(todo);
                todo.setTodoEndManager(todoEndManagerRepository.save(todoAssembler.toTodoEndManagerEntity(member, todoList)));
                todo.setFinishDate(LocalDateTime.now());
            } else { // todoEndManager가 이미 만들어져있을 경우
                TodoEndManager endManager = todoEndManager.get();
                endManager.getTodoList().add(todo); // endManager의 todoList에 추가
                todo.setTodoEndManager(todoEndManagerRepository.save(endManager));
                todo.setFinishDate(LocalDateTime.now());
            }
        } else { //true -> false 변경
            TodoEndManager todoEndManager = todo.getTodoEndManager();
            todoEndManager.getTodoList().remove(todo);

            todo.setTodoEndManager(null);
            todo.setFinishDate(null);

            todoEndManagerRepository.save(todoEndManager);
        }
        todoRepository.save(todo);
    }

    // 기한 지난 Todo있는지 체크
    public Boolean checkOverdueTodo() {
        List<Todo> todoList = todoRepository.findTodosByIsCompleteIsFalseAndEndDateLessThan(LocalDate.now());
        return !todoList.isEmpty();
    }

    // 내게 할당된 Todo 조회
    public List<MyTodoResDto> getMyTodo(Long memberId) {
        Member member = getMember(memberId);
        TodoManager todoManager = todoManagerRepository.findByMember(member)
                .orElseThrow(() -> new BaseException(BaseResponseCode.NOT_FOUND_TODOMANAGER));
        List<Todo> todoList = todoRepository.findTodosByTodoManagerOrderByCreatedDate(todoManager);

        List<MyTodoResDto> myTodoResDtoList = new ArrayList<>();
        for (Todo todo : todoList) {
            myTodoResDtoList.add(todoAssembler.toMyTodoResDtoEntity(todo));
        }
        return myTodoResDtoList;
    }

    // 월별 Todo 완료/미완료 날짜 조회
    public MonthlyCheckTodoListResDto getMonthlyTodo(String yearMonth) {
        int year = Integer.parseInt(yearMonth.split("-")[0]);
        int month = Integer.parseInt(yearMonth.split("-")[1]);

        List<LocalDate> completeDate = todoRepository.findCompleteDate(year, month);
        List<LocalDate> inCompleteDate = todoRepository.findInCompleteDate(year, month);

        return todoAssembler.toMonthlyCheckEntity(completeDate, inCompleteDate);
    }

    public List<TodoListResDto> getTodoListByGoal(Long goalId) {
        Goal goal = getGoal(goalId);

        List<TodoListResDto> todoListResDto = new ArrayList<>();
        List<Todo> todoList = todoRepository.findTodosByGoalOrderByCreatedDate(goal);

        for (Todo todo : todoList) {
            todoListResDto.add(todoAssembler.toDateTodoListResDtoEntity(todo));
        }

        return todoListResDto;
    }

    public AllReportTodo getReportTodo(Long projectId) {
        Project project = projectRepository.findProjectById(projectId)
                .orElseThrow(() -> new BaseException(BaseResponseCode.NOT_FOUND_PROJECT));
        List<Member> members = memberRepository.findMemberByProject(project);
        System.out.println("project = " + project.getId());
        System.out.println("members = " + members);
        int completeCount;
        int managedCount;
        int allCount = 0;
        List<ReportTodo> reportTodoList = new ArrayList<>();
        for (Member member : members) {
            System.out.println("member.getNickname() = " + member.getNickname());
            Optional<TodoManager> todoManager = todoManagerRepository.findByMember(member);
            Optional<TodoEndManager> todoEndManager = todoEndManagerRepository.findByMember(member);
            if (todoManager.isEmpty()) {
                managedCount = 0;
            } else {
                List<Todo> todoList = todoRepository.findTodosByTodoManagerOrderByCreatedDate(todoManager.get());
                managedCount = todoList.size();
            }
            allCount += managedCount;
            completeCount = todoEndManager.map(endManager -> endManager.getTodoList().size()).orElse(0);
            reportTodoList.add(todoAssembler.reportTodo(member, completeCount, managedCount));
        }
        return AllReportTodo.builder()
                .todoCount(allCount)
                .reportTodoList(reportTodoList).build();

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
