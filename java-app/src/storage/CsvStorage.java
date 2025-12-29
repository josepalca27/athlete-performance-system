package storage;
import model.Athlete;
import model.SoccerSession;

import java.util.ArrayList;
import java.io.FileWriter;
import java.io.PrintWriter;
import java.io.IOException;

public class CsvStorage {

    public static ArrayList<Athlete> loadAthletes(String path) {
        return new ArrayList<>();
    }

    public static void saveAthletes(String path, ArrayList<Athlete> athletes) {

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

    public static void loadSoccerSessions(String path, ArrayList<Athlete> athletes) {
        // Code to load soccer sessions from CSV will go here
        try (PrintWriter out = new PrintWriter(new FileWriter(path))) {

            out.println("athleteName,date,durationMinutes,intensityLevel,notes");

            for (Athlete athlete : athletes) {
                for (SoccerSession session : athlete.getSoccerSessions()) {
                    out.println(
                        athlete.getName() + "," +
                        session.getDate() + "," +
                        session.getDurationMinutes() + "," +
                        session.getIntensityLevel() + "," +
                        "\"" + session.getNotes() + "\""
                    );
                }
            }

        } catch (IOException e) {
            System.out.println("Error saving soccer sessions: " + e.getMessage());
        }

    }

    public static void saveSoccerSessions(String path, ArrayList<Athlete> athletes) {
        // Code to save soccer sessions to CSV will go here
    }

}