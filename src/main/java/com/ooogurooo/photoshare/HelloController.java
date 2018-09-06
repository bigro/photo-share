package com.ooogurooo.photoshare;

import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;

@Controller
@RequestMapping
public class HelloController {
    
    @GetMapping("/")
    public String hello(Model model) {
        model.addAttribute("hello", "Hello World!!");
        return "hello";
    }
}
