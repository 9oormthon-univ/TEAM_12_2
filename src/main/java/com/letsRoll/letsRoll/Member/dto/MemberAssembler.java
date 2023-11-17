package com.letsRoll.letsRoll.Member.dto;

import com.letsRoll.letsRoll.Member.dto.res.MemberResDto;
import com.letsRoll.letsRoll.Member.entity.Member;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Component;

import java.util.List;

@Component
@RequiredArgsConstructor
public class MemberAssembler {

    public static List<MemberResDto> fromEntities(List<Member> members) {
        return members.stream()
                .map(MemberAssembler::fromEntity)
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
