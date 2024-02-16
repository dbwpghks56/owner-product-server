package com.owner.product.global.responses.errors.response;

import com.fasterxml.jackson.annotation.JsonInclude;
import com.fasterxml.jackson.annotation.JsonProperty;
import com.owner.product.global.responses.MetaResponse;
import com.owner.product.global.responses.errors.codes.ErrorCode;
import jakarta.validation.constraints.NotNull;
import lombok.*;
import org.springframework.http.ResponseEntity;

@Getter
@Builder(access = AccessLevel.PRIVATE)
@AllArgsConstructor(access = AccessLevel.PRIVATE)
@NoArgsConstructor(access = AccessLevel.PRIVATE)
@JsonInclude(JsonInclude.Include.NON_NULL)
public class ErrorResponse {
    @JsonProperty("meta")
    private MetaResponse meta;

    private ErrorResponse(final @NotNull ErrorCode errorCode) {
        this.meta = new MetaResponse(errorCode.getHttpStatus().value(), errorCode.getMessage());
    }

    public static ResponseEntity<ErrorResponse> toResponseEntity(final @NotNull ErrorCode errorCode) {

        return ResponseEntity
                .status(errorCode.getHttpStatus())
                .body(new ErrorResponse(errorCode));
    }
}
