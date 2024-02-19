package com.owner.product.domain.auth.controller;

import com.owner.product.domain.auth.service.AuthService;
import com.owner.product.domain.user.dto.request.UserRequestDto;
import com.owner.product.global.responses.success.codes.AuthSuccessCode;
import com.owner.product.global.responses.success.response.SuccessResponse;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;
import jakarta.validation.Valid;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@Slf4j
@RestController
@RequiredArgsConstructor
@RequestMapping("/auths")
public class AuthController {
    private final AuthService authService;

    @PostMapping()
    public ResponseEntity<SuccessResponse> login(
            @RequestBody UserRequestDto.@Valid Login userLoginRequest,
            HttpServletResponse response
            ) {
        return SuccessResponse.toResponseEntity(
                AuthSuccessCode.LOGIN,
                authService.login(userLoginRequest, response)
        );
    }

    @PostMapping("/refresh")
    public ResponseEntity<SuccessResponse> refresh(
        HttpServletRequest request,
        HttpServletResponse response
    ) {
        return SuccessResponse.toResponseEntity(
                AuthSuccessCode.REFRESH,
                authService.refresh(request, response)
        );
    }

}
