package com.letsRoll.letsRoll.User.Service;

import com.letsRoll.letsRoll.User.dto.req.UserSignInReq;
import com.letsRoll.letsRoll.User.dto.req.UserSignUpReq;
import com.letsRoll.letsRoll.User.entity.User;
import com.letsRoll.letsRoll.User.repository.UserRepository;
import com.letsRoll.letsRoll.global.exception.BaseException;
import com.letsRoll.letsRoll.global.exception.BaseResponseCode;
import jakarta.servlet.http.HttpSession;
import lombok.RequiredArgsConstructor;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.stereotype.Service;


@Service
@RequiredArgsConstructor
public class UserService {
    private final UserRepository userRepository;
    private final BCryptPasswordEncoder passwordEncoder = new BCryptPasswordEncoder();

    private final HttpSession httpSession;

    public void signUp(UserSignUpReq userSignUpReq) {
        if (userRepository.findUserByUserName(userSignUpReq.getUserName()).isPresent()) {
            throw new BaseException(BaseResponseCode.ALREADY_EXIST_USER);
        } else {
            User user = User.builder()
                    .userName(userSignUpReq.getUserName())
                    .password(passwordEncoder.encode(userSignUpReq.getPassword()))
                    .nickname(userSignUpReq.getNickname())
                    .build();
            userRepository.save(user);
        }
    }
    public User login(UserSignInReq userSignInReq) {
        User user = userRepository.findUserByUserName(userSignInReq.getUserName())
                .orElseThrow(() -> new BaseException(BaseResponseCode.NOT_FOUND_USER));

        if (!passwordEncoder.matches(userSignInReq.getPassword(), user.getPassword())) {
            throw new BaseException(BaseResponseCode.NOT_EQUAL_PASSWORD);
        }

        httpSession.setAttribute("user", user);
        return user;
    }

}