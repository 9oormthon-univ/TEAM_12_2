package com.letsRoll.letsRoll.User.dto;

import com.letsRoll.letsRoll.User.dto.req.UserReq;
import com.letsRoll.letsRoll.User.dto.res.UserTokenDto;
import com.letsRoll.letsRoll.User.entity.User;
import com.letsRoll.letsRoll.global.enums.Common;
import com.letsRoll.letsRoll.global.enums.Role;
import lombok.Getter;
import lombok.RequiredArgsConstructor;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Component;

@Component
@RequiredArgsConstructor
public class UserAssembler {

    public User toEntity(UserReq userReq, PasswordEncoder encoder) {
        return User.builder()
                .userName(userReq.getUserName())
                .password(encoder.encode(userReq.getPassword()))
                .role(Role.USER)
                .build();
    }

    public UserTokenDto userTokenDto(String token) {
        return UserTokenDto.builder()
                .accessToken(token.split(Common.COMMA.getValue())[0])
                .refreshToken(token.split(Common.COMMA.getValue())[1])
                .build();
    }
}
