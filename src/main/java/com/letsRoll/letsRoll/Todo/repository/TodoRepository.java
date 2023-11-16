package com.letsRoll.letsRoll.Todo.repository;

import com.letsRoll.letsRoll.Goal.entity.Goal;
import com.letsRoll.letsRoll.Todo.entity.Todo;
import com.letsRoll.letsRoll.Todo.entity.TodoManager;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.time.LocalDate;
import java.util.List;
import java.util.Optional;

@Repository
public interface TodoRepository extends JpaRepository<Todo, Long> {

    Optional<Todo> findTodoById(Long id);

    // endDate가 date보다 크거나 같을 때의 todo list 조회
    List<Todo> findTodosByEndDateGreaterThanEqualOrderByCreatedDateAsc(LocalDate date);

    // 완성되지 않은 todoList 조회
    List<Todo> findTodosByIsCompleteIsFalseOrderByCreatedDateAsc();

    // 기한이 지난 미완료 todo들 조회
    List<Todo> findTodosByIsCompleteIsFalseAndEndDateLessThan(LocalDate now);

    // 내 todo들 생성순으로 조회
    List<Todo> findTodosByTodoManagerOrderByCreatedDate(TodoManager todoManager);

    // goal에 대한 todo 찾고 생성순으로 조회
    List<Todo> findTodosByGoalOrderByCreatedDate(Goal goal);
}
