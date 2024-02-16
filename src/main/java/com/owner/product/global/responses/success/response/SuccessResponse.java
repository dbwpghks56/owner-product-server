package com.owner.product.global.responses.success.response;

import com.fasterxml.jackson.annotation.JsonInclude;
import com.fasterxml.jackson.annotation.JsonProperty;
import com.owner.product.global.responses.MetaResponse;
import com.owner.product.global.responses.success.codes.SuccessCode;
import jakarta.validation.constraints.NotNull;
import lombok.*;
import lombok.extern.slf4j.Slf4j;
import org.springframework.http.HttpHeaders;
import org.springframework.http.ResponseEntity;

@Getter
@Slf4j
@Builder(access = AccessLevel.PRIVATE)
@AllArgsConstructor(access = AccessLevel.PRIVATE)
@NoArgsConstructor(access = AccessLevel.PRIVATE)
@JsonInclude(JsonInclude.Include.NON_NULL)
public class SuccessResponse {
    @JsonProperty("meta")
    private MetaResponse meta;

    @JsonProperty("data")
    private Object data;

    private SuccessResponse(final @NotNull SuccessCode successCode, Object responseObject) {
        this.meta = new MetaResponse(successCode.getHttpStatus().value(), successCode.getMessage());
        this.data = responseObject;
    }

    private SuccessResponse(final @NotNull SuccessCode successCode) {
        this.meta = new MetaResponse(successCode.getHttpStatus().value(), successCode.getMessage());
        this.data = null;
    }

    public static ResponseEntity<SuccessResponse> toResponseEntity(final @NotNull SuccessCode successCode) {
        log.info("Success Response - success code : {}, success message : {}", successCode.getSuccessCode(), successCode.getMessage());

        return ResponseEntity
                .status(successCode.getHttpStatus())
                .body(new SuccessResponse(successCode));
    }

    public static ResponseEntity<SuccessResponse> toResponseEntity(final @NotNull SuccessCode successCode,
                                                                   final @NotNull Object responseObject) {
        log.info("Success Response - success code : {}, success message : {}", successCode.getSuccessCode(), successCode.getMessage());

        return ResponseEntity
                .status(successCode.getHttpStatus())
                .body(new SuccessResponse(successCode, responseObject));
    }

    public static ResponseEntity<SuccessResponse> toResponseEntity(final @NotNull HttpHeaders headers,
                                                                   final @NotNull SuccessCode successCode,
                                                                   final @NotNull Object responseObject) {
        log.info("Success Response - success code : {}, success message : {}", successCode.getSuccessCode(), successCode.getMessage());

        return ResponseEntity
                .status(successCode.getHttpStatus())
                .headers(headers)
                .body(new SuccessResponse(successCode, responseObject));
    }
}
