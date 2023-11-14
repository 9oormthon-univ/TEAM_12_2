package com.letsRoll.letsRoll.Comment_Feeling.dto.req;

import jakarta.validation.Valid;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@AllArgsConstructor
@NoArgsConstructor
@Builder
@Valid
public class CommentReqDto {
    private Long goalId;
    private Long memberId;
    private String content;
    private Long todoId;
}
