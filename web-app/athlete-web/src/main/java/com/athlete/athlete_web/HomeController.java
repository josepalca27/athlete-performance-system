package com.athlete.athlete_web;

import com.athlete.athlete_web.model.SoccerSession;
import com.athlete.athlete_web.model.Workout;
import com.athlete.athlete_web.service.AthleteService;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.servlet.mvc.support.RedirectAttributes;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;


@Controller
public class HomeController {
    private final AthleteService athleteService;
    public HomeController(AthleteService athleteService) {
        this.athleteService = athleteService;
    }

    @GetMapping("/")
    public String home() {
        return "home";
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

    @GetMapping("/sessions")
    public String sessions(Model model) {
        List<Map<String, Object>> rows = new ArrayList<>();
        for (var a : athleteService.getAthletes()) {
        // Athlete must have getSoccerSessions()
        for (SoccerSession s : a.getSoccerSessions()) {
            Map<String, Object> row = new HashMap<>();
            row.put("athleteName", a.getName());
            row.put("date", s.getDate());
            row.put("durationMinutes", s.getDurationMinutes());
            row.put("intensityLevel", s.getIntensityLevel());
            row.put("notes", s.getNotes());
            rows.add(row);
        }
    }

    model.addAttribute("rows", rows);
    return "sessions";

    }   
    
    @GetMapping("/sessions/new")
    public String newSessionForm(Model model) {
    model.addAttribute("athletes", athleteService.getAthletes());
    return "new_session";
    }

    @PostMapping("/sessions")
    public String createSession(
        @RequestParam String athleteName,
        @RequestParam String date,
        @RequestParam int durationMinutes,
        @RequestParam int intensityLevel,
        @RequestParam(required = false, defaultValue = "") String notes,
        RedirectAttributes redirectAttributes
    ) {
    boolean ok = athleteService.logSoccerSession(athleteName, date, durationMinutes, intensityLevel, notes);

    if (!ok) {
        redirectAttributes.addFlashAttribute("error", "Athlete not found. Please select a valid athlete.");
        return "redirect:/sessions/new";
    }

    athleteService.saveAllData();
    return "redirect:/sessions";
    }

    @GetMapping("/workouts")
public String workouts(Model model) {
    List<Map<String, Object>> rows = new ArrayList<>();

    for (var a : athleteService.getAthletes()) {
        for (Workout w : a.getWorkouts()) {
            Map<String, Object> row = new HashMap<>();
            row.put("athleteName", a.getName());
            row.put("date", w.getDate());
            row.put("type", w.getType());
            row.put("durationMinutes", w.getDurationMinutes());
            row.put("intensityLevel", w.getIntensityLevel());
            row.put("notes", w.getNotes());
            rows.add(row);
        }
    }

    model.addAttribute("rows", rows);
    return "workouts";
    }

    @GetMapping("/workouts/new")
    public String newWorkoutForm(Model model) {
    model.addAttribute("athletes", athleteService.getAthletes());
    return "new_workout";   

    }

    @PostMapping("/workouts")
    public String createWorkout(
        @RequestParam String athleteName,
        @RequestParam String date,
        @RequestParam String type,
        @RequestParam int durationMinutes,
        @RequestParam int intensityLevel,
        @RequestParam(required = false, defaultValue = "") String notes,
        RedirectAttributes redirectAttributes
    ) {
    boolean ok = athleteService.logWorkout(athleteName, date, type, durationMinutes, intensityLevel, notes);
    if (!ok) {
        redirectAttributes.addFlashAttribute("error", "Athlete not found. Please select a valid athlete.");
        return "redirect:/workouts/new";
    }
    athleteService.saveAllData();
    return "redirect:/workouts";
    }
}