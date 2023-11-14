package com.letsRoll.letsRoll.Project.controller;

import com.letsRoll.letsRoll.Goal.dto.GoalDto;
import com.letsRoll.letsRoll.Goal.entity.Goal;
import com.letsRoll.letsRoll.Member.dto.req.MemberAddReq;
import com.letsRoll.letsRoll.Project.dto.req.ProjectStartReq;
import com.letsRoll.letsRoll.Project.service.ProjectService;
import com.letsRoll.letsRoll.global.common.BaseResponse;
import com.letsRoll.letsRoll.global.exception.BaseResponseCode;
import jakarta.validation.Valid;
import lombok.RequiredArgsConstructor;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/api/projects")
@RequiredArgsConstructor
public class ProjectController {
    private final ProjectService projectService;

    @PostMapping //프로젝트 등록
    public BaseResponse<Void> startProject(@RequestBody @Valid ProjectStartReq projectStartReq) {
        projectService.startProject(projectStartReq);
        return new BaseResponse<>(BaseResponseCode.SUCCESS);
    }

    @GetMapping("/{projectId}/goals")
    public BaseResponse<List<GoalDto>> getGoalsForProject(@PathVariable Long projectId) {
        List<GoalDto> goals = projectService.getGoalsForProject(projectId);
        return new BaseResponse<>(goals);
    }

    @PostMapping("/{projectId}/members")
    public BaseResponse<Void> addMemberToProject(@PathVariable Long projectId, @Valid @RequestBody MemberAddReq memberAddReq) {
        projectService.addMemberToProject(projectId, memberAddReq);
        return new BaseResponse<>(BaseResponseCode.SUCCESS);
    }
}
