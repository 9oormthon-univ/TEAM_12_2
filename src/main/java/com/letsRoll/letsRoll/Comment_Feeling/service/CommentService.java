package com.letsRoll.letsRoll.Comment_Feeling.service;

import com.letsRoll.letsRoll.Comment_Feeling.dto.CommentAssembler;
import com.letsRoll.letsRoll.Comment_Feeling.dto.FeelingAssembler;
import com.letsRoll.letsRoll.Comment_Feeling.dto.req.CommentReqDto;
import com.letsRoll.letsRoll.Comment_Feeling.dto.req.GoalFeelingReqDto;
import com.letsRoll.letsRoll.Comment_Feeling.dto.req.TodoFeelingReqDto;
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
import com.letsRoll.letsRoll.global.enums.Emoji;
import com.letsRoll.letsRoll.global.exception.BaseException;
import com.letsRoll.letsRoll.global.exception.BaseResponseCode;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

import java.util.ArrayList;
import java.util.List;
import java.util.Optional;

@Service
@RequiredArgsConstructor
public class CommentService {
    private final GoalRepository goalRepository;
    private final MemberRepository memberRepository;
    private final CommentAssembler commentAssembler;
    private final CommentRepository commentRepository;
    private final TodoRepository todoRepository;
    private final FeelingRepository feelingRepository;
    private final FeelingAssembler feelingAssembler;

    //comment 추가
    public void addComment(CommentReqDto commentReqDto, String type) {
        Goal goal = goalRepository.findGoalByIdAndIsComplete(commentReqDto.getGoalId(), false)
                .orElseThrow(() -> new BaseException(BaseResponseCode.NOT_FOUND_GOAL));

        Member member = getMember(commentReqDto.getMemberId());

        switch (type){ // type별 comment 저장
            case "GOAL" ->
                    commentRepository.save(commentAssembler.toCommentEntity(goal, member, commentReqDto.getContent()));
            case "TODO" -> {
                Todo todo = getTodo(commentReqDto.getTodoId());
                commentRepository.save(commentAssembler.toCommentEntity(goal, todo, member, commentReqDto.getContent()));
            }
        }
    }
    // todo별 Comment 조회
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

    // goalComment에 이모지 추가
    public void addGoalCommentFeeling(Long goalId, Long commentId, GoalFeelingReqDto goalFeelingReqDto) {
        Goal goal = getGoal(goalId);
        Comment comment = commentRepository.findCommentByIdAndGoalAndType(commentId, goal, CommentType.GOAL)
                .orElseThrow(() -> new BaseException(BaseResponseCode.NOT_FOUND_COMMENT));
        Member member = getMember(goalFeelingReqDto.getMemberId());
        Emoji emoji = isEmoji(goalFeelingReqDto.getEmoji());
        checkAlreadyExistFeeling(comment, member, emoji);
    }
    // todoComment에 이모지 추가
    public void addTodoCommentFeeling(Long todoId, Long commentId, TodoFeelingReqDto todoFeelingReqDto) {
        Todo todo = getTodo(todoId);
        Comment comment = commentRepository.findCommentByIdAndTodoAndType(commentId, todo, CommentType.TODO)
                .orElseThrow(() -> new BaseException(BaseResponseCode.NOT_FOUND_COMMENT));
        Member member = getMember(todoFeelingReqDto.getMemberId());
        checkAlreadyExistFeeling(comment, member, Emoji.HEART);
    }

    // 이미 이모지가 추가되어있는지 체크
    public void checkAlreadyExistFeeling(Comment comment, Member member, Emoji emoji) {
        Optional<Feeling> optionalFeeling = feelingRepository.findFeelingByCommentAndMember(comment, member);
        if (optionalFeeling.isPresent()) {
            Feeling feeling = optionalFeeling.get();
            if (feeling.getEmoji().equals(emoji)) {
                feelingRepository.delete(feeling);
            } else {
                feeling.setEmoji(emoji);
                feelingRepository.save(feeling);
            }
        } else {
            feelingRepository.save(feelingAssembler.toFeelingEntity(comment, member, emoji));
        }
    }

    //이모지 type으로 변환
    public Emoji isEmoji(String emoji) {

        if (emoji==null) {
            throw new BaseException(BaseResponseCode.CONTENT_NULL);
        }
        switch (emoji) {
            case "HEART" -> {
                return Emoji.HEART;
            }
            case "THUMBSUP" -> {
                return Emoji.THUMBSUP;
            }
            case "HAPPY" -> {
                return Emoji.HAPPY;
            }
            case "SAD" -> {
                return Emoji.SAD;
            }
            case "CHECK" -> {
                return Emoji.CHECK;
            }
            default -> throw new BaseException(BaseResponseCode.BAD_REQUEST);
        }
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
