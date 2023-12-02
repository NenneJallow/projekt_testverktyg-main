package com.example.spring_thymeleaf.controller;

import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;

@Controller
public class FormController {

    @GetMapping("/laptimeform")
    public String getTodoForm(){
        return "newtodoform";
    }

    @GetMapping
    public String landingPage(){
        return "redirect:/laptimes";
    }
}
