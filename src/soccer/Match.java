package soccer;

/**
 * Match instance that acts as a container for information regarding a Match. Information assigned by the database Controller
 */
public class Match {
	
	/**
	 * Match constructor
	 * @param id Match ID
	 */
	public Match (int id) {
		this.id = id;
	}
	
	/**
	 * Match ID
	 */
	private int id;
	
	/**
	 * Away Team
	 */
	private Team awayTeam;
	
	/**
	 * Home Team
	 */
	private Team homeTeam;
	
	/**
	 * Winning Team
	 */
	private Team winningTeam;
	
	/**
	 * Losing Team
	 */
	private Team losingTeam;
	
	/**
	 * Getter method returns id of team
	 * @return Team ID
	 */
	public int getID() {
		return this.id;
	}
	
	/**
	 * Getter method returns away team
	 * @return Away team
	 */
	public Team getAwayTeam() {
		return this.awayTeam;
	}
	
	/**
	 * Getter method returns home team
	 * @return Home team
	 */
	public Team getHomeTeam() {
		return this.homeTeam;
	}
	
	/**
	 * Getter method returns winning team
	 * @return Winning Team
	 */
	public Team getWinningTeam() {
		return this.winningTeam;
	}
	
	/**
	 * Getter method returns losing team
	 * @return Losing Team
	 */
	public Team getLosingTeam() {
		return this.losingTeam;
	}
	
	/**
	 * Setter method sets away team
	 * @param awayTeam Away Team
	 */
	public void setAwayTeam(Team awayTeam) {
		this.awayTeam = awayTeam;
	}
	
	/**
	 * Setter method sets home team
	 * @param homeTeam Home Team
	 */
	public void setHomeTeam(Team homeTeam) {
		this.homeTeam = homeTeam;
	}

	/**
	 * Setter method sets winning team
	 * @param wTeam Winning Team
	 */
	public void setWinningTeam(Team wTeam) {
		this.winningTeam = wTeam;
	}
	
	/**
	 * Setter method sets losing team
	 * @param lTeam Losing Team
	 */
	public void setLosingTeam(Team lTeam) {
		this.losingTeam = lTeam;
	}

}
