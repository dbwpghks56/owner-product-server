package com.owner.product.global.responses.success.codes;

import lombok.Getter;
import lombok.RequiredArgsConstructor;
import org.springframework.http.HttpStatus;

@Getter
@RequiredArgsConstructor
public enum ProductSuccessCode implements SuccessCode{
    SAVE (HttpStatus.CREATED, "PS100", "상품이 생성되었습니다."),
    FIND (HttpStatus.OK, "PS150", "조회 성공"),
    UPDATE(HttpStatus.OK, "PS200", "상품 정보가 수정되었습니다."),
    DELETE(HttpStatus.OK, "PS300", "상품 삭제에 성공했습니다.")
    ;

    private final HttpStatus httpStatus;

    private final String successCode;

    private final String message;
}
