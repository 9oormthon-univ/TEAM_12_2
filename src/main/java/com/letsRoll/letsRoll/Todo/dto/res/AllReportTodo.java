package com.letsRoll.letsRoll.Todo.dto.res;

import lombok.Builder;
import lombok.Getter;

import java.util.List;

@Builder
@Getter
public class AllReportTodo {
    private Integer todoCount;
    private List<ReportTodo> reportTodoList;
}
