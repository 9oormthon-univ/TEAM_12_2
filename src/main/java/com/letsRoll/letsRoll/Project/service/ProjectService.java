package com.letsRoll.letsRoll.Project.service;

import com.letsRoll.letsRoll.Goal.dto.GoalAssembler;
import com.letsRoll.letsRoll.Goal.dto.res.GoalResDto;
import com.letsRoll.letsRoll.Goal.entity.Goal;
import com.letsRoll.letsRoll.Goal.entity.GoalAgree;
import com.letsRoll.letsRoll.Goal.repository.GoalAgreeRepository;
import com.letsRoll.letsRoll.Goal.repository.GoalRepository;
import com.letsRoll.letsRoll.Goal.service.GoalService;
import com.letsRoll.letsRoll.Member.dto.req.MemberAddReq;
import com.letsRoll.letsRoll.Member.entity.Member;
import com.letsRoll.letsRoll.Member.repository.MemberRepository;
import com.letsRoll.letsRoll.Memoir.dto.req.MemoirAddReq;
import com.letsRoll.letsRoll.Memoir.entity.Memoir;
import com.letsRoll.letsRoll.Memoir.repository.MemoirRepository;
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
    @Autowired
    private final MemoirRepository memoirRepository;
    @Autowired
    private final GoalAgreeRepository goalAgreeRepository;

    public void setFinishDateIfGoalsCompleted(Project project) {
        boolean allGoalsCompleted = project.getGoals().stream().allMatch(Goal::getIsComplete);

        // 모든 목표가 완료되었다면 finishDate 설정
        if (allGoalsCompleted) {
            project.setFinishDate(LocalDate.now());
        }
        else{
            throw new BaseException(BaseResponseCode.NOT_COMPLETED_GOAL);
        }
    }


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
                .map(GoalAssembler::fromEntity)
                .collect(Collectors.toList());
    }

    public void addMemberToProject(Long projectId, @Valid MemberAddReq memberAddReq) {
        User user = userRepository.findUserById(memberAddReq.getUserId())
                .orElseThrow(() -> new BaseException(BaseResponseCode.NOT_FOUND_USER));


        Project project = projectRepository.findById(projectId)
                .orElseThrow(() -> new BaseException(BaseResponseCode.NOT_FOUND_PROJECT));

        String password = memberAddReq.getPassword();
        if (!password.equals(project.getPassword())) {
            throw new BaseException(BaseResponseCode.WRONG_PROJECT_PASSWORD);
        }


        String nickname = memberAddReq.getNickname();
        String role = memberAddReq.getRole();

        Member member = Member.builder()
                .project(project)
                .user(user)
                .nickname(nickname)
                .role(role)
                .build();

        memberRepository.save(member);

        // 프로젝트의 각 Goal에 대해 GoalAgree 생성 또는 가져오기
        List<Goal> goals = project.getGoals();
        for (Goal goal : goals) {
            GoalAgree goalAgree = GoalAgree.builder()
                            .goal(goal)
                            .member(member)
                            .build();

            goalAgreeRepository.save(goalAgree);
        }
    }

    public void getProjects(Long projectId) {
        Project project = projectRepository.findById(projectId)
                .orElseThrow(() -> new BaseException(BaseResponseCode.NOT_FOUND_PROJECT));
    }

    public void completeProject(Long projectId) {
        Project project = projectRepository.findById(projectId)
                .orElseThrow(() -> new BaseException(BaseResponseCode.NOT_FOUND_PROJECT));

        // 프로젝트의 모든 Goal이 완료된 상태인지 확인
        setFinishDateIfGoalsCompleted(project);

        projectRepository.save(project);
    }

    public void addMemoir(Long projectId, MemoirAddReq addMemoirReq) {
        Project project = projectRepository.findById(projectId)
                .orElseThrow(() -> new BaseException(BaseResponseCode.NOT_FOUND_PROJECT));
        // memberId로 Member 확인
        Member member = memberRepository.findById(addMemoirReq.getMemberId())
                .orElseThrow(() -> new BaseException(BaseResponseCode.NOT_FOUND_MEMBER));

        // 멤버가 프로젝트에 속해 있는지 확인
        if (!project.getMembers().contains(member)) {
            throw new BaseException(BaseResponseCode.NOT_FOUND_MEMBER);
        }

        // 프로젝트의 모든 Goal이 완료된 상태인지 확인
        boolean allGoalsCompleted = project.getGoals().stream().allMatch(Goal::getIsComplete);
        if (!allGoalsCompleted) {
            throw new BaseException(BaseResponseCode.NOT_COMPLETED_GOAL);
        }

        // 한 번만 Memoir를 작성할 수 있도록 체크
        if (memoirRepository.existsByMember(member)) {
            throw new BaseException(BaseResponseCode.ALREADY_WRITTEN_MEMOIR);
        }

        // Memoir를 생성하여 저장
        Memoir memoir = Memoir.builder()
                .member(member)
                .content(addMemoirReq.getContent())
                .build();

        memoirRepository.save(memoir);
    }
}
