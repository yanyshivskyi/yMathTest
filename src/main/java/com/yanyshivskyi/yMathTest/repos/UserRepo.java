package com.yanyshivskyi.yMathTest.repos;

import com.yanyshivskyi.yMathTest.domain.User;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.List;

public interface UserRepo extends JpaRepository<User, Long> {
    User findByUsername(String username);
    List<User> findByUsernameContainingIgnoreCase(String username);
    List<User> findByGroupstContainingIgnoreCase(String groupSt);
    List<User> findByUsernameContainingIgnoreCaseAndGroupstContainingIgnoreCase(String username, String groupSt);
}