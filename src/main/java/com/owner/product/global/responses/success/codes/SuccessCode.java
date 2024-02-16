package com.owner.product.global.responses.success.codes;

import org.springframework.http.HttpStatus;

public interface SuccessCode {
    String name();

    HttpStatus getHttpStatus();

    String getSuccessCode();

    String getMessage();
}
