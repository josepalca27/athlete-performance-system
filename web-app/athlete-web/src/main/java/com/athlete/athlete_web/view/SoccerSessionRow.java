package com.athlete.athlete_web.view;

public class SoccerSessionRow {
    private final String athleteName;
    private final String date;
    private final int durationMinutes;
    private final int intensityLevel;
    private final String notes;

    public SoccerSessionRow(String athleteName, String date, int durationMinutes, int intensityLevel, String notes) {
        this.athleteName = athleteName;
        this.date = date;
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
