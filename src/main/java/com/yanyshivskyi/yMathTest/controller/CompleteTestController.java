package com.yanyshivskyi.yMathTest.controller;

import com.yanyshivskyi.yMathTest.domain.Answer;
import com.yanyshivskyi.yMathTest.domain.Question;
import com.yanyshivskyi.yMathTest.domain.Result;
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
    public String test(@RequestParam(required = false, defaultValue = "") String filter1,
                       @RequestParam(required = false, defaultValue = "") String filter2,
                       @RequestParam(required = false, defaultValue = "") String filter3,
                       Model model) {
        List<Result> listRes = cTestService.findResults();

        if ( (filter1!=null && !filter1.isEmpty()) &&
             (filter2==null ||  filter2.isEmpty()) &&
             (filter3==null || filter3.isEmpty())){
                listRes = cTestService.findResultsT(filter1);
        } else if ( (filter2!=null && !filter2.isEmpty()) &&
                    (filter1==null ||  filter1.isEmpty()) &&
                    (filter3==null || filter3.isEmpty())) {
                        listRes = cTestService.findResultsU(filter2);
        } else if ( (filter3!=null && !filter3.isEmpty()) &&
                    (filter1==null ||  filter1.isEmpty()) &&
                    (filter2==null || filter2.isEmpty())) {
                        listRes = cTestService.findResultsG(filter3);
        } else if ( (filter1!=null && !filter1.isEmpty()) &&
                    (filter2!=null &&  !filter2.isEmpty()) &&
                    (filter3==null || filter3.isEmpty())) {
                        listRes=cTestService.findResultsTU(filter1, filter2);
        } else if ( (filter1!=null && !filter1.isEmpty()) &&
                    (filter3!=null &&  !filter3.isEmpty()) &&
                    (filter2==null || filter2.isEmpty())) {
                        listRes=cTestService.findResultsTG(filter1, filter3);
        } else if ( (filter2!=null && !filter2.isEmpty()) &&
                    (filter3!=null &&  !filter3.isEmpty()) &&
                    (filter1==null || filter1.isEmpty())) {
                        listRes=cTestService.findResultsUG(filter2, filter3);
        } else if ( (filter1!=null && !filter1.isEmpty()) &&
                    (filter2!=null &&  !filter2.isEmpty()) &&
                    (filter3!=null && !filter3.isEmpty())) {
                        listRes=cTestService.findResultsTUG(filter1,filter2, filter3);
        } else {        listRes=cTestService.findResults();
        }

        model.addAttribute("filter1", filter1);
        model.addAttribute("filter2", filter2);
        model.addAttribute("filter3", filter3);

        model.addAttribute("results", listRes);


        List<Float> maxPoint=new ArrayList<>();

        for(Result res:listRes){
            maxPoint.add(cTestService.getPoint(res.getTest()));
        }
        model.addAttribute("maxPoint", maxPoint);

        return "result";
    }

}

