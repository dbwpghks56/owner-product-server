package com.owner.product.domain.user.controller;

import com.owner.product.domain.user.dto.request.UserRequestDto;
import com.owner.product.domain.user.service.UserService;
import com.owner.product.global.responses.success.codes.UserSuccessCode;
import com.owner.product.global.responses.success.response.SuccessResponse;
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
@RequestMapping("/users")
public class UserController {
    private final UserService userService;

    @PostMapping()
    public ResponseEntity<SuccessResponse> register(@RequestBody UserRequestDto. @Valid Register userRegisterDto) {
        return SuccessResponse.toResponseEntity(
                UserSuccessCode.REGISTER,
                userService.register(userRegisterDto)
        );
    }
}
