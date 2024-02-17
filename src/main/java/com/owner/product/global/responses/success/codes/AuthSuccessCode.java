package com.owner.product.global.responses.success.codes;

import lombok.Getter;
import lombok.RequiredArgsConstructor;
import org.springframework.http.HttpStatus;

@Getter
@RequiredArgsConstructor
public enum AuthSuccessCode implements SuccessCode {
    LOGIN(HttpStatus.OK, "US100", "로그인에 성공하였습니다."),
    REFRESH(HttpStatus.OK, "US200", "토큰이 새로고침 되었습니다.")
    ;

    private final HttpStatus httpStatus;

    private final String successCode;

    private final String message;
}
