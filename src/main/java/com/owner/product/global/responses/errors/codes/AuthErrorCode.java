package com.owner.product.global.responses.errors.codes;

import lombok.Getter;
import lombok.RequiredArgsConstructor;
import org.springframework.http.HttpStatus;

@Getter
@RequiredArgsConstructor
public enum AuthErrorCode implements ErrorCode {
    NOT_FOUND(HttpStatus.NOT_FOUND, "U100", "해당 유저를 찾을 수 없습니다."),
    DUPLICATION(HttpStatus.BAD_REQUEST, "U200", "중복된 유저 입니다."),
    FAIL_REFRESH(HttpStatus.BAD_REQUEST, "A300", "토큰 새로고침에 실패했습니다."),
    TOKEN_EXPIRED(HttpStatus.BAD_REQUEST, "A400", "만료된 토큰입니다.")
    ;

    private final HttpStatus httpStatus;

    private final String errorCode;

    private final String message;
}
