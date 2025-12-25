package app;

import java.util.Scanner;

public class App {
    public static void main(String[] args) {
        System.out.println("Welcome to Athlete Performance System");
        Scanner scnr = new Scanner(System.in);

        boolean cont = true;
        int num = 0;

        while (cont) {
            System.out.println("Choose an option: ");
            System.out.println("1. Add Athlete");
            System.out.println("2. Log soccer session");
            System.out.println("3. Log workout");
            System.out.println("4. Exit");

            num = scnr.nextInt();

            switch (num) {
                case 1:
                    System.out.println("Add Athlete");
                    break;
                case 2:
                    System.out.println("Log soccer session");
                    break;
                case 3:
                    System.out.println("Log workout");
                    break;
                case 4:
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
