package com.example.firstproject.controller;

import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;

@Controller
public class FirstController {
    @GetMapping("/hi")
    public String niceToMeetYou(Model model){//모델 활용
        model.addAttribute("username","정호");
        return "greetings";//뷰페이지를 찾아서 보여줌
    }
    @GetMapping("/bye")
    public String seeYouNext(Model model){
        model.addAttribute("nickname","wjdgh");
        return "goodbye";//보여줄 view 템플릿 페이지를 나타냄
    }
}
