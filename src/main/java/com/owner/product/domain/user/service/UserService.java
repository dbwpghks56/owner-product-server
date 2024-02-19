package com.owner.product.domain.user.service;

import com.owner.product.domain.user.dto.request.UserRequestDto;
import com.owner.product.domain.user.security.UserDetailsImpl;

public interface UserService {
    String register(UserRequestDto.Register userRegisterDto);

    String me(UserDetailsImpl userDetails);
}
