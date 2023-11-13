package com.letsRoll.letsRoll.Todo.repository;

import com.letsRoll.letsRoll.Member.entity.Member;
import com.letsRoll.letsRoll.Todo.entity.TodoManager;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.Optional;

public interface TodoManagerRepository extends JpaRepository<TodoManager, Long> {

    Optional<TodoManager> findByMember(Member member);
}
