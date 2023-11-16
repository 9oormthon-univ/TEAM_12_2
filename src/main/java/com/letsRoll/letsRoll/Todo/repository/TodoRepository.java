package com.letsRoll.letsRoll.Todo.repository;

import com.letsRoll.letsRoll.Todo.entity.Todo;
import com.letsRoll.letsRoll.Todo.entity.TodoManager;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;
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

    // 월별로 완료하지 못한 todo가 있는 endDate들을 조회
    @Query("SELECT distinct todo.endDate FROM Todo todo " +
            "WHERE todo.isComplete = false AND " +
            "YEAR(todo.endDate) = :year AND MONTH(todo.endDate) = :month " +
            "ORDER BY todo.endDate ASC")
    List<LocalDate> findInCompleteDate(@Param("year") int year, @Param("month") int month);

    @Query("SELECT distinct todo.endDate FROM Todo todo " +
            "WHERE todo.isComplete = true AND " +
            "YEAR(todo.endDate) = :year AND MONTH(todo.endDate) = :month " +
            "ORDER BY todo.endDate ASC")
    List<LocalDate> findCompleteDate(@Param("year") int year, @Param("month") int month);
}