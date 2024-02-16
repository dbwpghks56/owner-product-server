package com.owner.product.global.responses.errors.handler;

import com.owner.product.global.responses.errors.codes.CommonErrorCode;
import com.owner.product.global.responses.errors.exceptions.RestBusinessException;
import com.owner.product.global.responses.errors.response.ErrorResponse;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.validation.ConstraintViolationException;
import jakarta.validation.constraints.NotNull;
import lombok.extern.slf4j.Slf4j;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.MethodArgumentNotValidException;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.bind.annotation.RestControllerAdvice;

@Slf4j
@RestControllerAdvice
public class RestControllerHandler {
    @ExceptionHandler(RestBusinessException.class)
    public ResponseEntity<ErrorResponse> handleBusinessException(final @NotNull RestBusinessException e,
                                                                 final @NotNull HttpServletRequest request) {

        log.error("Handle ['business exception'] - code: '{}', message: '{}'", e.getErrorCode().getErrorCode(), e.getErrorCode().getMessage());

        return ErrorResponse.toResponseEntity(e.getErrorCode());
    }

    @ExceptionHandler(MethodArgumentNotValidException.class)
    public ResponseEntity<ErrorResponse> handleMethodArgumentNotValidException (final @NotNull MethodArgumentNotValidException e,
                                                                                final @NotNull HttpServletRequest request) {

        log.error("Handle ['method argument not valid exception'] - code: '{}', message: '{}'", CommonErrorCode.BAD_REQUEST.getErrorCode(), e.getMessage());

        return ErrorResponse.toResponseEntity(e.getBindingResult().getFieldErrors());
    }

    @ExceptionHandler(ConstraintViolationException.class)
    public ResponseEntity<ErrorResponse> handleConstraintViolationException (final @NotNull ConstraintViolationException e,
                                                                                final @NotNull HttpServletRequest request) {

        log.error("Handle ['method argument not valid exception'] - code: '{}', message: '{}'", CommonErrorCode.BAD_REQUEST.getErrorCode(), e.getMessage());

        return ErrorResponse.toResponseEntity(e);
    }
}
