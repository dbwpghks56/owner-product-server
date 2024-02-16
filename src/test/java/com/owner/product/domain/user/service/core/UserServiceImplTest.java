package com.owner.product.domain.user.service.core;

import com.owner.product.domain.user.dto.request.UserRequestDto;
import com.owner.product.domain.user.entity.User;
import com.owner.product.domain.user.repository.UserRepository;
import com.owner.product.global.responses.success.codes.UserSuccessCode;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.MockitoAnnotations;
import org.springframework.boot.test.context.SpringBootTest;

import static org.junit.jupiter.api.Assertions.*;
import static org.mockito.Mockito.*;

@SpringBootTest
class UserServiceImplTest {
    @Mock
    private UserRepository userRepository;

    @InjectMocks
    private UserServiceImpl userService;

    @BeforeEach
    void setUp() {
        MockitoAnnotations.openMocks(this);
    }

    @Test
    void register() {
        UserRequestDto.Register userRegisterDto = new UserRequestDto.Register();
        userRegisterDto.setPhoneId("010-4748-6110");
        userRegisterDto.setPassword("asdfasdf");

        when(userRepository.existsByPhoneId(anyString())).thenReturn(false);

        // When
        String result = userService.register(userRegisterDto);

        // Then
        assertEquals(UserSuccessCode.REGISTER.getMessage(), result);
    }

    @Test
    void existsPhoneId() {
        String uniquePhoneId = "010-9876-5432";
        when(userRepository.existsByPhoneId(uniquePhoneId)).thenReturn(false);

        // When
        boolean result = userService.existsPhoneId(uniquePhoneId);

        // Then
        assertFalse(result);
    }
}