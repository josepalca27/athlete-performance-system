package com.athlete.athlete_web.view;

public class WorkoutRow {
    private final String athleteName;
    private final String date;
    private final String type;
    private final int durationMinutes;
    private final int intensityLevel;
    private final String notes;

    public WorkoutRow(String athleteName, String date, String type, int durationMinutes, int intensityLevel, String notes) {
        this.athleteName = athleteName;
        this.date = date;
        this.type = type;
        this.durationMinutes = durationMinutes;
        this.intensityLevel = intensityLevel;
        this.notes = notes;
    }

    public String getAthleteName() {
        return athleteName;
    }

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
}
