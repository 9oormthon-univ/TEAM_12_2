package com.letsRoll.letsRoll.global.exception;

import lombok.AllArgsConstructor;
import lombok.Getter;

@Getter
@AllArgsConstructor
public class BaseException extends RuntimeException {
    private BaseResponseCode baseResponseCode;

}