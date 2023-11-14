package com.letsRoll.letsRoll.Goal.service;

import com.letsRoll.letsRoll.Goal.dto.GoalDto;
import com.letsRoll.letsRoll.Goal.dto.req.GoalAddReq;
import com.letsRoll.letsRoll.Goal.entity.Goal;
import com.letsRoll.letsRoll.Goal.repository.GoalRepository;
import com.letsRoll.letsRoll.Project.entity.Project;
import com.letsRoll.letsRoll.Project.repository.ProjectRepository;
import com.letsRoll.letsRoll.global.exception.BaseException;
import com.letsRoll.letsRoll.global.exception.BaseResponseCode;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

import java.time.LocalDate;

@Service
@RequiredArgsConstructor
public class GoalService {
    private final GoalRepository goalRepository;
    private final ProjectRepository projectRepository;

    public void addGoal(Long projectId, GoalAddReq goalAddReq) {

        // 프로젝트 정보 가져오기
        Project project = projectRepository.findById(projectId)
                .orElseThrow(() -> new BaseException(BaseResponseCode.NOT_FOUND_PROJECT));

        String title = goalAddReq.getTitle();
        String content = goalAddReq.getContent();
        LocalDate startDate = goalAddReq.getStartDate();
        LocalDate endDate = goalAddReq.getEndDate();

        Goal goal = Goal.builder()
                .project(project)
                .title(title)
                .content(content)
                .startDate(startDate)
                .endDate(endDate)
                .build();

        goalRepository.save(goal);
        project.getGoals().add(goal);
        projectRepository.save(project);

    }

    public GoalDto getGoalDetails(Long goalId) {
        Goal goal = goalRepository.findById(goalId)
                .orElseThrow(() -> new BaseException(BaseResponseCode.NOT_FOUND_GOAL));

        return GoalDto.fromEntity(goal);
    }


}
