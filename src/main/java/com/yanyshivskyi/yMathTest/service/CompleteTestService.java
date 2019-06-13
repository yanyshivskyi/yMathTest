package com.yanyshivskyi.yMathTest.service;

import com.yanyshivskyi.yMathTest.domain.Answer;
import com.yanyshivskyi.yMathTest.domain.Question;
import com.yanyshivskyi.yMathTest.domain.Test;
import com.yanyshivskyi.yMathTest.repos.AnswerRepo;
import com.yanyshivskyi.yMathTest.repos.QuestionRepo;
import com.yanyshivskyi.yMathTest.repos.TestRepo;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Sort;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Optional;

@Service
public class CompleteTestService {
    @Autowired
    private TestRepo testRepo;

    @Autowired
    private QuestionRepo questr;

    @Autowired
    private AnswerRepo answerr;


    public List<Test> findAll() {
        return testRepo.findAll(Sort.by(Sort.Direction.DESC, "id"));
    }

    public int countQuestion(Long id) {
        int count=0;
        Optional<Test> byId = testRepo.findById(id);
        List<Question> allquest = questr.findAll();
        for(Question i: allquest){
            if(i.getIdTest()==byId.get()) count++;
        }
        return count;
    }


    public List<Question> findQuests(Long id) {
        Optional<Test> all = testRepo.findById(id);

        List<Question> list_quest= questr.findByIdTest(all);

        return list_quest;
    }

    public List<Answer> findAnswer(Question qst) {
        return answerr.findByQuestion(qst);
    }
}
