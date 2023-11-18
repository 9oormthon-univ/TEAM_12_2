package com.letsRoll.letsRoll.Project.dto;

import com.letsRoll.letsRoll.Goal.dto.GoalAssembler;
import com.letsRoll.letsRoll.Member.dto.MemberAssembler;
import com.letsRoll.letsRoll.Member.entity.Member;
import com.letsRoll.letsRoll.Project.dto.res.ProjectResDto;
import com.letsRoll.letsRoll.Project.entity.Project;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Component;

@Component
@RequiredArgsConstructor
public class ProjectAssembler {

    public static ProjectResDto projectResDto(Project project, Member member) {
        ProjectResDto projectDto = new ProjectResDto();
        projectDto.setProjectId(project.getId());
        projectDto.setTitle(project.getTitle());
        projectDto.setDescription(project.getDescription());
        projectDto.setPassword(project.getPassword());
        projectDto.setMode(project.getMode());
        projectDto.setStartDate(project.getStartDate());
        projectDto.setFinishDate(project.getFinishDate());
        projectDto.setEndDate(project.getEndDate());
        projectDto.setGoals(GoalAssembler.fromEntities(project.getGoals()));
        projectDto.setMembers(MemberAssembler.fromEntities(project.getMembers()));
        projectDto.setProgress(project.getProgress());
        projectDto.setMemberId(member.getId());
        return projectDto;
    }
    public static ProjectResDto projectResDto(Project project) {
        ProjectResDto projectDto = new ProjectResDto();
        projectDto.setProjectId(project.getId());
        projectDto.setTitle(project.getTitle());
        projectDto.setDescription(project.getDescription());
        projectDto.setPassword(project.getPassword());
        projectDto.setMode(project.getMode());
        projectDto.setStartDate(project.getStartDate());
        projectDto.setFinishDate(project.getFinishDate());
        projectDto.setEndDate(project.getEndDate());
        projectDto.setGoals(GoalAssembler.fromEntities(project.getGoals()));
        projectDto.setMembers(MemberAssembler.fromEntities(project.getMembers()));
        projectDto.setProgress(project.getProgress());
        return projectDto;
    }
}
