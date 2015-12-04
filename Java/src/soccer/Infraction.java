package com.fourpointzeroteam.nathan.fantasyfutbol.Futbol.soccer;

/**
 * Infraction class 
 */
public class Infraction {
	
	/**
	 * If 0 -> red card,
	 * If 1 -> yellow card
	 * If 2 -> penalty kick
	 */
	private int penalty;
	
	/**
	 * Infraction constructor
	 * @param penalty 0 -> red card, 1 -> yellow card, 2 -> penalty kick
	 */
	public Infraction(int penalty) {
		this.penalty = penalty;
	}

	/**
	 * Getter method that returns boolean penalty
	 * @return penalty 0 -> red card, 1 -> yellow card, 2 -> penalty kick
	 */
	public int getPenalty() {
		return this.penalty;
	}

	/**
	 * Setter method that sets boolean penalty to true or false
	 * @param penalty 0 -> red card, 1 -> yellow card, 2 -> penalty kick
	 */
	public void setPenalty(int penalty) {
		this.penalty = penalty;
	}
	
	
	

}
