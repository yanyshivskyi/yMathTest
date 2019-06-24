package com.yanyshivskyi.yMathTest.controller;

import com.yanyshivskyi.yMathTest.domain.Answer;
import com.yanyshivskyi.yMathTest.domain.Question;
import com.yanyshivskyi.yMathTest.domain.Test;
import com.yanyshivskyi.yMathTest.service.CompleteTestService;
import com.yanyshivskyi.yMathTest.service.TestService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.access.prepost.PreAuthorize;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.multipart.MultipartFile;

import javax.validation.Valid;
import java.io.IOException;
import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.*;

@Controller
@PreAuthorize("hasAuthority('ADMIN')")
public class EditController {
    @Autowired
    private CompleteTestService cTestService;

    @Autowired
    private TestService tr;

    private Long id_test;

    @GetMapping("/edit/{id}")
    public String editTest(@PathVariable Long id, Model model) {
        List<Question> list =cTestService.findQuests(id);
        Test test =tr.findTestID(id);
        List<List<Answer>> all = new ArrayList<>();
        List<Answer> answers=cTestService.findAnswer(list.get(0));
        for(Question qst:list) {
            List<Answer> answer = cTestService.findAnswer(qst);
            Collections.shuffle(answer);
            all.add(answer);
        }

        model.addAttribute("quests", list);
        model.addAttribute("answers", all);
        model.addAttribute("testt", test);
        model.addAttribute("sizze", all.size());
        id_test=id;

        return "editTest";
    }

  /*  @PostMapping("edit/{id}")
    public String saveTest(
            @RequestParam String[] cquest, @RequestParam String[] cquesttype,
            @RequestParam Float[] countPoint, @RequestParam(required = false) MultipartFile[] filename1,
            @RequestParam(required = false) MultipartFile[] filename2, @RequestParam String[] canswer,
            @RequestParam(required=false, name="canswercor") String[] canswercor,
            @RequestParam(required = false, name="canswer1") String canswer1,
            @Valid Test test, BindingResult bindingResult,
            Model model) throws ParseException, IOException {

        SimpleDateFormat dateParser = new SimpleDateFormat("HH:mm");
        Date date = dateParser.parse(test.getTime());
        SimpleDateFormat dateFormatter = new SimpleDateFormat("HH:mm:ss");
        test.setTime(dateFormatter.format(date));
        if(test.getCountTry()==null) test.setCountTry(0);

        if (bindingResult.hasErrors()) {
            Map<String, String> errorsMap = ControllerUtils.getErrors(bindingResult);
            model.mergeAttributes(errorsMap);
            model.addAttribute("test", test);
            return "createTest";
        } else {
            System.out.println(cquesttype[0]+"asss");
            tr.createTest(test, cquesttype, cquest, canswer, canswer1, canswercor, filename1, filename2, countPoint);
            model.addAttribute("test", null);
        }
        return "redirect:/main";
    }

   */
}
