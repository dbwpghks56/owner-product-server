package com.owner.product.domain.auth.service;

import com.owner.product.domain.user.dto.request.UserRequestDto;
import com.owner.product.domain.user.security.UserDetailsImpl;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;

public interface AuthService {
    String login(UserRequestDto.Login userLoginRequest, HttpServletResponse response);
    String refresh(HttpServletRequest request, HttpServletResponse response);
}
