package com.owner.product.global.responses.errors.response;

import com.fasterxml.jackson.annotation.JsonInclude;
import com.fasterxml.jackson.annotation.JsonProperty;
import com.owner.product.global.responses.MetaResponse;
import com.owner.product.global.responses.errors.codes.ErrorCode;
import jakarta.validation.ConstraintViolationException;
import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.NotEmpty;
import jakarta.validation.constraints.NotNull;
import lombok.*;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.security.authentication.BadCredentialsException;
import org.springframework.validation.FieldError;

import java.util.List;
import java.util.stream.Collectors;

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

    private ErrorResponse(final @NotEmpty List<FieldError> errors) {
        this.meta = new MetaResponse(HttpStatus.BAD_REQUEST.value(), HttpStatus.BAD_REQUEST.getReasonPhrase());
    }

    private ErrorResponse(final @NotEmpty ConstraintViolationException e) {
        this.meta = new MetaResponse(HttpStatus.BAD_REQUEST.value(), e.getMessage());
    }

    private ErrorResponse(final @NotEmpty BadCredentialsException e) {
        this.meta = new MetaResponse(HttpStatus.UNAUTHORIZED.value(), e.getMessage());
    }

    public static ResponseEntity<ErrorResponse> toResponseEntity(final @NotNull ErrorCode errorCode) {

        return ResponseEntity
                .status(errorCode.getHttpStatus())
                .body(new ErrorResponse(errorCode));
    }

    public static ResponseEntity<ErrorResponse> toResponseEntity(final @NotEmpty List<FieldError> errors) {
        return ResponseEntity
                .badRequest()
                .body(new ErrorResponse(errors));
    }

    public static ResponseEntity<ErrorResponse> toResponseEntity(final @NotEmpty ConstraintViolationException error) {
        return ResponseEntity
                .badRequest()
                .body(new ErrorResponse(error));
    }

    public static ResponseEntity<ErrorResponse> toResponseEntity(final @NotEmpty BadCredentialsException error) {
        return ResponseEntity
                .status(HttpStatus.UNAUTHORIZED)
                .body(new ErrorResponse(error));
    }
}
