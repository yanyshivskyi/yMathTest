package com.yanyshivskyi.yMathTest.controller;

import com.yanyshivskyi.yMathTest.domain.Answer;
import com.yanyshivskyi.yMathTest.domain.Question;
import com.yanyshivskyi.yMathTest.service.CompleteTestService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.*;

import java.util.*;


@Controller
public class CompleteTestController {
    @Autowired
    private CompleteTestService cTestService;


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

        return "testId";
    }


    @RequestMapping(value = "/test/{id}", method = RequestMethod.POST)
    public String compllTes1(@RequestParam String mystr) {
        System.out.println(mystr);
        return "redirect:/main";
    }


}

