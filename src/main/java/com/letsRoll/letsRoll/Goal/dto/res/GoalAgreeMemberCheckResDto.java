package com.letsRoll.letsRoll.Goal.dto.res;

import com.letsRoll.letsRoll.Goal.entity.GoalAgree;
import com.letsRoll.letsRoll.Member.entity.Member;
import lombok.Getter;
import lombok.Setter;

import java.util.List;
import java.util.stream.Collectors;

@Getter
@Setter
public class GoalAgreeMemberCheckResDto {
    private Long goalAgreeId;
    private Long memberId;
    private Boolean memberCheck;

    public static GoalAgreeMemberCheckResDto fromEntity(GoalAgree goalAgree) {
        GoalAgreeMemberCheckResDto dto = new GoalAgreeMemberCheckResDto();
        dto.setGoalAgreeId(goalAgree.getId());
        //dto.setMemberId(goalAgree.getMember().getId());

        Member member = goalAgree.getMember();
        if (member != null) {
            dto.setMemberId(member.getId());
        }

        dto.setMemberCheck(goalAgree.getMemberCheck());
        return dto;
    }

    public static List<GoalAgreeMemberCheckResDto> fromEntities(List<GoalAgree> goalAgrees) {
        return goalAgrees.stream()
                .map(GoalAgreeMemberCheckResDto::fromEntity)
                .collect(Collectors.toList());
    }
}