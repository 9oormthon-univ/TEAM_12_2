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

    public static List<MemberResDto> fromEntities(List<Member> members) {
        return members.stream()
                .map(MemberResDto::fromEntity)
                .toList();
    }

    public static MemberResDto fromEntity(Member member) {
        MemberResDto memberDto = new MemberResDto();
        memberDto.setId(member.getId());
        memberDto.setNickname(member.getNickname());
        memberDto.setRole(member.getRole());
        return memberDto;
    }
}
