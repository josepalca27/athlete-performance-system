package com.athlete.athlete_web.storage;
import com.athlete.athlete_web.model.Workout;
import com.athlete.athlete_web.model.Athlete;
import com.athlete.athlete_web.model.SoccerSession;

import java.util.ArrayList;
import java.io.FileWriter;
import java.io.PrintWriter;
import java.io.IOException;
import java.io.File;
import java.io.BufferedReader;
import java.io.FileReader;

public class CsvStorage {

    public static ArrayList<Athlete> loadAthletes(String path) {
        ArrayList<Athlete> athletes = new ArrayList<>();

        if(!new File(path).exists()) {
            return athletes;
        }

        try (BufferedReader br = new BufferedReader(new FileReader(path))) {

           br.readLine(); // skip header
           String line;     
           while ((line = br.readLine()) != null) {
                String[] parts = line.split(",");
                String name = parts[0];
                int age = Integer.parseInt(parts[1]);
                String sport = parts[2];
                String position = parts[3];
                double weight = Double.parseDouble(parts[4]);
                double height = Double.parseDouble(parts[5]);
                String country = parts[6];
                // create Athlete
                Athlete athlete = new Athlete(name, age, sport, position, weight, height, country);
                // add to list
                athletes.add(athlete);

            }
        } catch (IOException e) {
        System.out.println("Error loading athletes: " + e.getMessage());
    }
        return athletes;
    }

    public static void saveAthletes(String path, ArrayList<Athlete> athletes) {
        // Code to save athletes to CSV will go here
        ensureParentDirExists(path);
        try (PrintWriter out = new PrintWriter(new FileWriter(path))) {

            out.println("name,age,sport,position,weight,height,country");

            for (Athlete athlete : athletes) {
                out.println(
                    athlete.getName() + "," +
                    athlete.getAge() + "," +
                    athlete.getSport() + "," +
                    athlete.getPosition() + "," +
                    athlete.getWeight() + "," +
                    athlete.getHeight() + "," +
                    athlete.getCountry()
                );
            }
        } catch (IOException e) {
            System.out.println("Error saving athletes: " + e.getMessage());
        }
    }

    public static void saveSoccerSessions(String path, ArrayList<Athlete> athletes) {
        // Code to load soccer sessions from CSV will go here
        ensureParentDirExists(path);
        try (PrintWriter out = new PrintWriter(new FileWriter(path))) {

            out.println("athleteName,date,durationMinutes,intensityLevel,notes");

            for (Athlete athlete : athletes) {
                for (SoccerSession session : athlete.getSoccerSessions()) {
                    String notes = session.getNotes();
                    String safeNotes = notes.replace("\"", "\"\""); // escape quotes
                    out.println(
                        athlete.getName() + "," +
                        session.getDate() + "," +
                        session.getDurationMinutes() + "," +
                        session.getIntensityLevel() + "," +
                        "\"" + safeNotes + "\""
                    );
                }
            }

        } catch (IOException e) {
            System.out.println("Error saving soccer sessions: " + e.getMessage());
        }

    }

    public static void loadSoccerSessions(String path, ArrayList<Athlete> athletes) {
        // Code to load soccer sessions to CSV will go here
        if (!new File(path).exists()) return;

        try (BufferedReader br = new BufferedReader(new FileReader(path))) {

           br.readLine(); // skip header
           String line;     
           while ((line = br.readLine()) != null) {
                if (line.trim().isEmpty()) continue;
                String[] parts = line.split(",", 5);
                if (parts.length < 5) continue;
                String athleteName = parts[0];
                String date = parts[1];
                int durationMinutes = Integer.parseInt(parts[2]);
                int intensityLevel = Integer.parseInt(parts[3]);
                String notes = parts[4].replaceAll("^\"|\"$", ""); // remove surrounding quotes
                notes = notes.replace("\"\"", "\""); // unescape quotes

                // find athlete
                Athlete athlete = null;
                for (Athlete a : athletes) {
                    if (a.getName().equalsIgnoreCase(athleteName)) {
                        athlete = a;
                        break;
                    }
                }
                if (athlete != null) {
                    // create SoccerSession
                    SoccerSession session = new SoccerSession(date, durationMinutes, intensityLevel, notes);
                    // add to athlete
                    athlete.addSoccerSession(session);
                }
            }
        } catch (IOException e) {
        System.out.println("Error loading soccer sessions: " + e.getMessage());
        }
    }

    public static void saveWorkouts(String path, ArrayList<Athlete> athletes) {
        // Code to load workouts from CSV will go here
        ensureParentDirExists(path);
        try (PrintWriter out = new PrintWriter(new FileWriter(path))) {

            out.println("athleteName,date,type,durationMinutes,intensityLevel,notes");

            for (Athlete athlete : athletes) {
                for (Workout workout : athlete.getWorkouts()) {
                    String notes = workout.getNotes();
                    String safeNotes = notes.replace("\"", "\"\""); // escape quotes
                    out.println(
                        athlete.getName() + "," +
                        workout.getDate() + "," +
                        workout.getType() + "," +
                        workout.getDurationMinutes() + "," +
                        workout.getIntensityLevel() + "," +
                        "\"" + safeNotes + "\""
                    );
                }
            }

        } catch (IOException e) {
            System.out.println("Error saving workouts: " + e.getMessage());
        }   
    }

    public static void loadWorkouts(String path, ArrayList<Athlete> athletes) {
        // Code to load workouts to CSV will go here
        if (!new File(path).exists()) return;
        try (BufferedReader br = new BufferedReader(new FileReader(path))) {    
            
           br.readLine(); // skip header
           String line;     
           while ((line = br.readLine()) != null) {
                if (line.trim().isEmpty()) continue;
                String[] parts = line.split(",", 6);
                if (parts.length < 6) continue;
                String athleteName = parts[0];
                String date = parts[1];
                String type = parts[2];
                int durationMinutes = Integer.parseInt(parts[3]);
                int intensityLevel = Integer.parseInt(parts[4]);
                String notes = parts[5].replaceAll("^\"|\"$", ""); // remove surrounding quotes
                notes = notes.replace("\"\"", "\""); // unescape quotes

                // find athlete
                Athlete athlete = null;
                for (Athlete a : athletes) {
                    if (a.getName().equalsIgnoreCase(athleteName)) {
                        athlete = a;
                        break;
                    }
                }
                if (athlete != null) {
                    // create Workout
                    Workout workout = new Workout(date, type, durationMinutes, intensityLevel, notes);
                    // add to athlete
                    athlete.addWorkout(workout);
                }
            }
        } catch (IOException e) {
            System.out.println("Error loading workouts: " + e.getMessage());
        }
    }

    private static void ensureParentDirExists(String path) {
        File file = new File(path);
        File parentDir = file.getParentFile();
        if (parentDir != null && !parentDir.exists()) {
            parentDir.mkdirs();
        }
    }
}