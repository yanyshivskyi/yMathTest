package com.yanyshivskyi.yMathTest.service;

import com.yanyshivskyi.yMathTest.domain.*;
import com.yanyshivskyi.yMathTest.repos.AnswerRepo;
import com.yanyshivskyi.yMathTest.repos.QuestionRepo;
import com.yanyshivskyi.yMathTest.repos.ResultRepo;
import com.yanyshivskyi.yMathTest.repos.TestRepo;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.stereotype.Service;
import org.springframework.web.multipart.MultipartFile;

import java.io.File;
import java.io.IOException;
import java.text.ParseException;
import java.util.List;
import java.util.Optional;
import java.util.UUID;

@Service
public class TestService {
    @Autowired
    private TestRepo testRepo;

    @Autowired
    private QuestionRepo questr;

    @Autowired
    private AnswerRepo answerr;

    @Autowired
    private ResultRepo resr;

    @Value("${upload.path}")
    private String uploadPath;

    public Test findTestID(Long f){
        return testRepo.findById(f).get();
    }

    public Page<Test> findAll(Pageable pageable) {
        return testRepo.findAll(pageable);
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

    public void createTest(Test test,
                           String[] cquesttype, String[] cquest, String[] canswer, String canswer1,
                           String[] canswercor, MultipartFile[] filename1,MultipartFile[] filename2,
                           Float[] countPoint) throws ParseException, IOException {


        testRepo.save(test);
        System.out.println("QWERTY" + cquesttype[0]);
        int j=0; // счетчик 1 правильный ответ
        int curj=0; //текущий правильный ответ
        int questTypeTrue=0; //номер вопроса с 1м правильным ответом
        int idTrueQuest=0; //множественный варианты счетчик
        Answer answ;
        Question quest;
        String resultFileName1="";
        String resultFileName2="";

        for(int i=0; i<cquesttype.length;i++) {
            if (filename1[i] !=null && !filename1[i].getOriginalFilename().isEmpty()) {
                File uploadDir = new File(uploadPath);
                if (!uploadDir.exists()) {
                    uploadDir.mkdir();
                }

                String uuidFile = UUID.randomUUID().toString();
                resultFileName1 = uuidFile + "." + filename1[i].getOriginalFilename();
                filename1[i].transferTo(new File(uploadPath + "/" + resultFileName1));
            }

                if (filename2[i] !=null && !filename2[i].getOriginalFilename().isEmpty()) {
                    File uploadDir2 = new File(uploadPath);
                    if (!uploadDir2.exists()) {
                        uploadDir2.mkdir();
                    }

                    String uuidFile2 = UUID.randomUUID().toString();
                    resultFileName2 = uuidFile2 + "." + filename2[i].getOriginalFilename();
                    filename2[i].transferTo(new File(uploadPath + "/" + resultFileName2));
                }

            if(countPoint[i]<0 ||countPoint[i].isNaN() || countPoint[i]==null) countPoint[i]= Float.valueOf(1);

            quest = new Question(test, cquest[i], resultFileName1, resultFileName2, countPoint[i], cquesttype[i]);
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

    public Page<Test> findTest(String filter, Pageable pageable) {
        return testRepo.findByNameContainingIgnoreCase(filter, pageable);
    }

    public int countTry(Test test, User user) {
        int count=0;
        if (test.getCountTry()==0) return -1;
        Optional<Result> rs = resr.findByTestAndUser(test, user);
        if(rs.isPresent()) {
            count = rs.get().getNumberTry();
        }
        else count = test.getCountTry();
        return count;
    }
}
