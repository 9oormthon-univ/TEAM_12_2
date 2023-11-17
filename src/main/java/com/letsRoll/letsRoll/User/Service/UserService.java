package com.letsRoll.letsRoll.User.Service;

import com.letsRoll.letsRoll.User.dto.UserAssembler;
import com.letsRoll.letsRoll.User.dto.req.UserIdReq;
import com.letsRoll.letsRoll.User.dto.req.UserReq;
import com.letsRoll.letsRoll.User.dto.res.UserTokenDto;
import com.letsRoll.letsRoll.User.entity.User;
import com.letsRoll.letsRoll.User.repository.UserRepository;
import com.letsRoll.letsRoll.global.exception.BaseException;
import com.letsRoll.letsRoll.global.exception.BaseResponseCode;
import com.letsRoll.letsRoll.global.jwt.JwtTokenProvider;
import jakarta.transaction.Transactional;
import lombok.RequiredArgsConstructor;
import org.springframework.security.config.annotation.authentication.builders.AuthenticationManagerBuilder;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Service;

@Service
@RequiredArgsConstructor
public class UserService {
    private final UserRepository userRepository;
    private final AuthenticationManagerBuilder authenticationManagerBuilder;
    private final JwtTokenProvider jwtTokenProvider;
    private final PasswordEncoder passwordEncoder;
    private final UserAssembler userAssembler;

    @Transactional
    public UserTokenDto signUp(UserReq userReq) {
        if (userRepository.findUserByUserName(userReq.getUserName()).isPresent()) {
            throw new BaseException(BaseResponseCode.ALREADY_EXIST_USER);
        } else {
            User user = userRepository.save(userAssembler.toEntity(userReq, passwordEncoder));
            String token = jwtTokenProvider.createToken(user.getId(), user.getUserName());
            return userAssembler.userTokenDto(token);
        }
    }

    @Transactional
    public UserTokenDto logIn(UserReq userLogInReq) {
        // 1. username + password 를 기반으로 Authentication 객체 생성
        // 이때 authentication 은 인증 여부를 확인하는 authenticated 값이 false
        User user = userRepository.findUserByUserName(userLogInReq.getUserName())
                .orElseThrow(() -> new BaseException(BaseResponseCode.NOT_FOUND_USER));
        if (!passwordEncoder.matches(userLogInReq.getPassword(), user.getPassword())) {
            throw new BaseException(BaseResponseCode.WRONG_PASSWORD);
        }
        String token = jwtTokenProvider.createToken(user.getId(), user.getUserName());
        return userAssembler.userTokenDto(token);
    }

    public void withDraw(UserIdReq userIdReq) {
        User user = userRepository.findUserById(userIdReq.getUserId())
                .orElseThrow(() -> new BaseException(BaseResponseCode.NOT_FOUND_USER));

        if (!passwordEncoder.matches(userSignInReq.getPassword(), user.getPassword())) {
            throw new BaseException(BaseResponseCode.NOT_EQUAL_PASSWORD);
        }

        httpSession.setAttribute("user", user);
        return user;
    }

}
