package com.yanyshivskyi.yMathTest.repos;

import com.yanyshivskyi.yMathTest.domain.Result;
import com.yanyshivskyi.yMathTest.domain.Test;
import com.yanyshivskyi.yMathTest.domain.User;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.Optional;

public interface ResultRepo extends JpaRepository<Result, Long> {
    Result findByNumberTry(String number);
    Optional<Result> findByTestAndUser(Test test, User user);
}