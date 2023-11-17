package com.letsRoll.letsRoll.Memoir.dto;

import com.letsRoll.letsRoll.Member.dto.MemberAssembler;
import com.letsRoll.letsRoll.Member.dto.res.MemberResDto;
import com.letsRoll.letsRoll.Memoir.dto.res.MemoirResDto;
import com.letsRoll.letsRoll.Memoir.entity.Memoir;
import com.letsRoll.letsRoll.Project.dto.res.ProjectResDto;
import com.letsRoll.letsRoll.Project.entity.Project;
import org.springframework.stereotype.Component;

import java.util.List;
import java.util.stream.Collectors;

@Component
public class MemoirAssembler {
    public static List<MemoirResDto> fromEntities(List<Memoir> memoirs) {
        return memoirs.stream()
                .map(MemoirAssembler::fromEntity)
                .collect(Collectors.toList());
    }

    public static MemoirResDto fromEntity(Memoir memoir) {
        MemoirResDto memoirDto = new MemoirResDto();
        memoirDto.setId(memoir.getId());

        // Member 엔티티를 MemberResDto로 변환
        MemberResDto memberResDto = MemberAssembler.fromEntity(memoir.getMember());
        memoirDto.setMember(memberResDto);

        memoirDto.setContent(memoir.getContent());

        // Project 엔티티를 ProjectResDto로 변환
        Project project = memoir.getMember().getProject();
        ProjectResDto projectResDto = new ProjectResDto();
        projectResDto.setProjectId(project.getId());
        projectResDto.setTitle(project.getTitle());

        memoirDto.setProject(projectResDto);

        return memoirDto;
    }
}
