package com.owner.product.domain.user.controller;

import com.owner.product.domain.user.dto.request.UserRequestDto;
import com.owner.product.domain.user.security.UserDetailsImpl;
import com.owner.product.domain.user.service.UserService;
import com.owner.product.global.responses.success.codes.UserSuccessCode;
import com.owner.product.global.responses.success.response.SuccessResponse;
import jakarta.validation.Valid;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.http.ResponseEntity;
import org.springframework.security.core.annotation.AuthenticationPrincipal;
import org.springframework.web.bind.annotation.*;

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

    @GetMapping()
    public ResponseEntity<SuccessResponse> getMe(
            @AuthenticationPrincipal UserDetailsImpl userDetails
            ) {
        return SuccessResponse.toResponseEntity(
                UserSuccessCode.FINDME,
                userService.me(userDetails)
        );
    }
}
