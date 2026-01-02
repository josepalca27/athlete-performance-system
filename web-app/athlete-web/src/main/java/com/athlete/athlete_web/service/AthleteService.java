package com.athlete.athlete_web.service;
import com.athlete.athlete_web.model.Athlete;
import com.athlete.athlete_web.storage.CsvStorage;
import java.util.ArrayList;
import com.athlete.athlete_web.model.SoccerSession;
import com.athlete.athlete_web.model.Workout;


public class AthleteService {
    // Business logic related to Athlete will go here
    private ArrayList<Athlete> athletes;
    private String athletesPath;
    private String sessionsPath;
    private String workoutsPath;

    public AthleteService(String athletesPath, String sessionsPath, String workoutsPath) {
        this.athletesPath = athletesPath;
        this.sessionsPath = sessionsPath;
        this.workoutsPath = workoutsPath;
        this.athletes = CsvStorage.loadAthletes(athletesPath);
        CsvStorage.loadSoccerSessions(sessionsPath, this.athletes);
        CsvStorage.loadWorkouts(workoutsPath, this.athletes);
    }

    public ArrayList<Athlete> getAthletes() {
        return athletes;
    }

    public void saveAllData() {
        CsvStorage.saveAthletes(athletesPath, athletes);
        CsvStorage.saveSoccerSessions(sessionsPath, athletes);
        CsvStorage.saveWorkouts(workoutsPath, athletes);
    }

    public Athlete findAthleteByName(String name) {
        if (name == null) 
            return null;
        

        name = name.trim();

        for (Athlete athlete : athletes) {
            if (athlete.getName().equalsIgnoreCase(name)) {
                return athlete;
            }
        }
        return null;
    }

    public boolean addAthlete(String name, int age, String sport, String position, double weight, double height, String country) {
        if (name.trim().isEmpty() || findAthleteByName(name) != null) {
            return false; // Athlete with this name already exists
        }
        name = name.trim();
        sport = sport.trim();
        position = position.trim();
        country = country.trim();

        Athlete newAthlete = new Athlete(name, age, sport, position, weight, height, country);
        athletes.add(newAthlete);
        return true;
    }

    public boolean logSoccerSession(String athleteName, String date, int durationMinutes, int intensityLevel, String notes) {
        Athlete athlete = findAthleteByName(athleteName);
        if (athlete == null) {
            return false; // Athlete not found
        }

        SoccerSession session = new SoccerSession(date, durationMinutes, intensityLevel, notes);
        athlete.addSoccerSession(session);
        return true;
    }

    public boolean logWorkout(String athleteName, String date, String type, int durationMinutes, int intensityLevel, String notes) {
        Athlete athlete = findAthleteByName(athleteName);
        if (athlete == null) {
            return false; // Athlete not found
        }

        Workout workout = new Workout(date, type, durationMinutes, intensityLevel, notes);
        athlete.addWorkout(workout);
        return true;
    }





}