package com.yanyshivskyi.yMathTest.service;

import com.yanyshivskyi.yMathTest.domain.*;
import com.yanyshivskyi.yMathTest.repos.*;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Sort;
import org.springframework.stereotype.Service;

import java.io.Serializable;
import java.text.SimpleDateFormat;
import java.util.Date;
import java.util.List;
import java.util.Optional;

@Service
public class CompleteTestService implements Serializable {
    @Autowired
    private TestRepo testRepo;

    @Autowired
    private QuestionRepo questr;

    @Autowired
    private AnswerRepo answerr;

    @Autowired
    private ResultRepo resultr;

    @Autowired
    private UserRepo userr;


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


    public void saveResult(Long id_test, String mystr, Float fpoint) {
        Date date=new Date();
        SimpleDateFormat sformat = new SimpleDateFormat("yyyy.MM.dd hh:mm:ss");
        sformat.format(date);

        Optional<Result> prevRes=resultr.findByTestAndUser(testRepo.findById(id_test).get(), userr.findByUsername(mystr));
        int countTry=0;
        if(prevRes.isPresent()) {
            countTry=prevRes.get().getNumberTry()-1;
            if(prevRes.get().getPoint() > fpoint) {
                fpoint=prevRes.get().getPoint();
            }
            if(prevRes.get().getNumberTry()==0) return;
            resultr.delete(prevRes.get());
        }
        else {
            countTry=testRepo.findById(id_test).get().getCountTry()-1;
        }


        Result a=new Result(userr.findByUsername(mystr), testRepo.findById(id_test).get(), fpoint, date, countTry);
        resultr.save(a);
    }

    public List<Result> findResults() {
       return resultr.findAllByOrderByIdDesc();
    }

    public float getPoint(Test test) {
        float point= 0;
        List<Question> allquest = questr.findAll();
        for(Question i: allquest){
            if(i.getIdTest()==test) {
                point+=i.getCountPoint();
            }
        }
        return point;
    }

    public List<Result> findResultsT(String filter1) {
        List<Test> ts=testRepo.findByNameContainingIgnoreCase(filter1);
        return resultr.findByTestInOrderByIdDesc(ts);
    }

    public List<Result> findResultsU(String filter2) {
        List<User> us=userr.findByUsernameContainingIgnoreCase(filter2);
        return resultr.findByUserInOrderByIdDesc(us);
    }


    public List<Result> findResultsG(String filter3) {
        List<User> us=userr.findByGroupstContainingIgnoreCase(filter3);
        return resultr.findByUserInOrderByIdDesc(us);
    }


    public List<Result> findResultsTU(String filter1, String filter2) {
        List<Test> ts=testRepo.findByNameContainingIgnoreCase(filter1);
        List<User> us=userr.findByUsernameContainingIgnoreCase(filter2);
        return resultr.findByTestInAndUserInOrderByIdDesc(ts, us);
    }

    public List<Result> findResultsTG(String filter1, String filter3) {
        List<Test> ts=testRepo.findByNameContainingIgnoreCase(filter1);
        List<User> us=userr.findByGroupstContainingIgnoreCase(filter3);
        return resultr.findByTestInAndUserInOrderByIdDesc(ts, us);
    }

    public List<Result> findResultsUG(String filter2, String filter3) {
        List<User> us=userr.findByUsernameContainingIgnoreCaseAndGroupstContainingIgnoreCase(filter2, filter3);
        return resultr.findByUserInOrderByIdDesc(us);
    }

    public List<Result> findResultsTUG(String filter1, String filter2, String filter3) {
        List<Test> ts=testRepo.findByNameContainingIgnoreCase(filter1);
        List<User> us=userr.findByUsernameContainingIgnoreCaseAndGroupstContainingIgnoreCase(filter2, filter3);
        return resultr.findByTestInAndUserInOrderByIdDesc(ts, us);
    }
}
