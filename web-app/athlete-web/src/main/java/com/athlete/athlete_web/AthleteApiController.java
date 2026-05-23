package com.athlete.athlete_web;

import com.athlete.athlete_web.model.Athlete;
import com.athlete.athlete_web.service.AthleteService;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.http.ResponseEntity;
import java.util.List;
import java.util.ArrayList;


@RestController
@RequestMapping("/api")
public class AthleteApiController {

    private final AthleteService athleteService;

    public AthleteApiController(AthleteService athleteService) {
        this.athleteService = athleteService;
    }


    @GetMapping("/athletes")
    public List<Athlete> getAllAthletes() {
        return athleteService.getAthletes();
    }

    @GetMapping("/athletes/find")
    public ResponseEntity<Athlete> findAthletes(@RequestParam String name) {
        Athlete athlete = athleteService.findAthleteByName(name);
        if (athlete == null) {
            return ResponseEntity.notFound().build();
        }
        return ResponseEntity.ok(athlete);
    }

    @GetMapping("athletes/{name}")
    public ResponseEntity<Athlete> getAthleteByPath(@PathVariable String name) {
        Athlete athlete = athleteService.findAthleteByName(name);
        if (athlete == null) {
            return ResponseEntity.notFound().build();
        }
        return ResponseEntity.ok(athlete);
    }

}