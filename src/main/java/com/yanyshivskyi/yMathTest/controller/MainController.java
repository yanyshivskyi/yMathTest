package com.yanyshivskyi.yMathTest.controller;

import com.yanyshivskyi.yMathTest.domain.Test;
import com.yanyshivskyi.yMathTest.domain.User;
import com.yanyshivskyi.yMathTest.service.TestService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.domain.Sort;
import org.springframework.data.web.PageableDefault;
import org.springframework.security.access.prepost.PreAuthorize;
import org.springframework.security.core.annotation.AuthenticationPrincipal;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestParam;

@Controller
public class MainController {
    @Autowired
    private TestService testService;

    @Value("${upload.path}")
    private String uploadPath;

    @GetMapping("/")
    public String greeting(Model model) {
        return "greeting";
    }


    @PreAuthorize("hasAuthority('USER')")
    @GetMapping("/main")
    public String main(@AuthenticationPrincipal final User user,
                       @RequestParam(required=false, defaultValue = "") String filter, Model model,
                       @PageableDefault(sort = { "id" }, direction = Sort.Direction.DESC) Pageable pageable) {


        Page<Test> page=testService.findAll(pageable);
        if (filter!=null && !filter.isEmpty()) {
            page = testService.findTest(filter, pageable);
        }
        else {
            page=testService.findAll(pageable);
        }
        model.addAttribute("page", page);
        model.addAttribute("filter", filter);
        int[] count_qq=new int[page.getSize()];
        int[] count_gg=new int[page.getSize()];
        int j=0;
        for(Test i:page){
            count_qq[j] = testService.countQuestion(i.getId());
            count_gg[j] = testService.countTry(i, user);
            j++;
        }
        model.addAttribute("count_q", count_qq);
        model.addAttribute("url", "/main");
        model.addAttribute("count_g", count_gg);

        return "main";
    }

}

