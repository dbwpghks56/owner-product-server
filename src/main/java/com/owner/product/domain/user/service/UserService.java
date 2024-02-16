package com.owner.product.domain.user.service;

import com.owner.product.domain.user.dto.request.UserRequestDto;

public interface UserService {
    String register(UserRequestDto.Register userRegisterDto);
}
