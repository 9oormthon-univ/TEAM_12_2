package com.letsRoll.letsRoll.Project.service;

import com.letsRoll.letsRoll.Goal.dto.res.GoalResDto;
import com.letsRoll.letsRoll.Goal.entity.Goal;
import com.letsRoll.letsRoll.Goal.repository.GoalRepository;
import com.letsRoll.letsRoll.Goal.service.GoalService;
import com.letsRoll.letsRoll.Member.dto.req.MemberAddReq;
import com.letsRoll.letsRoll.Member.entity.Member;
import com.letsRoll.letsRoll.Member.repository.MemberRepository;
import com.letsRoll.letsRoll.Project.dto.req.ProjectStartReq;
import com.letsRoll.letsRoll.Project.entity.Project;
import com.letsRoll.letsRoll.Project.repository.ProjectRepository;
import com.letsRoll.letsRoll.User.dto.req.UserSignUpReq;
import com.letsRoll.letsRoll.User.entity.User;
import com.letsRoll.letsRoll.User.repository.UserRepository;
import com.letsRoll.letsRoll.global.enums.Mode;

import com.letsRoll.letsRoll.global.exception.BaseException;
import com.letsRoll.letsRoll.global.exception.BaseResponseCode;
import jakarta.validation.Valid;
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
    @Autowired
    private final MemberRepository memberRepository;
    @Autowired
    private final UserRepository userRepository;
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

    public List<GoalResDto> getGoalsForProject(Long projectId) {
        List<Goal> goals = goalRepository.findByProject_Id(projectId);

        return goals.stream()
                .map(GoalResDto::fromEntity)
                .collect(Collectors.toList());
    }

    public void addMemberToProject(Long projectId, @Valid MemberAddReq memberAddReq) {
        Project project = projectRepository.findById(projectId)
                .orElseThrow(() -> new BaseException(BaseResponseCode.NOT_FOUND_PROJECT));


        String password = memberAddReq.getPassword();
        if(!password.equals(project.getPassword())) {
            throw new BaseException(BaseResponseCode.WRONG_PROJECT_PASSWORD);
        }

        // 임시 사용자 데이터 생성
        UserSignUpReq tempUserReq = UserSignUpReq.builder()
                .userName("testUser2")
                .password("testPassword2")
                .build();

        // UserRepository를 통해 데이터베이스에 저장
        User tempUser = User.builder()
                .userName(tempUserReq.getUserName())
                .password(tempUserReq.getPassword())
                .build();
        userRepository.save(tempUser);

        String nickname = memberAddReq.getNickname();
        String role = memberAddReq.getRole();

        Member member = Member.builder()
                .project(project)
                .user(tempUser)
                .nickname(nickname)
                .role(role)
                .build();

        memberRepository.save(member);
    }

    public void getProjects(Long projectId) {
        Project project = projectRepository.findById(projectId)
                .orElseThrow(() -> new BaseException(BaseResponseCode.NOT_FOUND_PROJECT));


    }
}
