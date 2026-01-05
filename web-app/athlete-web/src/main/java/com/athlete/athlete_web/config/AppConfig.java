package com.athlete.athlete_web.config;

import com.athlete.athlete_web.service.AthleteService;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;

import java.nio.file.Path;
import java.nio.file.Paths;

@Configuration
public class AppConfig {

    @Bean
    public AthleteService athleteService() {
        Path dataDir = Paths.get("..", "..", "data").normalize();

        String athletesCsv = dataDir.resolve("athletes.csv").toString();
        String sessionsCsv = dataDir.resolve("soccer_sessions.csv").toString();
        String workoutsCsv = dataDir.resolve("workouts.csv").toString();

        return new AthleteService(athletesCsv, sessionsCsv, workoutsCsv);
    }
}
