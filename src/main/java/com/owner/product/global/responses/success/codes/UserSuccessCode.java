package com.owner.product.global.responses.success.codes;

import lombok.Getter;
import lombok.RequiredArgsConstructor;
import org.springframework.http.HttpStatus;

@Getter
@RequiredArgsConstructor
public enum UserSuccessCode implements SuccessCode{
    LOGOUT (HttpStatus.OK, "US100", "로그아웃 되었습니다."),
    FINDME (HttpStatus.OK, "US150", "마이페이지 조회 성공"),
    REGISTER(HttpStatus.CREATED, "US200", "회원가입에 성공했습니다.");

    private final HttpStatus httpStatus;

    private final String successCode;

    private final String message;
}
