package com.athlete.athlete_web;

import com.athlete.athlete_web.model.Athlete;
import com.athlete.athlete_web.service.AthleteService;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.PathVariable;
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
    public Athlete findAthletes(@RequestParam String name) {
        return athleteService.findAthleteByName(name);

    }

    @GetMapping("athletes/{name}")
    public Athlete getAthleteByName(@PathVariable String name) {
        return athleteService.findAthleteByName(name);
    }

}