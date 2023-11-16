package com.letsRoll.letsRoll.Goal.dto.res;

import com.letsRoll.letsRoll.Comment_Feeling.dto.res.CommentInfoDto;
import com.letsRoll.letsRoll.Comment_Feeling.dto.res.TodoCommentResDto;
import com.letsRoll.letsRoll.Comment_Feeling.entity.Comment;
import lombok.Builder;
import lombok.Getter;

import java.util.List;

@Builder
@Getter
public class TimeLineResDto {

    List<CommentInfoDto> goalCommentList;
    List<TodoCommentResDto> todoCommentList;
}
