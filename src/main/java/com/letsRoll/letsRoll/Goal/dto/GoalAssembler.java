package com.letsRoll.letsRoll.Goal.dto;

import com.letsRoll.letsRoll.Goal.dto.res.CheckGoalAgree;
import com.letsRoll.letsRoll.Goal.entity.Goal;
import com.letsRoll.letsRoll.Member.entity.Member;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Component;

import java.util.List;

@Component
@RequiredArgsConstructor
public class GoalAssembler {

    public CheckGoalAgree checkGoalAgree(Goal goal, List<Member> memberList) {
        return CheckGoalAgree.builder()
                .goalId(goal.getId())
                .memberIds(memberList.stream().map(Member::getId).toList())
                .memberNickNames(memberList.stream().map(Member::getNickname).toList())
                .build();
    }
}
