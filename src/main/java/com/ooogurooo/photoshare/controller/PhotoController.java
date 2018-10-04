package com.ooogurooo.photoshare.controller;

import com.ooogurooo.photoshare.model.criteria.Limit;
import com.ooogurooo.photoshare.model.image.Images;
import com.ooogurooo.photoshare.service.ImageService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;

@Controller
@RequestMapping
public class PhotoController {
    @Autowired
    ImageService imageService;
    
    @GetMapping("/slide")
    public String slide(Model model,
                        @RequestParam(value = "limit", required = false) Integer limit) {
        addImages(model, new Limit(limit));
        return "slide";
    }

    @GetMapping("/list")
    public String list(Model model,
                       @RequestParam(value = "limit", required = false) Integer limit) {
        addImages(model, new Limit(limit));
        return "list";
    }

    private void addImages(Model model, Limit limit) {
        Images images = imageService.list(limit);
        model.addAttribute("images", images);
    }
}
