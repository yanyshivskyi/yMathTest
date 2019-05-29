package com.yanyshivskyi.yMathTest.controller;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.multipart.MultipartFile;

import java.io.File;
import java.io.IOException;
import java.util.Map;
import java.util.UUID;

@Controller
public class MainController {
  //  @Autowired
  //  private SimpleAnswerRepo answerRepo;

    @Value("${upload.path}")
    private String uploadPath;

    @GetMapping("/")
    public String greeting(Map<String, Object> model) {
        return "greeting";
    }

    @GetMapping("/test")
    public String test(Map<String, Object> model) {
        return "test";
    }


    @GetMapping("/main")
    public String main(@RequestParam(required=false, defaultValue = "") String filter, Map<String, Object> model) {
     /*   Iterable<SimpleAnswer> t_in =answerRepo.findAll();
        if (filter!=null && !filter.isEmpty()) {
            t_in = answerRepo.findByQuestion(filter);
        }
        else {
            t_in=answerRepo.findAll();
        }
        model.put("tests_input", t_in);
        model.put("filter", filter);

      */
        return "main";
    }


    @PostMapping("/main")
    public String add(@RequestParam("file") MultipartFile file,
                      @RequestParam String question,
                      @RequestParam String answer,
                      Map<String, Object> model
    ) throws IOException {
       // SimpleAnswer test_in = new SimpleAnswer(question, answer);
        if (file !=null && !file.getOriginalFilename().isEmpty()) {
            File uploadDir = new File(uploadPath);
            if(!uploadDir.exists()) {
                uploadDir.mkdir();
            }

            String uuidFile = UUID.randomUUID().toString();
            String resultFileName=uuidFile+"."+file.getOriginalFilename();
            file.transferTo(new File(uploadPath+"/"+resultFileName));
          //  test_in.setFilename(resultFileName);
        }

     //   answerRepo.save(test_in);

    //    Iterable<SimpleAnswer> t_in = answerRepo.findAll();
    //    model.put("tests_input", t_in);
        return "main";
    }

}