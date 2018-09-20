package com.ooogurooo.photoshare;

import com.ooogurooo.photoshare.service.ImageService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;

@Controller
@RequestMapping
public class HelloController {
    @Autowired
    ImageService imageService;
    
    @GetMapping("/")
    public String hello(Model model) {
        model.addAttribute("hello", "Hello World!!");
        return "hello";
    }

    @GetMapping("/list")
    public String list(Model model) {
        String url = imageService.list();
        model.addAttribute("imageUrl", url);
        return "list";
    }
}
