package app;

import java.util.ArrayList;
import java.util.Scanner;
import model.Athlete;
import model.SoccerSession;
import model.Workout;
import storage.CsvStorage;
import service.AthleteService;


public class App {


    private static int readInt(Scanner scnr, String prompt) {
        int value;
        while (true) {
            System.out.print(prompt);
            try {
                value = Integer.parseInt(scnr.nextLine());
                break;
            } catch (NumberFormatException e) {
                System.out.println("Invalid input. Please enter a valid integer.");
            }
        }
        return value;
    }

    private static int readIntInRange(Scanner scnr, String prompt, int min, int max) {
        int value;
        while (true) {
            value = readInt(scnr, prompt);
            if (value >= min && value <= max) {
                break;
            } else {
                System.out.println("Input must be between " + min + " and " + max + ".");
            }
        }
        return value;
    }   

    private static double readDouble(Scanner scnr, String prompt) {
        double value;
        while(true) {
            System.out.print(prompt);
            try {
                value = Double.parseDouble(scnr.nextLine());
                break;
            } catch (NumberFormatException e) {
                System.out.println("Invalid input. Please enter a valid number.");
            }   
        }
        return value;
    }

    private static double readDoubleInRange(Scanner scnr, String prompt, double min, double max) {
        double value;
        while (true) {
            value = readDouble(scnr, prompt);
            if (value >= min && value <= max) {
                break;
            } else {
                System.out.println("Input must be between " + min + " and " + max + ".");
            }
        }
        return value;
    }

    public static void main(String[] args) {
        System.out.println("Welcome to Athlete Performance System");
        Scanner scnr = new Scanner(System.in);
        AthleteService service = new AthleteService("../data/athletes.csv", "../data/soccer_sessions.csv", "../data/workouts.csv");
        ArrayList<Athlete> athletes = service.getAthletes();

        boolean cont = true;
        int num = 0;

        while (cont) {
            System.out.println("Choose an option: ");
            System.out.println("1. Add Athlete");
            System.out.println("2. Log soccer session");
            System.out.println("3. Log workout");
            System.out.println("4. View Athletes");
            System.out.println("5. View soccer sessions");
            System.out.println("6. View athlete summary");
            System.out.println("7. View workouts");
            System.out.println("8. Exit");

            num = readIntInRange(scnr, "Select option (1-8): ", 1, 8);
            

            switch (num) {
                case 1:
                    System.out.println("Add Athlete");
                    System.out.print("Full name: ");
                    String name = scnr.nextLine().trim();
                    int age = readIntInRange(scnr, "Age: ", 1, 100);
                    System.out.print("Sport: ");
                    String sport = scnr.nextLine().trim();
                    System.out.print("Position: ");
                    String position = scnr.nextLine().trim();
                    double weight = readDoubleInRange(scnr, "Weight: ", 1.0, 500.0);
                    double height = readDoubleInRange(scnr, "Height: ", 0.5, 2.5);
                    System.out.print("Country: ");
                    String country = scnr.nextLine().trim();
                    boolean added = service.addAthlete(name, age, sport, position, weight, height, country);

                    if (!added) {
                        System.out.println("Athlete with this name already exists.");
                    } else {
                        System.out.println("Athlete added successfully.");
                    }

                    break;
                case 2:
                    System.out.println("Log soccer session");
                    System.out.print("Athlete name: ");
                    String athleteName = scnr.nextLine().trim();
                    System.out.print("Date (YYYY-MM-DD): ");
                    String date = scnr.nextLine().trim();
                    int durationMinutes = readIntInRange(scnr, "Duration (minutes): ", 1, 300);
                    
                    int intensityLevel = readIntInRange(scnr, "Intensity level (1-10): ", 1, 10);
                    
                    System.out.print("Notes: ");
                    String notes = scnr.nextLine().trim();
                    boolean logged = service.logSoccerSession(athleteName, date, durationMinutes, intensityLevel, notes);
                    if (logged) {
                        System.out.println("Soccer session logged successfully.");
                    } else {
                        System.out.println("Athlete not found.");
                    }
                    break;

                case 3:
                    System.out.println("Log workout");
                    System.out.print("Athlete name: ");
                    String athNameW = scnr.nextLine().trim();
                    System.out.print("Date (YYYY-MM-DD): ");
                    String dateW = scnr.nextLine().trim();
                    System.out.print("Workout type: ");
                    String type = scnr.nextLine().trim();
                    int durationMinutesW = readIntInRange(scnr, "Duration (minutes): ", 1, 300);
                    
                    int intensityLevelW = readIntInRange(scnr, "Intensity level (1-10): ", 1, 10);
                    
                    System.out.print("Notes: ");
                    String notesW = scnr.nextLine().trim();
                    boolean loggedW = service.logWorkout(athNameW, dateW, type, durationMinutesW, intensityLevelW, notesW);
                    if (loggedW) {
                        System.out.println("Workout logged successfully.");
                    } else {
                        System.out.println("Athlete not found.");
                    }
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
                    System.out.println("View athlete summary");
                    System.out.print("Athlete name: ");
                    String athName = scnr.nextLine().trim();
                    Athlete athFound = service.findAthleteByName(athName);
                    if (athFound == null) {
                        System.out.println("Athlete not found.");
                        break;
                    }
                    System.out.println("Athlete Summary for " + athFound.getName() + ":");
                    System.out.println("Age: " + athFound.getAge());
                    System.out.println("Sport: " + athFound.getSport());
                    System.out.println("Position: " + athFound.getPosition());
                    System.out.println("Weight: " + athFound.getWeight());
                    System.out.println("Height: " + athFound.getHeight());
                    System.out.println("Country: " + athFound.getCountry());
                    System.out.println("Total Soccer Sessions: " + athFound.getSoccerSessionsCount());
                    System.out.println("Total Soccer Minutes: " + athFound.getTotalSoccerMinutes());
                    System.out.println("Average Soccer Intensity: " + athFound.getAverageSoccerIntensity());
                    System.out.println("Total Workouts: " + athFound.getWorkoutCount());
                    System.out.println("Total Workout Minutes: " + athFound.getTotalWorkoutMinutes());
                    System.out.println("Average Workout Intensity: " + athFound.getAverageWorkoutIntensity());
                    break;

                case 7:
                    System.out.println("View workouts");
                    if(athletes.isEmpty()) {
                        System.out.println("No athletes available.");
                        break;
                    }
                    for (Athlete athleteItem : athletes) {
                        ArrayList<Workout> workouts = athleteItem.getWorkouts();

                        if (workouts.isEmpty()) {
                            System.out.println(athleteItem.getName() + ": No workouts.");
                            continue;
                        }

                        for (Workout workoutInfo : workouts) {
                            System.out.println(
                                "Athlete: " + athleteItem.getName() +
                                ", Date: " + workoutInfo.getDate() +
                                ", Type: " + workoutInfo.getType() +
                                ", Duration: " + workoutInfo.getDurationMinutes() + " minutes" +
                                ", Intensity: " + workoutInfo.getIntensityLevel() +
                                ", Notes: " + workoutInfo.getNotes()
                            );
                        }
                    }
                    break;
                case 8:
                    System.out.println("Exiting...");
                    service.saveAllData();
                    
                    cont = false;
                    break;
                default:
                    System.out.println("Invalid option.");
            }
        }
        scnr.close();
    }
}
