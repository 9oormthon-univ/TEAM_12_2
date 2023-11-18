package com.letsRoll.letsRoll.Goal.dto;

import com.letsRoll.letsRoll.Goal.dto.req.GoalAddReq;
import com.letsRoll.letsRoll.Goal.dto.res.CheckGoalAgree;
import com.letsRoll.letsRoll.Goal.dto.res.GoalAgreeMemberCheckResDto;
import com.letsRoll.letsRoll.Goal.dto.res.GoalResDto;
import com.letsRoll.letsRoll.Goal.dto.res.ReportGoalResDto;
import com.letsRoll.letsRoll.Goal.entity.Goal;
import com.letsRoll.letsRoll.Goal.entity.GoalAgree;
import com.letsRoll.letsRoll.Member.entity.Member;
import com.letsRoll.letsRoll.Project.entity.Project;
import com.letsRoll.letsRoll.Todo.dto.res.TodoListResDto;
import com.letsRoll.letsRoll.Todo.entity.Todo;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Component;

import java.util.List;
import java.util.stream.Collectors;

@Component
@RequiredArgsConstructor
public class GoalAssembler {

    public Goal goal(GoalAddReq goalAddReq, Project project) {
        return Goal.builder()
                .project(project)
                .title(goalAddReq.getTitle())
                .content(goalAddReq.getContent())
                .startDate(goalAddReq.getStartDate())
                .endDate(goalAddReq.getEndDate())
                .build();
    }

    public GoalAgree goalAgree(Goal goal, Member member) {
        return GoalAgree.builder()
                .goal(goal)
                .member(member)  // 멤버 정보 주입
                .build();
    }
    public CheckGoalAgree checkGoalAgree(Goal goal, List<Member> memberList) {
        return CheckGoalAgree.builder()
                .goalId(goal.getId())
                .memberIds(memberList.stream().map(Member::getId).toList())
                .memberNickNames(memberList.stream().map(Member::getNickname).toList())
                .build();
    }
    public static ReportGoalResDto reportGoalResDto(Project project, Goal goal) {
        return ReportGoalResDto.builder()
                .goalId(goal.getId())
                .projectId(project.getId())
                .title(goal.getTitle())
                .startDate(goal.getStartDate())
                .endDate(goal.getEndDate())
                .finishDate(goal.getFinishDate())
                .isComplete(goal.getIsComplete())
                .build();
    }
    public static GoalResDto fromEntity(Goal goal) {
        GoalResDto goalDto = new GoalResDto();
        goalDto.setProjectId(goal.getProject().getId());
        goalDto.setGoalId(goal.getId());
        goalDto.setTitle(goal.getTitle());
        goalDto.setContent(goal.getContent());
        goalDto.setStartDate(goal.getStartDate());
        goalDto.setEndDate(goal.getEndDate());
        goalDto.setComplete(goal.getIsComplete());
        goalDto.setProgress(goal.getProgress());

        List<GoalAgree> goalAgreeList = goal.getGoalAgreeList();
        if (goalAgreeList != null && !goalAgreeList.isEmpty()) {
            goalDto.setGoalAgreeList(GoalAgreeMemberCheckResDto.fromEntities(goalAgreeList));
        }

        List<Todo> todoList = goal.getTodoList();
        if (todoList != null && !todoList.isEmpty()) {
            goalDto.setTodoList(TodoListResDto.fromEntities(todoList));
        }

        return goalDto;
    }

    public static List<GoalResDto> fromEntities(List<Goal> goals) {
        return goals.stream()
                .map(GoalAssembler::fromEntity)
                .collect(Collectors.toList());
    }
}
