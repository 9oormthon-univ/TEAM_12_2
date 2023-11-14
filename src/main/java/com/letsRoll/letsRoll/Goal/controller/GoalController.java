package com.letsRoll.letsRoll.Goal.controller;

import com.letsRoll.letsRoll.Goal.dto.GoalDto;
import com.letsRoll.letsRoll.Goal.dto.req.GoalAddReq;
import com.letsRoll.letsRoll.Goal.service.GoalAgreeService;
import com.letsRoll.letsRoll.Goal.service.GoalService;
import com.letsRoll.letsRoll.global.common.BaseResponse;
import com.letsRoll.letsRoll.global.exception.BaseResponseCode;
import jakarta.validation.Valid;
import lombok.RequiredArgsConstructor;
import org.springframework.web.bind.annotation.*;

@RestController
@RequestMapping("/api/goals")
@RequiredArgsConstructor
public class GoalController {
    private final GoalService goalService;
    private final GoalAgreeService goalAgreeService;

    @PostMapping("/{projectId}")
    public BaseResponse<Void> addGoal(@PathVariable Long projectId, @RequestBody @Valid GoalAddReq goalAddReq) {
        goalService.addGoal(projectId, goalAddReq);
        return new BaseResponse<>(BaseResponseCode.SUCCESS);
    }
    @GetMapping("/{goalId}")
    public BaseResponse<GoalDto> getGoalDetails(@PathVariable Long goalId) {
        GoalDto goalDetails = goalService.getGoalDetails(goalId);
        return new BaseResponse<>(goalDetails);
    }

    @PostMapping("/{goalId}/agree")
    public BaseResponse<Void> agreeGoal(@PathVariable Long goalId) {
        goalAgreeService.agreeGoal(goalId);
        return new BaseResponse<>(BaseResponseCode.SUCCESS);
    }


}
