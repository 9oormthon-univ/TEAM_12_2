package com.letsRoll.letsRoll.Comment_Feeling.dto.res;

import lombok.Builder;
import lombok.Getter;

import java.time.LocalDateTime;
import java.util.List;

@Getter
@Builder
public class CommentInfoDto {
    private LocalDateTime createdTime;
    private String content;
    private Long commentMemberId;
    private Integer emojiCount;
    private List<Long> feelingCheckMemberId;
}
