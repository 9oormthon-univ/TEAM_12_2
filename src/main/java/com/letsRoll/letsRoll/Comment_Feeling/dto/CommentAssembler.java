package com.letsRoll.letsRoll.Comment_Feeling.dto;

import com.letsRoll.letsRoll.Comment_Feeling.entity.Comment;
import com.letsRoll.letsRoll.Goal.entity.Goal;
import com.letsRoll.letsRoll.Member.entity.Member;
import com.letsRoll.letsRoll.Todo.entity.Todo;
import com.letsRoll.letsRoll.global.enums.CommentType;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Component;

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
}
