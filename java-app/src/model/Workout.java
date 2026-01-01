package model;

public class Workout {
    private String date;
    private String type;
    private int durationMinutes;
    private int intensityLevel;
    private String notes;

    public Workout(String date, String type, int durationMinutes, int intensityLevel, String notes) {
        this.date = date;
        this.type = type;
        this.durationMinutes = durationMinutes;
        this.intensityLevel = intensityLevel;
        this.notes = notes;
    }

    // Getters
    public String getDate() {
        return date;
    }

    public String getType() {
        return type;
    }

    public int getDurationMinutes() {
        return durationMinutes;
    }

    public int getIntensityLevel() {
        return intensityLevel;
    }

    public String getNotes() {
        return notes;
    }

    public String toString() {
        return "Date: " + date + ", Type: " + type + ", Duration: " + durationMinutes + " minutes, Intensity: " + intensityLevel + ", Notes: " + notes;
    }
}