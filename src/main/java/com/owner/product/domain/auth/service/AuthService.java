package com.owner.product.domain.auth.service;

import com.owner.product.domain.user.dto.request.UserRequestDto;
import jakarta.servlet.http.HttpServletResponse;

public interface AuthService {
    String login(UserRequestDto.Login userLoginRequest, HttpServletResponse response);
    String refresh(UserRequestDto.Token userTokenRequest);
}
