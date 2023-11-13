package com.letsRoll.letsRoll.Project.dto.req;

import com.letsRoll.letsRoll.global.enums.Mode;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.time.LocalDate;

@Data
@AllArgsConstructor
@NoArgsConstructor
@Builder
public class ProjectStartReq {
    private String title;
    private String description;
    private String password;
    private Mode mode;
    private LocalDate startDate;
    private LocalDate endDate;
}