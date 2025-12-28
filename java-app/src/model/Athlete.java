package model;
import java.util.ArrayList;
import model.SoccerSession;

public class Athlete {
    private String name;
    private int age;
    private String sport;
    private String position;
    private double weight;
    private double height;
    private String country;
    private ArrayList<SoccerSession> soccerSessions;

    public Athlete(String name, int age, String sport, String position, double weight, double height, String country) {
        this.name = name;
        this.age = age;
        this.sport = sport;
        this.position = position;
        this.weight = weight;
        this.height = height;
        this.country = country;
        this.soccerSessions = new ArrayList<>();
    }

    // Getters and Setters
    public String getName() {
        return name;
    }

    public int getAge() {
        return age;
    }

    public String getSport() {
        return sport;
    }

    public String getPosition() {
        return position;
    }

    public double getWeight() {
        return weight;
    }

    public double getHeight() {
        return height;
    }

    public String getCountry() {
        return country;
    }

    public void setName(String name) {
        this.name = name;
    }

    public void setAge(int age) {
        this.age = age;
    }

    public void setSport(String sport) {
        this.sport = sport;
    }

    public void setPosition(String position) {
        this.position = position;
    }

    public void setWeight(double weight) {
        this.weight = weight;
    }

    public void setHeight(double height) {
        this.height = height;
    }

    public void setCountry(String country) {
        this.country = country;
    }

    public void addSoccerSession(SoccerSession session) {
        this.soccerSessions.add(session);
    }

    public ArrayList<SoccerSession> getSoccerSessions() {
        return soccerSessions;
    }



}