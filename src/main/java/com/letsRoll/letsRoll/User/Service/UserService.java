package com.letsRoll.letsRoll.User.Service;

import com.letsRoll.letsRoll.User.dto.req.UserSignUpReq;
import com.letsRoll.letsRoll.User.entity.User;
import com.letsRoll.letsRoll.User.repository.UserRepository;
import com.letsRoll.letsRoll.global.exception.BaseException;
import com.letsRoll.letsRoll.global.exception.BaseResponseCode;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

@Service
@RequiredArgsConstructor
public class UserService {
    private final UserRepository userRepository;

    public void signUp(UserSignUpReq userSignUpReq) {
        if (userRepository.findUserByUserName(userSignUpReq.getUserName()).isPresent()) {
            throw new BaseException(BaseResponseCode.ALREADY_EXIST_USER);
        } else {
            User user = User.builder()
                    .userName(userSignUpReq.getUserName())
                    .password(userSignUpReq.getPassword()).build();
            userRepository.save(user);
        }
    }
}
