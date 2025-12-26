package app;

import java.util.ArrayList;
import java.util.Scanner;
import model.Athlete;

public class App {
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
            System.out.println("5. Exit");

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
                    System.out.println("Exiting...");
                    cont = false;
                    break;
                default:
                    System.out.println("Invalid option.");
            }
        }
        scnr.close();
    }
}
