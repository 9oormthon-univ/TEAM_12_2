package com.letsRoll.letsRoll.Member.controller;

import com.letsRoll.letsRoll.Member.dto.res.MemberResDto;
import com.letsRoll.letsRoll.Member.service.MemberService;
import com.letsRoll.letsRoll.global.common.BaseResponse;
import lombok.RequiredArgsConstructor;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import java.util.List;

@RestController
@RequestMapping("/api/members")
@RequiredArgsConstructor
public class MemberController {
    private final MemberService memberService;
    @GetMapping("/{projectId}")
    public BaseResponse<List<MemberResDto>> getMemberList(@PathVariable Long projectId) {
        return new BaseResponse<>(memberService.getMemberList(projectId));
    }
}
