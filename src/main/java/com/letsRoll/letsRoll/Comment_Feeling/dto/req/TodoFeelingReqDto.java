package com.letsRoll.letsRoll.Comment_Feeling.dto.req;

import jakarta.validation.Valid;
import lombok.Getter;

@Getter
@Valid
public class TodoFeelingReqDto {
    private Long memberId;
}
