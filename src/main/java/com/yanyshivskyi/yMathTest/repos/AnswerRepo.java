package com.yanyshivskyi.yMathTest.repos;

import com.yanyshivskyi.yMathTest.domain.Answer;
import com.yanyshivskyi.yMathTest.domain.Question;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.List;

public interface AnswerRepo extends JpaRepository<Answer, Long> {
    Answer findByAnswerText(String text);
    List<Answer> findByQuestion(Question m);
}