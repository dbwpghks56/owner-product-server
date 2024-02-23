package com.owner.product.domain.user.controller;

import com.owner.product.domain.user.dto.request.UserRequestDto;
import com.owner.product.domain.user.security.UserDetailsImpl;
import com.owner.product.global.responses.success.response.SuccessResponse;
import io.swagger.v3.oas.annotations.Operation;
import jakarta.validation.Valid;
import org.springframework.http.ResponseEntity;
import org.springframework.security.core.annotation.AuthenticationPrincipal;
import org.springframework.web.bind.annotation.RequestBody;

public interface UserSwagger {

    @Operation(
            summary = "회원가입 - 관리자 권한",
            description = """
                    ## [회원가입 - 관리자 권한 API]
                    ### 관리자가 카페테리아 어플리케이션에 회원을 등록합니다.
                    
                                     
                    ## [Exceptions]
                    ### 1. UserErrorCode.DUPLICATION : 중복되는 이메일 주소의 회원이 존재하는 경우 해당 예외를 리턴합니다.
                    """
    )
    ResponseEntity<SuccessResponse> register(@RequestBody UserRequestDto. @Valid Register userRegisterDto);

    @Operation(
            summary = "로그인 유저 조회",
            description = """
                    ## [유저 조회]
                    ### 현재 로그인 된 유저의 정보를 조회 합니다.
                    
                                     
                    ## [Exceptions]
                    ### 1. UserErrorCode.DUPLICATION : 중복되는 이메일 주소의 회원이 존재하는 경우 해당 예외를 리턴합니다.
                    """
    )
    ResponseEntity<SuccessResponse> getMe(
            @AuthenticationPrincipal UserDetailsImpl userDetails
    );
}
