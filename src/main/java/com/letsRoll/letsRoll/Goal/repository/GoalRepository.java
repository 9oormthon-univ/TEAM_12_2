package com.letsRoll.letsRoll.Goal.repository;

import com.letsRoll.letsRoll.Goal.entity.Goal;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.List;
import java.util.Optional;

public interface GoalRepository extends JpaRepository<Goal, Long> {

    Optional<Goal> findById(Long id);

    List<Goal> findByProject_Id(Long projectId);
}