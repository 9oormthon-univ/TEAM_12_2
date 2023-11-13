package com.letsRoll.letsRoll.Goal.repository;

import com.letsRoll.letsRoll.Goal.entity.Goal;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.Optional;

@Repository
public interface GoalRepository extends JpaRepository<Goal, Long> {
    Optional<Goal> findGoalByIdAndIsComplete(Long goalId, Boolean isComplete);
    Optional<Goal> findGoalById(Long goalId);

}
