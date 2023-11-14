package com.letsRoll.letsRoll.Goal.repository;

import com.letsRoll.letsRoll.Goal.entity.Goal;
import com.letsRoll.letsRoll.Goal.entity.GoalAgree;
import com.letsRoll.letsRoll.Member.entity.Member;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.Optional;

public interface GoalAgreeRepository extends JpaRepository<GoalAgree, Long>{
    Optional<GoalAgree> findByGoalAndMember(Goal goal, Member member);
}
