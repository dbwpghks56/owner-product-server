package com.owner.product.global.responses.errors.codes;

import lombok.Getter;
import lombok.RequiredArgsConstructor;
import org.springframework.http.HttpStatus;

@Getter
@RequiredArgsConstructor
public enum UserErrorCode implements ErrorCode {
    NOT_FOUND(HttpStatus.NOT_FOUND, "U100", "해당 유저를 찾을 수 없습니다."),
    DUPLICATION(HttpStatus.BAD_REQUEST, "U200", "중복된 유저 입니다."),
    PASSWORD_MISMATCH(HttpStatus.BAD_REQUEST, "U300", "비밀번호가 일치하지 않습니다."),
    ;

    private final HttpStatus httpStatus;

    private final String errorCode;

    private final String message;
}
