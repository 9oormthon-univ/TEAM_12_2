package com.letsRoll.letsRoll.Member.dto.res;

import com.letsRoll.letsRoll.Member.entity.Member;
import lombok.Getter;
import lombok.Setter;

import java.util.List;

@Getter
@Setter
public class MemberResDto {
    private Long id;
    private String nickname;
    private String role;
}
