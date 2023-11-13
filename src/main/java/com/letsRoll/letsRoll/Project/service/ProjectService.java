package com.letsRoll.letsRoll.Project.service;

import com.letsRoll.letsRoll.Goal.dto.GoalDto;
import com.letsRoll.letsRoll.Goal.entity.Goal;
import com.letsRoll.letsRoll.Goal.repository.GoalRepository;
import com.letsRoll.letsRoll.Goal.service.GoalService;
import com.letsRoll.letsRoll.Project.dto.req.ProjectStartReq;
import com.letsRoll.letsRoll.Project.entity.Project;
import com.letsRoll.letsRoll.Project.repository.ProjectRepository;
import com.letsRoll.letsRoll.global.enums.Mode;

import com.letsRoll.letsRoll.global.exception.BaseException;
import com.letsRoll.letsRoll.global.exception.BaseResponseCode;
import lombok.RequiredArgsConstructor;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.time.LocalDate;
import java.util.List;
import java.util.stream.Collectors;

@Service
@RequiredArgsConstructor
public class ProjectService {
    @Autowired
    private final ProjectRepository projectRepository;
    @Autowired
    private final GoalService goalService;
    @Autowired
    private final GoalRepository goalRepository;

    @Transactional
    public void startProject(ProjectStartReq projectStartReq) {
        // ProjectStartReq로부터 필요한 정보 추출
        String title = projectStartReq.getTitle();
        String description = projectStartReq.getDescription();
        String password = projectStartReq.getPassword();
        Mode mode = projectStartReq.getMode();
        LocalDate startDate = projectStartReq.getStartDate();
        LocalDate endDate = projectStartReq.getEndDate();

        Project project = Project.builder()
                .title(title)
                .description(description)
                .password(password)
                .mode(mode)
                .startDate(startDate)
                .endDate(endDate)
                .build();


        projectRepository.save(project);
    }

    public List<GoalDto> getGoalsForProject(Long projectId) {
        // 해당 프로젝트에 속한 목표 목록을 가져옴
        List<Goal> goals = goalRepository.findByProject_Id(projectId);

        // 필요에 따라 Entity를 DTO로 변환하여 반환
        return goals.stream()
                .map(GoalDto::fromEntity)
                .collect(Collectors.toList());
    }
}
