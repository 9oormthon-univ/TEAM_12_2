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
    WRONG_PASSWORD("U0002", HttpStatus.BAD_REQUEST, "비밀번호가 틀렸습니다."),

    NOT_FOUND_USER("U0003", HttpStatus.NOT_FOUND, "USER를 찾을 수 없습니다."),

    NOT_EQUAL_PASSWORD("U0004", HttpStatus.BAD_REQUEST, "비밀번호가 일치하지 않습니다."),

    // member
    NOT_FOUND_MEMBER("M0001", HttpStatus.NOT_FOUND, "MEMBER를 찾을 수 없습니다."),
    NOT_PROJECT_MEMBER("M0002", HttpStatus.BAD_REQUEST, "프로젝트에 속해있지 않은 멤버입니다."),

    // project
    NOT_FOUND_PROJECT("P0001", HttpStatus.NOT_FOUND, "PROJECT를 찾을 수 없습니다."),
    WRONG_PROJECT_PASSWORD("P0002", HttpStatus.BAD_REQUEST, "PROJECT 비밀번호가 틀렸습니다."),

    // goal
    NOT_FOUND_GOAL("G0001", HttpStatus.NOT_FOUND, "GOAL을 찾을 수 없습니다."),
    NOT_COMPLETED_GOAL("G0002", HttpStatus.BAD_REQUEST, "GOAL을 완료해야 합니다."),
    // todo
    NOT_FOUND_TODO("T0001", HttpStatus.NOT_FOUND, "Todo를 찾을 수 없습니다."),
    NOT_ALL_TODOS_COMPLETE("T0002", HttpStatus.BAD_REQUEST, "모든 할 일을 완료해야 합니다."),

    // todoManager
    NOT_FOUND_TODOMANAGER("TM0001", HttpStatus.NOT_FOUND, "TodoManager를 찾을 수 없습니다"),


    // memoir
    ALREADY_WRITTEN_MEMOIR("M0001", HttpStatus.BAD_REQUEST, "이미 회고를 작성했습니다."),
    NOT_FOUND_MEMOIR("M0002", HttpStatus.NOT_FOUND, "회고를 찾을 수 없습니다."),

    // comment
    NOT_FOUND_COMMENT("C0001", HttpStatus.NOT_FOUND, "Comment를 찾을 수 없습니다"),


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
