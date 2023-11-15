package com.letsRoll.letsRoll.Memoir.dto.req;

import lombok.*;

@Data
@AllArgsConstructor
@NoArgsConstructor
@Builder
public class MemoirAddReq {
    private Long memberId;
    private String content;
}
