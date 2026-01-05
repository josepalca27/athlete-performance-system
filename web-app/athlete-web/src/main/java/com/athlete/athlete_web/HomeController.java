package com.athlete.athlete_web;

import com.athlete.athlete_web.model.Athlete;
import com.athlete.athlete_web.model.SoccerSession;
import com.athlete.athlete_web.model.Workout;
import com.athlete.athlete_web.service.AthleteService;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.servlet.mvc.support.RedirectAttributes;
import com.athlete.athlete_web.view.SoccerSessionRow;
import com.athlete.athlete_web.view.WorkoutRow;


import java.util.ArrayList;
import java.util.List;


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
        List<SoccerSessionRow> rows = new ArrayList<>();

        for (var a : athleteService.getAthletes()) {
            for (SoccerSession s : a.getSoccerSessions()) {
                rows.add(new SoccerSessionRow(
                        a.getName(),
                        s.getDate(),
                        s.getDurationMinutes(),
                        s.getIntensityLevel(),
                        s.getNotes()
                ));
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
        redirectAttributes.addFlashAttribute("error", "Invalid input. Check date format (YYYY-MM-DD), duration, and intensity.");
        return "redirect:/sessions/new";
    }

    athleteService.saveAllData();
    return "redirect:/sessions";
    }

    @GetMapping("/workouts")
    public String workouts(Model model) {
        List<WorkoutRow> rows = new ArrayList<>();

        for (var a : athleteService.getAthletes()) {
            for (Workout w : a.getWorkouts()) {
                rows.add(new WorkoutRow(
                        a.getName(),
                        w.getDate(),
                        w.getType(),
                        w.getDurationMinutes(),
                        w.getIntensityLevel(),
                        w.getNotes()
                ));
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
        redirectAttributes.addFlashAttribute("error", "Invalid input. Check date format (YYYY-MM-DD), duration, and intensity.");
        return "redirect:/workouts/new";
    }
    athleteService.saveAllData();
    return "redirect:/workouts";

    }

    @GetMapping("/athletes/detail")
    public String athleteDetail(@RequestParam String name, Model model, RedirectAttributes redirectAttributes) {
        Athlete athlete = athleteService.findAthleteByName(name);

        if (athlete == null) {
            redirectAttributes.addFlashAttribute("error", "Athlete not found.");
            return "redirect:/athletes";
        }

        model.addAttribute("athlete", athlete);
        model.addAttribute("sessions", athlete.getSoccerSessions());
        model.addAttribute("workouts", athlete.getWorkouts());

        return "athlete_detail";
    }

    @GetMapping("/about")
    public String about() {
        return "about";
    }

}