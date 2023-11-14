package com.letsRoll.letsRoll.Todo.dto.req;

import jakarta.validation.Valid;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.time.LocalDate;

@Data
@AllArgsConstructor
@NoArgsConstructor
@Builder
@Valid
public class AddTodoReqDto {

    // goal
    private Long goalId;

    //내용
    private String content;

    //목표 날짜
    private LocalDate endDate;

    // 담당자
    private Long memberId;
}
