package soccer;

/**
 * Infraction class 
 */
public class Infraction {
	
	/**
	 * If true, penalty was a red card, otherwise it was a yellow card
	 */
	private boolean penalty;
	
	/**
	 * Infraction constructor
	 * @param penalty True -> Red Card. False -> Yellow Card
	 */
	public Infraction(boolean penalty) {
		this.penalty = penalty;
	}

	/**
	 * Getter method that returns boolean penalty
	 * @return penalty True -> Red Card. False -> Yellow Card
	 */
	public boolean getPenalty() {
		return this.penalty;
	}

	/**
	 * Setter method that sets boolean penalty to true or false
	 * @param penalty True -> Red Card. False -> Yellow Card
	 */
	public void setPenalty(boolean penalty) {
		this.penalty = penalty;
	}
	
	
	

}
