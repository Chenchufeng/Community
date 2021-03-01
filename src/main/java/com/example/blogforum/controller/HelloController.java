package com.example.blogforum.controller;

import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestParam;

@Controller
public class HelloController {
    @GetMapping("/Kevin")
    public String hello(@RequestParam(name = "name",defaultValue = "World") String name, Model model){
        model.addAttribute("name",name);
        return "hello";
    }
}
