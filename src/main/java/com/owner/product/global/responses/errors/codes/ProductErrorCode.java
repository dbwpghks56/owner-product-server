package com.owner.product.global.responses.errors.codes;

import lombok.Getter;
import lombok.RequiredArgsConstructor;
import org.springframework.http.HttpStatus;

@Getter
@RequiredArgsConstructor
public enum ProductErrorCode implements ErrorCode {
    NOT_FOUND(HttpStatus.NOT_FOUND, "P100", "해당 상품을 찾을 수 없습니다."),
    ;

    private final HttpStatus httpStatus;

    private final String errorCode;

    private final String message;
}
