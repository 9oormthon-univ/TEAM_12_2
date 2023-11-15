package com.letsRoll.letsRoll.Goal.service;

import com.letsRoll.letsRoll.Goal.entity.Goal;
import com.letsRoll.letsRoll.Goal.entity.GoalAgree;
import com.letsRoll.letsRoll.Goal.repository.GoalAgreeRepository;
import com.letsRoll.letsRoll.Goal.repository.GoalRepository;
import com.letsRoll.letsRoll.Member.entity.Member;
import com.letsRoll.letsRoll.Member.repository.MemberRepository;
import com.letsRoll.letsRoll.global.exception.BaseException;
import com.letsRoll.letsRoll.global.exception.BaseResponseCode;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

@Service
@RequiredArgsConstructor
public class GoalAgreeService {
    private final GoalAgreeRepository goalAgreeRepository;
    private final GoalRepository goalRepository;
    private final MemberRepository memberRepository;

    public void agreeGoal(Long goalId, Long memberId) {
        Goal goal = goalRepository.findById(goalId)
                .orElseThrow(() -> new BaseException(BaseResponseCode.NOT_FOUND_GOAL));

//        Long memberId = 5L; // 임시 member 값
        Member member = memberRepository.findById(memberId)
                .orElseThrow(() -> new BaseException(BaseResponseCode.NOT_FOUND_MEMBER));

        // 멤버가 프로젝트에 속해 있는지 확인
        if (!goal.getProject().getMembers().contains(member)) {
            throw new BaseException(BaseResponseCode.NOT_FOUND_MEMBER);
        }

        // 해당 멤버에 대한 GoalAgree 엔티티 조회, 없으면 생성함(추후 멤버 추가됐을 때)
        GoalAgree goalAgree = goalAgreeRepository.findByGoalAndMember(goal, member)
                .orElse(new GoalAgree(goal, member));

        // 사용자가 체크를 누를 때
        goalAgree.setMemberCheck(true);

        goalAgreeRepository.save(goalAgree);
    }
}

