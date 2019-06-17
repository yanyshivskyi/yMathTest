package com.yanyshivskyi.yMathTest.repos;

import com.yanyshivskyi.yMathTest.domain.Result;
import com.yanyshivskyi.yMathTest.domain.Test;
import com.yanyshivskyi.yMathTest.domain.User;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.List;
import java.util.Optional;

public interface ResultRepo extends JpaRepository<Result, Long> {
    Optional<Result> findByTestAndUser(Test test, User user);

    List<Result> findByTestInOrderByIdDesc(List<Test> ts);
    List<Result> findByUserInOrderByIdDesc(List<User> ts);
    List<Result> findByTestInAndUserInOrderByIdDesc(List<Test> ts, List<User> us);
    List<Result> findAllByOrderByIdDesc();
}