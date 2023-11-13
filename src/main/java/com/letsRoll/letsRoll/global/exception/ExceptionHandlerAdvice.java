package com.letsRoll.letsRoll.global.exception;

import com.letsRoll.letsRoll.global.common.BaseResponse;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.MethodArgumentNotValidException;
import org.springframework.web.bind.MissingServletRequestParameterException;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.bind.annotation.ResponseStatus;
import org.springframework.web.bind.annotation.RestControllerAdvice;

import java.util.Objects;

@RestControllerAdvice
public class ExceptionHandlerAdvice {
    @ExceptionHandler(BaseException.class)
    protected ResponseEntity<?> handleBaseException(BaseException e) {
        return ResponseEntity.status(e.getBaseResponseCode().getStatus())
                .body(BaseResponse.error(e.getBaseResponseCode().getStatus().value(), e.getBaseResponseCode().getCode(), e.getBaseResponseCode().getMessage()));
    }

    @ResponseStatus(HttpStatus.BAD_REQUEST)
    @ExceptionHandler(MethodArgumentNotValidException.class)
    protected ResponseEntity<?> handleMethodArgumentNotValidException(MethodArgumentNotValidException e) {
        Objects.requireNonNull(e.getFieldError());
        BaseResponseCode baseResponseCode = BaseResponseCode.findByCode(e.getFieldError().getDefaultMessage());
        return ResponseEntity.status(HttpStatus.BAD_REQUEST)
                .body(BaseResponse.error(baseResponseCode.getStatus().value(), baseResponseCode.getCode(), baseResponseCode.getMessage()));
    }

    @ResponseStatus(HttpStatus.BAD_REQUEST)
    @ExceptionHandler(MissingServletRequestParameterException.class)
    protected ResponseEntity<?> handleMissingServletRequestParameterException(MissingServletRequestParameterException e) {
        return ResponseEntity.status(HttpStatus.BAD_REQUEST)
                .body(BaseResponse.error(HttpStatus.BAD_REQUEST.value(), BaseResponseCode.NULL_REQUEST_PARAM.getCode(), BaseResponseCode.NULL_REQUEST_PARAM.getMessage()));
    }
}