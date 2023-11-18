package com.letsRoll.letsRoll.Goal.dto.req;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.time.LocalDate;

@Data
@AllArgsConstructor
@NoArgsConstructor
@Builder
public class GoalAddReq {
    private String title;
    private String content;
    private LocalDate startDate;
    private LocalDate endDate;
    private Long userId;
}
