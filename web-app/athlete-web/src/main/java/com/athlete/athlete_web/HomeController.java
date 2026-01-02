package com.athlete.athlete_web;

import com.athlete.athlete_web.model.Athlete;
import com.athlete.athlete_web.service.AthleteService;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.ResponseBody;

import java.util.stream.Collectors;

@Controller
public class HomeController {
    private final AthleteService athleteService;
    public HomeController(AthleteService athleteService) {
        this.athleteService = athleteService;
    }

    @GetMapping("/")
    @ResponseBody
    public String home() {
        return "Athlete Web App is running!";
    }

    @GetMapping("/athletes")
    @ResponseBody
     public String athletes() {
        return athleteService.getAthletes()
                .stream()
                .map(a -> a.getName()
                        + " | Age: " + a.getAge()
                        + " | Sport: " + a.getSport())
                .collect(Collectors.joining("\n"));
    }
}
