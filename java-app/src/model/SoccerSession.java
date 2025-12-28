package model;

public class SoccerSession {

	// TODO: declare fields here when you decide parameters, for example:
	private String athleteName;
	private String date;
	private int durationMinutes;
    private int intensityLevel;
	private String notes;

	/**
	 * Constructor skeleton. Replace the commented example parameters
	 * with the actual parameters you want to use.
	 */
	public SoccerSession(String athleteName, String date, int durationMinutes, int intensityLevel, String notes) {
		this.athleteName = athleteName;
		this.date = date;
		this.durationMinutes = durationMinutes;
        this.intensityLevel = intensityLevel;
		this.notes = notes;
	}

	// Getters will be implemented after you confirm the constructor parameters.
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

