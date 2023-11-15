package com.letsRoll.letsRoll.Memoir.service;

import com.letsRoll.letsRoll.Goal.dto.res.GoalResDto;
import com.letsRoll.letsRoll.Goal.entity.Goal;
import com.letsRoll.letsRoll.Member.repository.MemberRepository;
import com.letsRoll.letsRoll.Memoir.dto.res.MemoirResDto;
import com.letsRoll.letsRoll.Memoir.entity.Memoir;
import com.letsRoll.letsRoll.Memoir.repository.MemoirRepository;
import com.letsRoll.letsRoll.global.exception.BaseException;
import com.letsRoll.letsRoll.global.exception.BaseResponseCode;
import lombok.RequiredArgsConstructor;

import org.springframework.stereotype.Service;

import java.util.List;

@Service
@RequiredArgsConstructor
public class MemoirService {

    private final MemoirRepository memoirRepository;


    public List<MemoirResDto> getMemoirs(Long projectId) {
        List<Memoir> memoirs = memoirRepository.findByProjectId(projectId);
        return MemoirResDto.fromEntities(memoirs);
    }

    public MemoirResDto getMemoir(Long memoirId) {
        Memoir memoir = memoirRepository.findById(memoirId)
                .orElseThrow(() -> new BaseException(BaseResponseCode.NOT_FOUND_MEMOIR));
        return MemoirResDto.fromEntity(memoir);
    }
}





