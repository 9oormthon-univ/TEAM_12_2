package com.letsRoll.letsRoll.Todo.dto.res;

import lombok.Builder;
import lombok.Getter;

import java.time.LocalDate;

@Getter
public class TodoResDto {
    // goal
    private Long goalId;
    //내용
    private String content;
    //목표 날짜
    private LocalDate endDate;
    // 담당자
    private Long managerId;
    // 실제 완료한 담당자
    private Long endManagerId;
    // 완료 여부
    private Boolean isComplete;

    @Builder
    TodoResDto(Long goalId, String content, LocalDate endDate, Long managerId, Long endManagerId, Boolean isComplete) {
        this.goalId = goalId;
        this.content = content;
        this.endDate = endDate;
        this.managerId = managerId;
        this.endManagerId = endManagerId;
        this.isComplete = isComplete;
    }

}
