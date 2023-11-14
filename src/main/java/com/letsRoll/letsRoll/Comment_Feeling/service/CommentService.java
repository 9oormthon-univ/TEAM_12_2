package com.letsRoll.letsRoll.Comment_Feeling.service;

import com.letsRoll.letsRoll.Comment_Feeling.dto.CommentAssembler;
import com.letsRoll.letsRoll.Comment_Feeling.dto.req.CommentReqDto;
import com.letsRoll.letsRoll.Comment_Feeling.repository.CommentRepository;
import com.letsRoll.letsRoll.Goal.entity.Goal;
import com.letsRoll.letsRoll.Goal.repository.GoalRepository;
import com.letsRoll.letsRoll.Member.entity.Member;
import com.letsRoll.letsRoll.Member.repository.MemberRepository;
import com.letsRoll.letsRoll.Todo.entity.Todo;
import com.letsRoll.letsRoll.Todo.repository.TodoRepository;
import com.letsRoll.letsRoll.global.exception.BaseException;
import com.letsRoll.letsRoll.global.exception.BaseResponseCode;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

@Service
@RequiredArgsConstructor
public class CommentService {
    private final GoalRepository goalRepository;
    private final MemberRepository memberRepository;
    private final CommentAssembler commentAssembler;
    private final CommentRepository commentRepository;
    private final TodoRepository todoRepository;




    public void addComment(CommentReqDto commentReqDto, String type) {
        Goal goal = goalRepository.findGoalByIdAndIsComplete(commentReqDto.getGoalId(), false)
                .orElseThrow(() -> new BaseException(BaseResponseCode.NOT_FOUND_GOAL));
        Member member = memberRepository.findMemberById(commentReqDto.getMemberId()).orElseThrow(() -> new BaseException(BaseResponseCode.NOT_FOUND_MEMBER));
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
}
