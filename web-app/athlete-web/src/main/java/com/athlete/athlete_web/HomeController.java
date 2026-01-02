package com.athlete.athlete_web;

import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.ResponseBody;

@Controller
public class HomeController {

    @GetMapping("/")
    @ResponseBody
    public String home() {
        return "Athlete Web App is running!";
    }

    @GetMapping("/athletes")
    @ResponseBody
    public String athletes() {
        return "Athletes page (next: load from CSV)";
    }
}
