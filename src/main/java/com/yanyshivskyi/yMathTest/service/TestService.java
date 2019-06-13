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

import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.Date;
import java.util.List;
import java.util.Optional;

@Service
public class TestService {
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

    public void createTest(String count_tst, String lim_time, String textTest, String descrip,
                           String[] cquesttype, String[] cquest, String[] canswer, String canswer1,
                           String[] canswercor) throws ParseException {


        int foo; //количество попыток прохождения - count_tst
        try {
            foo = Integer.parseInt(count_tst);
        }
        catch (NumberFormatException e)
        {
            foo = 0;
        }
        SimpleDateFormat dateParser = new SimpleDateFormat("HH:mm");
// Parsing the date
        Date date = dateParser.parse(lim_time);
// Format for output
        SimpleDateFormat dateFormatter = new SimpleDateFormat("HH:mm:ss");
        Test t= new Test(textTest, descrip, dateFormatter.format(date), foo);
        testRepo.save(t);

        int j=0; // счетчик 1 правильный ответ
        int curj=0; //текущий правильный ответ
        int questTypeTrue=0; //номер вопроса с 1м правильным ответом
        int idTrueQuest=0; //множественный варианты счетчик
        Answer answ;
        Question quest;

        for(int i=0; i<cquesttype.length;i++) {
            quest = new Question(t, cquest[i], "", "", (float) 1, cquesttype[i]);
            questr.save(quest);
            if (cquesttype[i].equals("0")) {
                curj = 0;
                System.out.println("q");
                while (!canswer[j].equals("**/**")) {
                    System.out.println("w");
                    if (canswer1.charAt(questTypeTrue)-48 == curj) {
                        answ = new Answer(quest, canswer[j], true, null);
                        System.out.println("trr");
                    } else {
                        answ = new Answer(quest, canswer[j], false, null);
                    }
                    answerr.save(answ);
                    j++;
                    curj++;
                }
                j++;
                questTypeTrue++;
            }

            if (cquesttype[i].equals("1")) {
                curj=0;

                while (!canswer[j].equals("**/**")) {
                    if (canswercor[idTrueQuest].charAt(0)-48 == curj) {
                        answ = new Answer(quest, canswer[j], true, null);
                        idTrueQuest++;
                    } else {
                        answ = new Answer(quest, canswer[j], false, null);
                    }
                    answerr.save(answ);
                    j++;
                    curj++;
                }
                j++;
            }

            if (cquesttype[i].equals("2")) {
                answ = new Answer(quest, canswer[j], true, null);
                answerr.save(answ);
                j += 2; // скипаем **/**
            }

            if (cquesttype[i].equals("3")) {
                while (!canswer[j].equals("**/**")) {
                    answ = new Answer(quest, canswer[j], true, canswer[j + 1]);
                    answerr.save(answ);
                    j += 2; // скипаем **/**
                }
                j++; //last
            }
            if(canswercor[idTrueQuest].equals("**/**")) idTrueQuest++;
        }

    }
}
