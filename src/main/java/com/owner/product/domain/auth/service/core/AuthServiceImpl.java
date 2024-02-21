package com.owner.product.domain.auth.service.core;

import com.owner.product.domain.auth.service.AuthService;
import com.owner.product.domain.user.dto.request.UserRequestDto;
import com.owner.product.domain.user.entity.User;
import com.owner.product.domain.user.repository.UserRepository;
import com.owner.product.domain.user.security.CustomUserDetailsServiceImpl;
import com.owner.product.domain.user.security.UserDetailsImpl;
import com.owner.product.global.auth.JwtTokenProvider;
import com.owner.product.global.responses.errors.codes.AuthErrorCode;
import com.owner.product.global.responses.errors.codes.UserErrorCode;
import com.owner.product.global.responses.errors.exceptions.RestBusinessException;
import com.owner.product.global.responses.success.codes.AuthSuccessCode;
import jakarta.servlet.http.Cookie;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.http.HttpCookie;
import org.springframework.security.authentication.AuthenticationManager;
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.security.core.userdetails.UserDetailsService;
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
    private final CustomUserDetailsServiceImpl userDetailsService;
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

        return AuthSuccessCode.LOGIN.getMessage();
    }

    @Override
    public String refresh(HttpServletRequest request, HttpServletResponse response) {
        Cookie[] cookies = request.getCookies();
        String refreshToken = "";

        if (cookies != null) {
            for (Cookie cookie : cookies) {
                if (cookie.getName().equals("RefreshToken")) {
                    refreshToken = cookie.getValue();
                }
            }
        }


        if(jwtTokenProvider.validateToken(refreshToken) && !refreshToken.isEmpty()) {
            String username = jwtTokenProvider.getUsernameFromToken(refreshToken);

            UserDetailsImpl userDetails = (UserDetailsImpl) userDetailsService.loadUserByUsername(username);

            UsernamePasswordAuthenticationToken authenticationToken =
                    new UsernamePasswordAuthenticationToken(userDetails, null, userDetails.getAuthorities());


            String newToken = jwtTokenProvider.generateToken(authenticationToken);

            Cookie accessCookie = new Cookie("AccessToken", newToken);

            accessCookie.setHttpOnly(true);
            accessCookie.setSecure(true);
            accessCookie.setPath("/api/v1");

            response.addCookie(accessCookie);

            return AuthSuccessCode.REFRESH.getMessage();
        }

        throw new RestBusinessException(AuthErrorCode.FAIL_REFRESH);
    }
}
