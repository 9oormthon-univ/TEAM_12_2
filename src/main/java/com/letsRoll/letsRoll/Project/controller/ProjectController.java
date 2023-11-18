package com.letsRoll.letsRoll.Project.controller;

import com.letsRoll.letsRoll.Goal.dto.res.GoalResDto;
import com.letsRoll.letsRoll.Member.dto.req.MemberAddReq;
import com.letsRoll.letsRoll.Memoir.dto.req.MemoirAddReq;

import com.letsRoll.letsRoll.Memoir.service.MemoirService;
import com.letsRoll.letsRoll.Project.dto.ProjectAssembler;
import com.letsRoll.letsRoll.Project.dto.req.ProjectStartReq;
import com.letsRoll.letsRoll.Project.dto.res.ProjectResDto;
import com.letsRoll.letsRoll.Project.entity.Project;
import com.letsRoll.letsRoll.Project.repository.ProjectRepository;
import com.letsRoll.letsRoll.Project.service.ProjectService;
import com.letsRoll.letsRoll.global.common.BaseResponse;
import com.letsRoll.letsRoll.global.exception.BaseException;
import com.letsRoll.letsRoll.global.exception.BaseResponseCode;
import jakarta.validation.Valid;
import lombok.RequiredArgsConstructor;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
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

    @GetMapping("/{projectId}")
    public BaseResponse<ProjectResDto> getProjectDetails(@PathVariable Long projectId, @RequestParam Long userId) {

        return new BaseResponse<>(projectService.getProjectDetails(projectId, userId));
    }


    @GetMapping("/{projectId}/goals")
    public BaseResponse<List<GoalResDto>> getGoalsForProject(@PathVariable Long projectId) {
        List<GoalResDto> goals = projectService.getGoalsForProject(projectId);
        return new BaseResponse<>(goals);
    }

    @PostMapping("/{projectId}/members")
    public BaseResponse<Void> addMemberToProject(@PathVariable Long projectId, @Valid @RequestBody MemberAddReq memberAddReq) {
        projectService.addMemberToProject(projectId, memberAddReq);
        return new BaseResponse<>(BaseResponseCode.SUCCESS);
    }

    @PostMapping("/{projectId}/complete")
    public BaseResponse<Void> finishProject(@PathVariable Long projectId) {
        projectService.completeProject(projectId);
        return new BaseResponse<>(BaseResponseCode.SUCCESS);
    }

    @PostMapping("/{projectId}/memoirs")
    public BaseResponse<Void> addMemoir(@PathVariable Long projectId, @Valid @RequestBody MemoirAddReq addMemoirReq) {
        projectService.addMemoir(projectId, addMemoirReq);
        return new BaseResponse<>(BaseResponseCode.SUCCESS);
    }

}
