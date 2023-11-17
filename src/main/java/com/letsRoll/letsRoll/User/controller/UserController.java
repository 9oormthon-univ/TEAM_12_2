package com.letsRoll.letsRoll.User.controller;


import com.letsRoll.letsRoll.User.Service.UserService;
import com.letsRoll.letsRoll.User.dto.req.UserSignInReq;
import com.letsRoll.letsRoll.User.dto.req.UserSignUpReq;
import com.letsRoll.letsRoll.User.entity.User;
import com.letsRoll.letsRoll.global.common.BaseResponse;
import com.letsRoll.letsRoll.global.exception.BaseResponseCode;
import jakarta.servlet.http.HttpSession;
import jakarta.validation.Valid;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.web.bind.annotation.*;

@Slf4j
@RestController
@RequestMapping("/api/users")
@RequiredArgsConstructor
public class UserController {

    private final UserService userService;
    private final HttpSession httpSession;
    @PostMapping("/signup") // 회원 가입
    public BaseResponse<Void> signUp(@RequestBody @Valid UserSignUpReq userSignUpReq) {
        userService.signUp(userSignUpReq);
        return new BaseResponse<>(BaseResponseCode.SUCCESS);
    }

    @PostMapping("/login")
    public BaseResponse<User> signIn(@RequestBody @Valid UserSignInReq userSignInReq, HttpSession session) {
        User loggedInUser = userService.login(userSignInReq);
        // 로그인 성공 시 세션에 사용자 정보 저장

        loggedInUser.setSessionId(session.getId());
        return new BaseResponse<>(loggedInUser);
    }
}