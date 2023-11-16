package com.letsRoll.letsRoll.Comment_Feeling.dto;

import com.letsRoll.letsRoll.Comment_Feeling.dto.res.CommentInfoDto;
import com.letsRoll.letsRoll.Comment_Feeling.dto.res.TodoCommentResDto;
import com.letsRoll.letsRoll.Comment_Feeling.entity.Comment;
import com.letsRoll.letsRoll.Goal.entity.Goal;
import com.letsRoll.letsRoll.Member.entity.Member;
import com.letsRoll.letsRoll.Todo.entity.Todo;
import com.letsRoll.letsRoll.global.enums.CommentType;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Component;

import java.time.LocalDateTime;
import java.util.List;

@Component
@RequiredArgsConstructor
public class CommentAssembler {

    public Comment toCommentEntity(Goal goal, Member member, String content) {
        return Comment.builder()
                .goal(goal)
                .member(member)
                .content(content)
                .type(CommentType.GOAL)
                .build();
    }
    public Comment toCommentEntity(Goal goal, Todo todo, Member member, String content) {
        return Comment.builder()
                .goal(goal)
                .todo(todo)
                .member(member)
                .content(content)
                .type(CommentType.TODO)
                .build();
    }

    public CommentInfoDto toCommentInfoDtoEntity(LocalDateTime createdTime, String content, Long memberId, Integer emojiCount, List<Long> feelingCheckMemberId) {
        return CommentInfoDto.builder()
                .createdTime(createdTime)
                .content(content)
                .emojiCount(emojiCount)
                .commentMemberId(memberId)
                .feelingCheckMemberId(feelingCheckMemberId)
                .build();
    }


    public TodoCommentResDto toTodoCommentResDtoEntity(Todo todo, List<CommentInfoDto> commentList) {
        return TodoCommentResDto.builder()
                .todoId(todo.getId())
                .todoContent(todo.getContent())
                .todoManagerMemberId(todo.getTodoManager().getMember().getId())
                .todoEndDate(todo.getEndDate())
                .commentList(commentList)
                .build();
    }
}
