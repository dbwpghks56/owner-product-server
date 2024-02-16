package com.owner.product.domain.user.security;

import com.owner.product.domain.user.entity.User;
import com.owner.product.domain.user.repository.UserRepository;
import com.owner.product.global.responses.errors.codes.UserErrorCode;
import com.owner.product.global.responses.errors.exceptions.RestBusinessException;
import lombok.RequiredArgsConstructor;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

@Service
@RequiredArgsConstructor
public class CustomUserDetailsServiceImpl implements UserDetailsService {
    private final UserRepository userRepository;

    @Override
    @Transactional(readOnly = true)
    public UserDetails loadUserByUsername(String phoneId) throws UsernameNotFoundException {
        User user = userRepository.findByPhoneId(phoneId).orElseThrow(
                () -> new RestBusinessException(UserErrorCode.NOT_FOUND)
        );

        return user.toUserDetails();
    }
}
