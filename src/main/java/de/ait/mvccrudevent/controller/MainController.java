package de.ait.mvccrudevent.controller;

import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;

@Controller
public class MainController {

    @GetMapping("/")
    public String getHome(Model model){
        model.addAttribute("name", "Home page");
        return "home";
    }

    @GetMapping("/about")
    public String getAbout(Model model){
        model.addAttribute("name", "About");
        return "about";
    }
}
