package com.yanyshivskyi.yMathTest.repos;

import com.yanyshivskyi.yMathTest.domain.User;
import org.springframework.data.jpa.repository.JpaRepository;

public interface UserRepo extends JpaRepository<User, Long> {
    User findByUsername(String username);
}