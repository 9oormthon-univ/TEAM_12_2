package com.letsRoll.letsRoll.Todo.dto.res;


import lombok.Builder;
import lombok.Getter;

import java.time.LocalDate;
import java.util.List;

@Getter
@Builder
public class MonthlyCheckTodoListResDto {
    private List<LocalDate> allCompleteDateList;
    private List<LocalDate> inCompleteDateList;
}
