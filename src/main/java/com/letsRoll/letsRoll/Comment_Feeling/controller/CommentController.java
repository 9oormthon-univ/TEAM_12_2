package com.letsRoll.letsRoll.Comment_Feeling.controller;

import com.letsRoll.letsRoll.Comment_Feeling.dto.req.CommentReqDto;
import com.letsRoll.letsRoll.Comment_Feeling.service.CommentService;
import com.letsRoll.letsRoll.global.common.BaseResponse;
import com.letsRoll.letsRoll.global.exception.BaseResponseCode;
import jakarta.validation.Valid;
import lombok.RequiredArgsConstructor;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequiredArgsConstructor
public class CommentController {
    private final CommentService commentService;

    @PostMapping("/comments")
    public BaseResponse<Void> addComment(@RequestBody @Valid CommentReqDto commentReqDto, @RequestParam String type) {
        commentService.addComment(commentReqDto, type);
        return new BaseResponse<>(BaseResponseCode.SUCCESS);
    }
}
