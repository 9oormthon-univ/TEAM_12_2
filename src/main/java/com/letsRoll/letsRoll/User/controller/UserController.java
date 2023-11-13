package com.letsRoll.letsRoll.User.controller;

import com.letsRoll.letsRoll.User.Service.UserService;
import com.letsRoll.letsRoll.User.dto.req.UserSignUpReq;
import com.letsRoll.letsRoll.global.common.BaseResponse;
import com.letsRoll.letsRoll.global.exception.BaseResponseCode;
import jakarta.validation.Valid;
import lombok.RequiredArgsConstructor;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequestMapping("/user")
@RequiredArgsConstructor
public class UserController {

    private final UserService userService;

    @PostMapping // 회원 가입
    public BaseResponse<Void> signUp(@RequestBody @Valid UserSignUpReq userSignUpReq) {
        userService.signUp(userSignUpReq);
        return new BaseResponse<>(BaseResponseCode.SUCCESS);
    }
}
