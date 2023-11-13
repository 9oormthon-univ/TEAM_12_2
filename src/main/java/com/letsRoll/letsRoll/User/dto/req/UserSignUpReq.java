package com.letsRoll.letsRoll.User.dto.req;

import jakarta.validation.Valid;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@AllArgsConstructor
@NoArgsConstructor
@Builder
@Valid
public class UserSignUpReq {
    private String userName;
    private String password;
}
