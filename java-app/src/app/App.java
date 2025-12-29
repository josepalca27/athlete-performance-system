package app;

import java.util.ArrayList;
import java.util.Scanner;
import model.Athlete;
import model.SoccerSession;
import storage.CsvStorage;


public class App {
    private static Athlete findAthlete(ArrayList<Athlete> athletes, String name) {
    for (Athlete athlete : athletes) {
        if (athlete.getName().equalsIgnoreCase(name)) {
            return athlete;
        }
    }
    return null;
}
    public static void main(String[] args) {
        System.out.println("Welcome to Athlete Performance System");
        Scanner scnr = new Scanner(System.in);
        ArrayList<Athlete> athletes = new ArrayList<>();

        boolean cont = true;
        int num = 0;

        while (cont) {
            System.out.println("Choose an option: ");
            System.out.println("1. Add Athlete");
            System.out.println("2. Log soccer session");
            System.out.println("3. Log workout");
            System.out.println("4. View Athletes");
            System.out.println("5. View soccer sessions");
            System.out.println("6. Exit");

            num = scnr.nextInt();
            scnr.nextLine(); // consume newline

            switch (num) {
                case 1:
                    System.out.println("Add Athlete");
                    System.out.println("Full name: ");
                    String name = scnr.nextLine();
                    System.out.println("Age: ");
                    int age = scnr.nextInt();
                    scnr.nextLine(); 
                    System.out.println("Sport: ");
                    String sport = scnr.nextLine();
                    System.out.println("Position: ");
                    String position = scnr.nextLine();
                    System.out.println("Weight: ");
                    double weight = scnr.nextDouble();
                    System.out.println("Height: ");
                    double height = scnr.nextDouble();
                    scnr.nextLine();
                    System.out.println("Country: ");
                    String country = scnr.nextLine();
                    Athlete athlete = new Athlete(name, age, sport, position, weight, height, country);
                    athletes.add(athlete);
                    System.out.println("Athlete added successfully.");

                    break;
                case 2:
                    System.out.println("Log soccer session");
                    System.out.println("Athlete name: ");
                    String athleteName = scnr.nextLine();
                    Athlete found = findAthlete(athletes, athleteName);
                    if (found == null) {
                        System.out.println("Athlete not found.");
                        break;
                    }
                    System.out.println("Date (YYYY-MM-DD): ");
                    String date = scnr.nextLine();
                    System.out.println("Duration (minutes): ");
                    int durationMinutes = scnr.nextInt();
                    scnr.nextLine();
                    System.out.println("Intensity level (1-10): ");
                    int intensityLevel = scnr.nextInt();
                    scnr.nextLine();
                    System.out.println("Notes: ");
                    String notes = scnr.nextLine();
                    SoccerSession session = new SoccerSession(date, durationMinutes, intensityLevel, notes);
                    found.addSoccerSession(session);
                    System.out.println("Soccer session logged successfully.");
                    break;
                case 3:
                    System.out.println("Log workout");
                    break;
                case 4:
                    System.out.println("View Athletes");
                    if(athletes.isEmpty()) {
                        System.out.println("No athletes available.");
                        break;
                    }
                    for (Athlete athleteItem : athletes) {
                        System.out.println(athleteItem.getName() + " (" + athleteItem.getAge() + ") - " + athleteItem.getSport() + " (" + athleteItem.getPosition() + ") - " + athleteItem.getCountry());
                    }
                    break;
                case 5:
                    System.out.println("View soccer sessions");
                    if(athletes.isEmpty()) {
                        System.out.println("No athletes available.");
                        break;
                    }
                    for (Athlete athleteItem : athletes) {
                        ArrayList<SoccerSession> sessions = athleteItem.getSoccerSessions();

                        if (sessions.isEmpty()) {
                            System.out.println(athleteItem.getName() + ": No soccer sessions.");
                            continue;
                        }

                        for (SoccerSession sessionInfo : sessions) {
                            System.out.println(
                                "Athlete: " + athleteItem.getName() +
                                ", Date: " + sessionInfo.getDate() +
                                ", Duration: " + sessionInfo.getDurationMinutes() + " minutes" +
                                ", Intensity: " + sessionInfo.getIntensityLevel() +
                                ", Notes: " + sessionInfo.getNotes()
                            );
                        }
                    }
                    break;
                case 6:
                    System.out.println("Exiting...");
                    CsvStorage.saveAthletes("data/athletes.csv", athletes);
                    CsvStorage.loadSoccerSessions("data/soccer_sessions.csv", athletes);
                    cont = false;
                    break;
                default:
                    System.out.println("Invalid option.");
            }
        }
        scnr.close();
    }
}
