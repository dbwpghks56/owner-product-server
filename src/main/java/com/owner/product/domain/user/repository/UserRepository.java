package com.owner.product.domain.user.repository;

import com.owner.product.domain.user.entity.User;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.Optional;

public interface UserRepository extends JpaRepository<User, Long> {
    Optional<User> findByPhoneId(String phoneId);

    boolean existsByPhoneId(String phoneId);
}
