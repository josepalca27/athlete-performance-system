package com.athlete.athlete_web;

import com.athlete.athlete_web.model.Athlete;
import com.athlete.athlete_web.service.AthleteService;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.servlet.mvc.support.RedirectAttributes;
import java.util.List;

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
    public String athletes(Model model) {
        model.addAttribute("athletes", athleteService.getAthletes());
        return "athletes";
    }

    @GetMapping("/athletes/new")
    public String newAthleteForm() {
        return "new_athlete";
    }

    @PostMapping("/athletes")
    public String createAthlete(
            @RequestParam String name,
            @RequestParam int age,
            @RequestParam String sport,
            @RequestParam String position,
            @RequestParam double weight,
            @RequestParam double height,
            @RequestParam String country,
            RedirectAttributes redirectAttributes
    ) {
        boolean added = athleteService.addAthlete(name, age, sport, position, weight, height, country);

        if (!added) {
            redirectAttributes.addFlashAttribute("error", "Athlete name already exists or is invalid.");
            return "redirect:/athletes/new";
        }

        athleteService.saveAllData();
        return "redirect:/athletes";
    }
}