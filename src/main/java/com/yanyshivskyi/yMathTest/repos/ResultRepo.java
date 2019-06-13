package com.yanyshivskyi.yMathTest.repos;

import com.yanyshivskyi.yMathTest.domain.Result;
import org.springframework.data.jpa.repository.JpaRepository;

public interface ResultRepo extends JpaRepository<Result, Long> {
    Result findByNumberTry(String number);
}