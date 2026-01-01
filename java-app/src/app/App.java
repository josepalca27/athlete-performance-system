package app;

import java.util.ArrayList;
import java.util.Scanner;
import model.Athlete;
import model.SoccerSession;
import model.Workout;
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
        ArrayList<Athlete> athletes = CsvStorage.loadAthletes("../data/athletes.csv");
        CsvStorage.loadSoccerSessions("../data/soccer_sessions.csv", athletes);
        CsvStorage.loadWorkouts("../data/workouts.csv", athletes);

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
                    if(findAthlete(athletes, name) != null) {
                        System.out.println("Athlete with this name already exists.");
                        break;
                    }
                    
                    int age = readIntInRange(scnr, "Age: ", 1, 100);
                     
                    System.out.print("Sport: ");
                    String sport = scnr.nextLine().trim();
                    System.out.print("Position: ");
                    String position = scnr.nextLine();
                    
                    double weight = readDoubleInRange(scnr, "Weight: ", 1.0, 500.0);
                   
                    
                    double height = readDoubleInRange(scnr, "Height: ", 0.5, 2.5);
                    
                    System.out.print("Country: ");
                    String country = scnr.nextLine();
                    Athlete athlete = new Athlete(name, age, sport, position, weight, height, country);
                    athletes.add(athlete);
                    System.out.println("Athlete added successfully.");

                    break;
                case 2:
                    System.out.println("Log soccer session");
                    System.out.print("Athlete name: ");
                    String athleteName = scnr.nextLine().trim();
                    Athlete found = findAthlete(athletes, athleteName);
                    if (found == null) {
                        System.out.println("Athlete not found.");
                        break;
                    }
                    System.out.print("Date (YYYY-MM-DD): ");
                    String date = scnr.nextLine();
                    int durationMinutes = readIntInRange(scnr, "Duration (minutes): ", 1, 300);
                    
                    int intensityLevel = readIntInRange(scnr, "Intensity level (1-10): ", 1, 10);
                    
                    System.out.print("Notes: ");
                    String notes = scnr.nextLine();
                    SoccerSession session = new SoccerSession(date, durationMinutes, intensityLevel, notes);
                    found.addSoccerSession(session);
                    System.out.println("Soccer session logged successfully.");
                    break;
                case 3:
                    System.out.println("Log workout");
                    System.out.print("Athlete name: ");
                    String athNameW = scnr.nextLine().trim();
                    Athlete athFoundW = findAthlete(athletes, athNameW);
                    if (athFoundW == null) {
                        System.out.println("Athlete not found.");
                        break;
                    }
                    System.out.println("Date (YYYY-MM-DD): ");
                    String dateW = scnr.nextLine();
                    System.out.print("Workout type: ");
                    String type = scnr.nextLine();
                    int durationMinutesW = readIntInRange(scnr, "Duration (minutes): ", 1, 300);
                    
                    int intensityLevelW = readIntInRange(scnr, "Intensity level (1-10): ", 1, 10);
                    
                    System.out.println("Notes: ");
                    String notesW = scnr.nextLine();
                    Workout workout = new Workout(dateW, type, durationMinutesW, intensityLevelW, notesW);

                    athFoundW.addWorkout(workout);
                    System.out.println("Workout logged successfully.");
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
                    Athlete athFound = findAthlete(athletes, athName);
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
                    CsvStorage.saveAthletes("../data/athletes.csv", athletes);
                    CsvStorage.saveSoccerSessions("../data/soccer_sessions.csv", athletes);
                    CsvStorage.saveWorkouts("../data/workouts.csv", athletes);
                    
                    cont = false;
                    break;
                default:
                    System.out.println("Invalid option.");
            }
        }
        scnr.close();
    }
}
