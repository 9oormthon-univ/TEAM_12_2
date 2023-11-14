package com.letsRoll.letsRoll.Comment_Feeling.service;

import com.letsRoll.letsRoll.Comment_Feeling.dto.CommentAssembler;
import com.letsRoll.letsRoll.Comment_Feeling.dto.req.CommentReqDto;
import com.letsRoll.letsRoll.Comment_Feeling.dto.res.CommentInfoDto;
import com.letsRoll.letsRoll.Comment_Feeling.dto.res.TodoCommentResDto;
import com.letsRoll.letsRoll.Comment_Feeling.entity.Comment;
import com.letsRoll.letsRoll.Comment_Feeling.entity.Feeling;
import com.letsRoll.letsRoll.Comment_Feeling.repository.CommentRepository;
import com.letsRoll.letsRoll.Comment_Feeling.repository.FeelingRepository;
import com.letsRoll.letsRoll.Goal.entity.Goal;
import com.letsRoll.letsRoll.Goal.repository.GoalRepository;
import com.letsRoll.letsRoll.Member.entity.Member;
import com.letsRoll.letsRoll.Member.repository.MemberRepository;
import com.letsRoll.letsRoll.Todo.entity.Todo;
import com.letsRoll.letsRoll.Todo.repository.TodoRepository;
import com.letsRoll.letsRoll.global.enums.CommentType;
import com.letsRoll.letsRoll.global.exception.BaseException;
import com.letsRoll.letsRoll.global.exception.BaseResponseCode;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

import java.util.ArrayList;
import java.util.List;

@Service
@RequiredArgsConstructor
public class CommentService {
    private final GoalRepository goalRepository;
    private final MemberRepository memberRepository;
    private final CommentAssembler commentAssembler;
    private final CommentRepository commentRepository;
    private final TodoRepository todoRepository;
    private final FeelingRepository feelingRepository;


    public void addComment(CommentReqDto commentReqDto, String type) {
        Goal goal = goalRepository.findGoalByIdAndIsComplete(commentReqDto.getGoalId(), false)
                .orElseThrow(() -> new BaseException(BaseResponseCode.NOT_FOUND_GOAL));
        Member member = getMember(commentReqDto.getMemberId());
        switch (type){
            case "GOAL" ->
                    commentRepository.save(commentAssembler.toCommentEntity(goal, member, commentReqDto.getContent()));
            case "TODO" -> {
                Todo todo = todoRepository.findTodoById(commentReqDto.getTodoId())
                        .orElseThrow(() -> new BaseException(BaseResponseCode.NOT_FOUND_TODO));
                commentRepository.save(commentAssembler.toCommentEntity(goal, todo, member, commentReqDto.getContent()));
            }
        }
    }
    public TodoCommentResDto getTodoComment(Long todoId) {
        Todo todo = getTodo(todoId);
        List<Comment> commentList = commentRepository.findAllByGoalAndTodoAndTypeOrderByCreatedDateAsc(todo.getGoal(), todo, CommentType.TODO);
        List<CommentInfoDto> commentDtoList = new ArrayList<>();
        for (Comment comment : commentList) {
            List<Feeling> feelingList = feelingRepository.findAllByComment(comment);

            List<Long> memberList = new ArrayList<>();
            feelingList.forEach(feeling -> memberList.add(feeling.getMember().getId()));

            commentDtoList.add(commentAssembler.toCommentInfoDtoEntity(comment.getCreatedDate(),
                    comment.getContent(), comment.getMember().getId(), memberList.size(), memberList));
        }
        return commentAssembler.toTodoCommentResDtoEntity(todo, commentDtoList);
    }

    public Goal getGoal(Long goalId) {
        return goalRepository.findById(goalId)
                .orElseThrow(() -> new BaseException(BaseResponseCode.NOT_FOUND_GOAL));
    }

    public Todo getTodo(Long todoId) {
        return todoRepository.findTodoById(todoId)
                .orElseThrow(() -> new BaseException(BaseResponseCode.NOT_FOUND_TODO));
    }

    public Member getMember(Long memberId) {
        return memberRepository.findMemberById(memberId)
                .orElseThrow(() -> new BaseException(BaseResponseCode.NOT_FOUND_MEMBER));
    }
}
