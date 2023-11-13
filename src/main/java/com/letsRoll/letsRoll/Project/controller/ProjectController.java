package com.letsRoll.letsRoll.Project.controller;

import com.letsRoll.letsRoll.Project.dto.req.ProjectStartReq;
import com.letsRoll.letsRoll.Project.service.ProjectService;
import com.letsRoll.letsRoll.global.common.BaseResponse;
import com.letsRoll.letsRoll.global.exception.BaseResponseCode;
import jakarta.validation.Valid;
import lombok.RequiredArgsConstructor;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

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
}
