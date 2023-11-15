package com.letsRoll.letsRoll.Comment_Feeling.controller;

import com.letsRoll.letsRoll.Comment_Feeling.dto.req.CommentReqDto;
import com.letsRoll.letsRoll.Comment_Feeling.dto.req.GoalFeelingReqDto;
import com.letsRoll.letsRoll.Comment_Feeling.dto.req.TodoFeelingReqDto;
import com.letsRoll.letsRoll.Comment_Feeling.dto.res.TodoCommentResDto;
import com.letsRoll.letsRoll.Comment_Feeling.service.CommentService;
import com.letsRoll.letsRoll.global.common.BaseResponse;
import com.letsRoll.letsRoll.global.exception.BaseResponseCode;
import jakarta.validation.Valid;
import lombok.RequiredArgsConstructor;
import org.springframework.web.bind.annotation.*;

@RestController
@RequiredArgsConstructor
@RequestMapping("/api")
public class CommentController {
    private final CommentService commentService;

    @PostMapping("/comments")
    public BaseResponse<Void> addComment(@RequestBody @Valid CommentReqDto commentReqDto, @RequestParam String type) {
        commentService.addComment(commentReqDto, type);
        return new BaseResponse<>(BaseResponseCode.SUCCESS);
    }

    @GetMapping("/todos/{todoId}/comment")
    public BaseResponse<TodoCommentResDto> getTodoComment(@PathVariable Long todoId) {
        return new BaseResponse<>(commentService.getTodoComment(todoId));
    }

    @PostMapping("/todos/{todoId}/{commentId}")
    public BaseResponse<Void> addTodoCommentFeeling(@PathVariable Long todoId, @PathVariable Long commentId, @RequestBody @Valid TodoFeelingReqDto todoFeelingReqDto) {
        commentService.addTodoCommentFeeling(todoId, commentId, todoFeelingReqDto);
        return new BaseResponse<>(BaseResponseCode.SUCCESS);
    }


    @PostMapping("/goals/{goalId}/{commentId}")
    public BaseResponse<Void> addGoalCommentFeeling(@PathVariable Long goalId, @PathVariable Long commentId, @RequestBody @Valid GoalFeelingReqDto goalFeelingReqDto) {
        commentService.addGoalCommentFeeling(goalId, commentId, goalFeelingReqDto);
        return new BaseResponse<>(BaseResponseCode.SUCCESS);
    }
}


