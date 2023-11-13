package com.letsRoll.letsRoll.Project.service;

import com.letsRoll.letsRoll.Project.dto.req.ProjectStartReq;
import com.letsRoll.letsRoll.Project.entity.Project;
import com.letsRoll.letsRoll.Project.repository.ProjectRepository;
import com.letsRoll.letsRoll.global.enums.Mode;

import lombok.RequiredArgsConstructor;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.time.LocalDate;

@Service
@RequiredArgsConstructor
public class ProjectService {
    @Autowired
    private final ProjectRepository projectRepository;

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
}
