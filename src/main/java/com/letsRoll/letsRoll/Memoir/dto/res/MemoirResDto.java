package com.letsRoll.letsRoll.Memoir.dto.res;

import com.letsRoll.letsRoll.Member.dto.res.MemberResDto;
import com.letsRoll.letsRoll.Member.entity.Member;
import com.letsRoll.letsRoll.Memoir.entity.Memoir;
import com.letsRoll.letsRoll.Project.dto.res.ProjectResDto;
import com.letsRoll.letsRoll.Project.entity.Project;
import lombok.Getter;
import lombok.Setter;

import java.util.List;
import java.util.stream.Collectors;

@Getter
@Setter
public class MemoirResDto {
    private Long id;
    private MemberResDto member; // Member 엔티티 대신 MemberResDto 사용
    private String content;
    private ProjectResDto project; // Project 엔티티 대신 ProjectResDto 사용

    public static List<MemoirResDto> fromEntities(List<Memoir> memoirs) {
        return memoirs.stream()
                .map(MemoirResDto::fromEntity)
                .collect(Collectors.toList());
    }

    public static MemoirResDto fromEntity(Memoir memoir) {
        MemoirResDto memoirDto = new MemoirResDto();
        memoirDto.setId(memoir.getId());

        // Member 엔티티를 MemberResDto로 변환
        MemberResDto memberResDto = MemberResDto.fromEntity(memoir.getMember());
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
