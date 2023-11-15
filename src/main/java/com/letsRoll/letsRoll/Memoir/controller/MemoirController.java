package com.letsRoll.letsRoll.Memoir.controller;

import com.letsRoll.letsRoll.Memoir.dto.req.MemoirAddReq;
import com.letsRoll.letsRoll.Memoir.dto.res.MemoirResDto;
import com.letsRoll.letsRoll.Memoir.entity.Memoir;
import com.letsRoll.letsRoll.Memoir.service.MemoirService;
import com.letsRoll.letsRoll.global.common.BaseResponse;
import com.letsRoll.letsRoll.global.exception.BaseResponseCode;
import jakarta.validation.Valid;
import lombok.RequiredArgsConstructor;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/api/memoirs")
@RequiredArgsConstructor
public class MemoirController {

    private final MemoirService memoirService;

    @GetMapping("/{projectId}")
    public BaseResponse<List<MemoirResDto>> getMemoirs(@PathVariable Long projectId) {
        List<MemoirResDto> memoirs = memoirService.getMemoirs(projectId);
        return new BaseResponse<>(memoirs);
    }

    @GetMapping("/{projectId}/{memoirId}")
    public BaseResponse<MemoirResDto> getMemoir(@PathVariable Long memoirId) {
        MemoirResDto memoir = memoirService.getMemoir(memoirId);
        return new BaseResponse<>(memoir);
    }

}