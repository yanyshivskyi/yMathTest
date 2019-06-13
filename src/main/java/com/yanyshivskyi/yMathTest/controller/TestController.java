package com.yanyshivskyi.yMathTest.controller;


import com.yanyshivskyi.yMathTest.domain.Answer;
import com.yanyshivskyi.yMathTest.domain.Question;
import com.yanyshivskyi.yMathTest.domain.Test;
import com.yanyshivskyi.yMathTest.repos.AnswerRepo;
import com.yanyshivskyi.yMathTest.repos.QuestionRepo;
import com.yanyshivskyi.yMathTest.repos.TestRepo;
import com.yanyshivskyi.yMathTest.service.TestService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.access.prepost.PreAuthorize;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestParam;

import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.Date;

@Controller
@PreAuthorize("hasAuthority('ADMIN')")
public class TestController {
    @Autowired
    TestService tr;

    @GetMapping("/create")
    public String createTest(Model model) {
        return "createTest";
    }

    @PostMapping("create")
    public String saveTest(@RequestParam String textTest,@RequestParam String descrip,
                           @RequestParam String lim_time, @RequestParam String count_tst,
                           @RequestParam String[] cquest,
                           @RequestParam String[] cquesttype, @RequestParam String[] canswer,
                           @RequestParam(required=false, name="canswercor") String[] canswercor,
                           @RequestParam(required = false, name="canswer1") String canswer1) throws ParseException {

        tr.createTest(count_tst, lim_time, textTest, descrip, cquesttype, cquest, canswer, canswer1, canswercor);

        return "redirect:/main";
    }
}
