package com.letsRoll.letsRoll.Todo.dto.res;

import lombok.Builder;
import lombok.Getter;

@Getter
@Builder
public class ReportTodo {
    private Long memberId;
    private String memberNickName;
    private Integer completeCount;
    private Integer managedCount;
}
