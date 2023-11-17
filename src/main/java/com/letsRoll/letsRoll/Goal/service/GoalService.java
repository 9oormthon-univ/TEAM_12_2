package com.letsRoll.letsRoll.Goal.service;

import com.letsRoll.letsRoll.Comment_Feeling.dto.CommentAssembler;
import com.letsRoll.letsRoll.Comment_Feeling.dto.res.CommentInfoDto;
import com.letsRoll.letsRoll.Comment_Feeling.dto.res.TodoCommentResDto;
import com.letsRoll.letsRoll.Comment_Feeling.entity.Comment;
import com.letsRoll.letsRoll.Comment_Feeling.entity.Feeling;
import com.letsRoll.letsRoll.Comment_Feeling.repository.CommentRepository;
import com.letsRoll.letsRoll.Comment_Feeling.repository.FeelingRepository;
import com.letsRoll.letsRoll.Goal.dto.res.GoalResDto;
import com.letsRoll.letsRoll.Goal.dto.req.GoalAddReq;
import com.letsRoll.letsRoll.Goal.dto.res.TimeLineResDto;
import com.letsRoll.letsRoll.Goal.entity.Goal;
import com.letsRoll.letsRoll.Goal.entity.GoalAgree;
import com.letsRoll.letsRoll.Goal.repository.GoalAgreeRepository;
import com.letsRoll.letsRoll.Goal.repository.GoalRepository;
import com.letsRoll.letsRoll.Member.entity.Member;
import com.letsRoll.letsRoll.Project.entity.Project;
import com.letsRoll.letsRoll.Project.repository.ProjectRepository;
import com.letsRoll.letsRoll.Todo.entity.Todo;
import com.letsRoll.letsRoll.Todo.repository.TodoRepository;
import com.letsRoll.letsRoll.global.enums.CommentType;
import com.letsRoll.letsRoll.global.exception.BaseException;
import com.letsRoll.letsRoll.global.exception.BaseResponseCode;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

import java.time.LocalDate;
import java.util.ArrayList;
import java.util.List;

@Service
@RequiredArgsConstructor
public class GoalService {
    private final GoalRepository goalRepository;
    private final ProjectRepository projectRepository;
    private final GoalAgreeRepository goalAgreeRepository;
    private final CommentRepository commentRepository;
    private final CommentAssembler commentAssembler;
    private final FeelingRepository feelingRepository;
    private final TodoRepository todoRepository;
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

        for (Member member : project.getMembers()) {
            // GoalAgree 정보 저장
            GoalAgree goalAgree = GoalAgree.builder()
                    .goal(goal)
                    .member(member)  // 멤버 정보 주입
                    .build();

            goalAgreeRepository.save(goalAgree);
        }

    }

    public GoalResDto getGoalDetails(Long goalId) {
        Goal goal = goalRepository.findById(goalId)
                .orElseThrow(() -> new BaseException(BaseResponseCode.NOT_FOUND_GOAL));

        return GoalResDto.fromEntity(goal);
    }

    public void completeGoal(Long goalId) {
        Goal goal = goalRepository.findById(goalId)
                .orElseThrow(() -> new BaseException(BaseResponseCode.NOT_FOUND_GOAL));
    for(GoalAgree goalAgree : goal.getGoalAgreeList()) {
        if(!goalAgree.getMemberCheck()) {
            throw new BaseException(BaseResponseCode.NOT_COMPLETED_GOAL);
        }
    }
        goal.setIsComplete(true);
        goalRepository.save(goal);
    }
    public TimeLineResDto getTimeLine(Long goalId) {
        Goal goal = goalRepository.findById(goalId)
                .orElseThrow(() -> new BaseException(BaseResponseCode.NOT_FOUND_GOAL));
        List<CommentInfoDto> goalCommentList = getComments(commentRepository.findAllByGoalAndTypeOrderByCreatedDateAsc(goal, CommentType.GOAL));
        List<Todo> todoList = todoRepository.findTodosByGoalAndIsCompleteIsTrueOrderByCreatedDate(goal);
        List<TodoCommentResDto> todoCommentList = new ArrayList<>();
        for (Todo todo : todoList) {
            List<CommentInfoDto> todoCommentInfo = getComments(commentRepository.findAllByGoalAndTodoAndTypeOrderByCreatedDateAsc(goal, todo, CommentType.TODO));
            todoCommentList.add(commentAssembler.toTodoCommentResDtoEntity(todo, todoCommentInfo));
        }
        return TimeLineResDto.builder().goalCommentList(goalCommentList).todoCommentList(todoCommentList).build();
    }

    public List<CommentInfoDto> getComments(List<Comment> comments) {
        List<CommentInfoDto> commentList = new ArrayList<>();
        for (Comment comment : comments) {
            List<Feeling> feelingList = feelingRepository.findAllByComment(comment);

            List<Long> memberList = new ArrayList<>();
            feelingList.forEach(feeling -> memberList.add(feeling.getMember().getId()));

            commentList.add(commentAssembler.toCommentInfoDtoEntity(comment.getCreatedDate(),comment.getContent(), comment.getMember().getId(),
                    memberList.size(), memberList));
        }
        return commentList;
    }
}
