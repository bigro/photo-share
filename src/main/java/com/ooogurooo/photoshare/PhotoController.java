package com.ooogurooo.photoshare;

import com.ooogurooo.photoshare.model.image.Images;
import com.ooogurooo.photoshare.service.ImageService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;

@Controller
@RequestMapping
public class PhotoController {
    @Autowired
    ImageService imageService;
    
    @GetMapping("/slide")
    public String slide(Model model) {
        addImages(model);
        return "slide";
    }

    @GetMapping("/list")
    public String list(Model model) {
        addImages(model);
        return "list";
    }

    private void addImages(Model model) {
        Images images = imageService.list();
        model.addAttribute("images", images);
    }
}
