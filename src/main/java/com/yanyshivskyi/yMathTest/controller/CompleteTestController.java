package com.yanyshivskyi.yMathTest.controller;

import com.yanyshivskyi.yMathTest.domain.Answer;
import com.yanyshivskyi.yMathTest.domain.Question;
import com.yanyshivskyi.yMathTest.domain.Result;
import com.yanyshivskyi.yMathTest.domain.Test;
import com.yanyshivskyi.yMathTest.service.CompleteTestService;
import net.bytebuddy.description.method.ParameterList;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.*;

import java.util.*;


@Controller
public class CompleteTestController {
    @Autowired
    private CompleteTestService cTestService;

    private Long id_test;


    @GetMapping("test/{id}")
    public String test_idd(@PathVariable Long id, Model model) {
        List<Question> list =cTestService.findQuests(id);
        List<List<Answer>> all = new ArrayList<>();
        List<Answer> answers=cTestService.findAnswer(list.get(0));
        for(Question qst:list) {
            List<Answer> answer = cTestService.findAnswer(qst);
            Collections.shuffle(answer);
            all.add(answer);
        }

        model.addAttribute("quests", list);
        model.addAttribute("answers", all);
        id_test=id;

        return "testId";
    }


    @PostMapping("/test/{id}")
    public String compllTes1(@RequestParam String mystr, @RequestParam Float fpoint) {
        System.out.println(id_test);
        System.out.println(fpoint);
        cTestService.saveResult(id_test, mystr, fpoint);
        return "redirect:/main";
    }

    @GetMapping("/result")
    public String test(Model model) {
        List<Result> listRes= cTestService.findResults();
        model.addAttribute("results", listRes);


        List<Float> maxPoint=new ArrayList<>();

        for(Result res:listRes){
            maxPoint.add(cTestService.getPoint(res.getTest()));
        }
        model.addAttribute("maxPoint", maxPoint);

        return "result";
    }

}

