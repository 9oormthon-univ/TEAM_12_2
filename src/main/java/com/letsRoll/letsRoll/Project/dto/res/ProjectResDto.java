package com.letsRoll.letsRoll.Project.dto.res;

import com.letsRoll.letsRoll.Goal.dto.res.GoalResDto;
import com.letsRoll.letsRoll.Member.dto.res.MemberResDto;
import com.letsRoll.letsRoll.Memoir.dto.res.MemoirResDto;
import com.letsRoll.letsRoll.Project.entity.Project;
import com.letsRoll.letsRoll.global.enums.Mode;
import lombok.Getter;
import lombok.Setter;

import java.time.LocalDate;
import java.util.List;

@Getter
@Setter
public class ProjectResDto {
    private Long projectId;
    private String title;
    private String description;
    private String password;
    private Mode mode;
    private LocalDate startDate;
    private LocalDate finishDate;
    private LocalDate endDate;
    private List<GoalResDto> goals;
    private List<MemberResDto> members;

    public static ProjectResDto fromEntity(Project project) {
        ProjectResDto projectDto = new ProjectResDto();
        projectDto.setProjectId(project.getId());
        projectDto.setTitle(project.getTitle());
        projectDto.setDescription(project.getDescription());
        projectDto.setPassword(project.getPassword());
        projectDto.setMode(project.getMode());
        projectDto.setStartDate(project.getStartDate());
        projectDto.setFinishDate(project.getFinishDate());
        projectDto.setEndDate(project.getEndDate());
        projectDto.setGoals(GoalResDto.fromEntities(project.getGoals()));
        projectDto.setMembers(MemberResDto.fromEntities(project.getMembers()));
        return projectDto;
    }
}
