package com.letsRoll.letsRoll.global.exception;

import lombok.AllArgsConstructor;
import lombok.Getter;
import org.springframework.http.HttpStatus;

import java.util.Arrays;

@Getter
@AllArgsConstructor
public enum BaseResponseCode {

    //success
    SUCCESS("S0001", HttpStatus.OK, "요청에 성공했습니다"),

    // global
    NO_PERMISSION("GL001", HttpStatus.FORBIDDEN, "권한이 없습니다."),
    NULL_REQUEST_PARAM("GL002", HttpStatus.BAD_REQUEST, "쿼리 파라미터가 없습니다."),
    BAD_REQUEST("GL003", HttpStatus.BAD_REQUEST, "잘못된 요청입니다."),
    CONTENT_NULL("GL004", HttpStatus.BAD_REQUEST, "내용을 입력해 주세요."),

    // user
    ALREADY_EXIST_USER("U0001", HttpStatus.CONFLICT, "이미 존재하는 USER입니다"),

    // member
    NOT_FOUND_MEMBER("M0001", HttpStatus.NOT_FOUND, "멤버를 찾을 수 없습니다."),

    // goal
    NOT_FOUND_GOAL("G0001", HttpStatus.NOT_FOUND, "Goal을 찾을 수 없습니다."),

    // todo
    NOT_FOUND_TODO("T0001", HttpStatus.NOT_FOUND, "Todo를 찾을 수 없습니다."),

    // todoManager
    NOT_FOUND_TODOMANAGER("TM0001", HttpStatus.NOT_FOUND, "TodoManager를 찾을 수 없습니다"),

    ;

    public final String code;
    public final HttpStatus status;
    public final String message;

    public static BaseResponseCode findByCode(String code) {
        return Arrays.stream(BaseResponseCode.values())
                .filter(b -> b.getCode().equals(code))
                .findAny().orElseThrow(() -> new BaseException(BAD_REQUEST));
    }
}
