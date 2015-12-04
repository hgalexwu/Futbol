package com.fourpointzeroteam.nathan.fantasyfutbol.Futbol.soccer;

public class Shot {
	
	/**
	 * If true, shot resulted in a goal. Else if false, shot wasn't a goal.
	 */
	private boolean isGoal;
	
	/**
	 * Shot constructor
	 * @param isGoal True -> Goal. False -> Not a goal
	 */
	public Shot(boolean isGoal) {
		this.setGoal(isGoal);
	}

	/**
	 * Getter method that returns if shot resulted in goal
	 * @return True -> Goal. False -> Not a goal
	 */
	public boolean getGoal() {
		return isGoal;
	}

	/**
	 * Setter method that sets if shot resulted in goal
	 * @param isGoal True -> Goal. False -> Not a goal
	 */
	public void setGoal(boolean isGoal) {
		this.isGoal = isGoal;
	}
}
