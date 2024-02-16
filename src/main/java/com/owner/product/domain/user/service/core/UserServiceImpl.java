package com.owner.product.domain.user.service.core;

import com.owner.product.domain.user.dto.request.UserRequestDto;
import com.owner.product.domain.user.entity.User;
import com.owner.product.domain.user.repository.UserRepository;
import com.owner.product.domain.user.service.UserService;
import com.owner.product.global.responses.errors.codes.UserErrorCode;
import com.owner.product.global.responses.errors.exceptions.RestBusinessException;
import com.owner.product.global.responses.success.codes.UserSuccessCode;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

@Service
@RequiredArgsConstructor
@Transactional
@Slf4j
public class UserServiceImpl implements UserService {
    private final UserRepository userRepository;

    @Override
    public String register(UserRequestDto.Register userRegisterDto) {
        if(existsPhoneId(userRegisterDto.getPhoneId())) {
            throw new RestBusinessException(UserErrorCode.DUPLICATION);
        }

        userRepository.save(userRegisterDto.toEntity());
        return UserSuccessCode.REGISTER.getMessage();
    }

    public boolean existsPhoneId(String phoneId) {
        return userRepository.existsByPhoneId(phoneId);
    }
}
