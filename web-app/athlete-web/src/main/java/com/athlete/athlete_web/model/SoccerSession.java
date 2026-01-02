package com.athlete.athlete_web.model;

public class SoccerSession {

	//declare fields here when you decide parameters, for example:
	private String date;
	private int durationMinutes;
    private int intensityLevel;
	private String notes;

	/**
	 * Constructor skeleton. Replace the commented example parameters
	 * with the actual parameters you want to use.
	 */
	public SoccerSession(String date, int durationMinutes, int intensityLevel, String notes) {
		this.date = date;
		this.durationMinutes = durationMinutes;
        this.intensityLevel = intensityLevel;
		this.notes = notes;
	}

	// Getters will be implemented after you confirm the constructor parameters.
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

