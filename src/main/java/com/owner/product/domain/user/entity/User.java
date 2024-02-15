package com.owner.product.domain.user.entity;

import com.owner.product.global.base.BaseEntity;
import jakarta.persistence.*;
import jakarta.validation.constraints.Pattern;
import lombok.*;
import lombok.experimental.SuperBuilder;
import org.hibernate.annotations.DynamicUpdate;

@Getter
@SuperBuilder
@Entity
@DynamicUpdate
@Table(name = "tb_user")
@NoArgsConstructor(access = AccessLevel.PROTECTED)
@AllArgsConstructor(access = AccessLevel.PRIVATE)
public class User extends BaseEntity {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @Pattern(regexp="^\\d{3}-\\d{4}-\\d{4}$", message="휴대전화 번호는 ###-####-#### 형식이어야 합니다.")
    @Column(nullable = false)
    private String phoneId;

    @Column(nullable = false)
    private String password;
}
