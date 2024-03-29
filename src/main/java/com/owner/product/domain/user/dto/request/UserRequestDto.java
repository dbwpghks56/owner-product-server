package com.owner.product.domain.user.dto.request;

import com.owner.product.domain.user.entity.User;
import jakarta.validation.Valid;
import jakarta.validation.constraints.Pattern;
import lombok.Getter;
import lombok.Setter;

@Getter
@Setter
@Valid
public class UserRequestDto {
    @Getter
    @Setter
    public static class Token {
        private String refreshToken;
    }

    @Getter
    @Setter
    @Valid
    public static class Register {
        @Pattern(regexp="^\\d{3}-\\d{4}-\\d{4}$", message="휴대전화 번호는 ###-####-#### 형식이어야 합니다.")
        private String phoneId;
        private String password;

        public User toEntity() {
            return User.builder()
                    .phoneId(this.phoneId)
                    .password(this.password)
                    .build();
        }
    }

    @Getter
    @Setter
    @Valid
    public static class Login {
        @Pattern(regexp="^\\d{3}-\\d{4}-\\d{4}$", message="휴대전화 번호는 ###-####-#### 형식이어야 합니다.")
        private String phoneId;
        private String password;
    }
}
