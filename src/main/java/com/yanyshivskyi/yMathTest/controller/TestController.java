package com.yanyshivskyi.yMathTest.controller;


import com.yanyshivskyi.yMathTest.domain.Test;
import com.yanyshivskyi.yMathTest.repos.TestRepo;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.access.prepost.PreAuthorize;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.*;

import java.sql.Time;

@Controller
@PreAuthorize("hasAuthority('ADMIN')")
public class TestController {
    @Autowired TestRepo tr;

    @GetMapping("/create")
    public String createTest(Model model) {
        return "createTest";
    }

    @PostMapping("/create")
    public String saveTest(@RequestParam String textTest){
        Test t= new Test(textTest, "123", Time.valueOf("00:15:00"), 4);
        tr.save(t);
        return "redirect:/create";
    }
}
