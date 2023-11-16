package com.letsRoll.letsRoll.Comment_Feeling.dto.res;

import lombok.Builder;
import lombok.Getter;

import java.time.LocalDate;
import java.util.List;

@Getter
@Builder
public class TodoCommentResDto {
    private Long todoId;
    private String todoContent;
    private LocalDate todoEndDate;
    private Long todoManagerMemberId; // todo담당자(todoManager)의 memberId
    private List<CommentInfoDto> commentList; //한 todo에 할당된 comment 리스트
}
