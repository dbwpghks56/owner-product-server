package com.owner.product.domain.auth.service.core;

import com.owner.product.domain.auth.service.AuthService;
import com.owner.product.domain.user.dto.request.UserRequestDto;
import com.owner.product.domain.user.repository.UserRepository;
import com.owner.product.global.auth.JwtTokenProvider;
import com.owner.product.global.responses.success.codes.AuthSuccessCode;
import jakarta.servlet.http.Cookie;
import jakarta.servlet.http.HttpServletResponse;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.http.HttpCookie;
import org.springframework.security.authentication.AuthenticationManager;
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

@Service
@Slf4j
@RequiredArgsConstructor
@Transactional
public class AuthServiceImpl implements AuthService {
    private final AuthenticationManager authenticationManager;
    private final UserRepository userRepository;
    private final PasswordEncoder passwordEncoder;
    private final JwtTokenProvider jwtTokenProvider;

    @Override
    public String login(UserRequestDto.Login userLoginRequest, HttpServletResponse response) {
        Authentication authentication = authenticationManager.authenticate(new UsernamePasswordAuthenticationToken(
                userLoginRequest.getPhoneId(),
                userLoginRequest.getPassword()
        ));

        SecurityContextHolder.getContext().setAuthentication(authentication);

        String token = jwtTokenProvider.generateToken(authentication);
        String refreshToken = jwtTokenProvider.generateRefreshToken(authentication);

        Cookie accessCookie = new Cookie("AccessToken", token);
        Cookie refreshCookie = new Cookie("RefreshToken", refreshToken);

        accessCookie.setHttpOnly(true);
        accessCookie.setSecure(true);

        refreshCookie.setHttpOnly(true);
        refreshCookie.setSecure(true);

        response.addCookie(accessCookie);
        response.addCookie(refreshCookie);

        log.error(token);
        log.error(refreshToken);

        return AuthSuccessCode.LOGIN.getMessage();
    }

    @Override
    public String refresh(UserRequestDto.Token userTokenRequest) {
        return null;
    }
}
