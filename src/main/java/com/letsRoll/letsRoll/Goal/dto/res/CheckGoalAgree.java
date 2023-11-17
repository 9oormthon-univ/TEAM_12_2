package com.letsRoll.letsRoll.Goal.dto.res;

import com.letsRoll.letsRoll.Member.entity.Member;
import lombok.Builder;
import lombok.Getter;

import java.util.List;

@Getter
@Builder
public class CheckGoalAgree {
    private Long goalId;
    private List<String> memberNickNames;
    private List<Long> memberIds;
}
