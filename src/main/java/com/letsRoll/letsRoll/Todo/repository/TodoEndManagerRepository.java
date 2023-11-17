package com.letsRoll.letsRoll.Todo.repository;

import com.letsRoll.letsRoll.Member.entity.Member;
import com.letsRoll.letsRoll.Todo.entity.TodoEndManager;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.Optional;

@Repository
public interface TodoEndManagerRepository extends JpaRepository<TodoEndManager, Long> {
    Optional<TodoEndManager> findByMember(Member member);
}
