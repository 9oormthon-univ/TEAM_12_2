package com.letsRoll.letsRoll.Comment_Feeling.controller;

import com.letsRoll.letsRoll.Comment_Feeling.dto.req.CommentReqDto;
import com.letsRoll.letsRoll.Comment_Feeling.dto.res.TodoCommentResDto;
import com.letsRoll.letsRoll.Comment_Feeling.service.CommentService;
import com.letsRoll.letsRoll.global.common.BaseResponse;
import com.letsRoll.letsRoll.global.exception.BaseResponseCode;
import jakarta.validation.Valid;
import lombok.RequiredArgsConstructor;
import org.springframework.web.bind.annotation.*;

@RestController
@RequiredArgsConstructor
public class CommentController {
    private final CommentService commentService;

    @PostMapping("/comments")
    public BaseResponse<Void> addComment(@RequestBody @Valid CommentReqDto commentReqDto, @RequestParam String type) {
        commentService.addComment(commentReqDto, type);
        return new BaseResponse<>(BaseResponseCode.SUCCESS);
    }

    @GetMapping("/todo/{todoId}/comment")
    public BaseResponse<TodoCommentResDto> getTodoComment(@PathVariable Long todoId) {
        return new BaseResponse<>(commentService.getTodoComment(todoId));
    }
}


