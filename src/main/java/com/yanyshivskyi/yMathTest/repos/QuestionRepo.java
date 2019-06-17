package com.yanyshivskyi.yMathTest.repos;

import com.yanyshivskyi.yMathTest.domain.Question;
import com.yanyshivskyi.yMathTest.domain.Test;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.List;
import java.util.Optional;

public interface QuestionRepo extends JpaRepository<Question, Long> {
        Question findByQuestionText(String text);

        List<Question> findByIdTest(Optional<Test> all);
}