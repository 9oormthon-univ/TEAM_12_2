package com.letsRoll.letsRoll.User.dto.res;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;

@Builder
@Data
@AllArgsConstructor
public class UserTokenDto {

    private String accessToken;
    private String refreshToken;
}
