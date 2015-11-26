package soccer;

import java.util.ArrayList;

import exception.InvalidInputException;
import exception.PlayerNotFoundException;

/**
 * Team instance that acts as a container for information regarding a team.Information assigned by the database Controller
 */
public class Team {
	
	/**
	 * Team Constructor 
	 * @param id Team ID
	 */
	public Team(int id){
		this.id = id;
	}
	
	/**
	 * Team ID
	 */
	private int id;
	
	/**
	 * List of players on the team
	 */
	private ArrayList<Player> listPlayers;
	
	/**
	 * number of Points of team
	 */
	private int numPoints;
	
	/**
	 * number of Goals scored by team
	 */
	private int numGoals;
	
	/**
	 * number of penalties by team
	 */
	private int numPenalties;
	
	/**
	 * Getter method returns team ID
	 * @return Team ID
	 */
	public int getID() {
		return this.id;
	}
	
	/**
	 * Getter method returns player list
	 * @return ArrayList of players
	 */
	public ArrayList<Player> getPlayerList() {
		return this.listPlayers;
	}
	
	/**
	 * Getter method returns Player
	 * @param playerID player ID of player you are looking for
	 * @return Player matching player ID
	 * @throws PlayerNotFoundException Player wasn't found on team
	 */
	public Player getPlayer(int playerID) throws PlayerNotFoundException {
		for (int i=0; i < listPlayers.size(); i++) {
			if (listPlayers.get(i).getID() == playerID) {
				return listPlayers.get(i);
			}
		}
		throw new PlayerNotFoundException();
	}
	
	/**
	 * Getter method that returns number of points of team
	 * @return Number of Points of team
	 */
	public int getNumPoints() {
		return this.numPoints;
	}
	
	/**
	 * Getter method that returns number of goals scored by team
	 * @return Number of Goals score by team
	 */
	public int getNumGoals() {
		return this.numGoals;
	}
	
	/**
	 * Getter method that returns number of Penalties of team
	 * @return Number of penalties of team
	 */
	public int getNumPenalties() {
		return this.numPenalties;
	}
	
	/**
	 * Setter method that adds points to the team
	 * @param points Number of points to add to team
	 * @throws InvalidInputException Number of points added is negative
	 */
	public void addNumPoints(int points) throws InvalidInputException{
		if (numPoints < 0)
			throw new InvalidInputException();
		else
			this.numPoints += points;
	}
	
	/**
	 * Setter method that adds goals to a team
	 * @param goals Number of goals to add to team
	 * @throws InvalidInputException Number of goals added is negative
	 */
	public void addNumGoals(int goals) throws InvalidInputException {
		if (goals < 0)
			throw new InvalidInputException();
		else 
			this.numGoals += goals;
	}
	
	/**
	 * Setter method that adds penalties to a team
	 * @param penalties Number of penalties to add to team
	 * @throws InvalidInputException Number of penalties added is negative
	 */
	public void addNumPenalties(int penalties) throws InvalidInputException {
		if (penalties < 0)
			throw new InvalidInputException();
		else 
			this.numPenalties += penalties;
	}
	
	/**
	 * Setter method that sets points of a team
	 * @param points Number of points of team
	 * @throws InvalidInputException Number of points of team is negative
	 */
	public void setNumPoints(int points) throws InvalidInputException{
		if (numPoints < 0)
			throw new InvalidInputException();
		else
			this.numPoints += points;
	}
	
	/**
	 * Setter method that sets goals of a team
	 * @param goals Number of goals scored by team
	 * @throws InvalidInputException Number of goals of team is negative
	 */
	public void setNumGoals(int goals) throws InvalidInputException {
		if (goals < 0)
			throw new InvalidInputException();
		else 
			this.numGoals = goals;
	}
	
	/**
	 * Setter method that sets penalties of a team
	 * @param penalties Number of penalties of team
	 * @throws InvalidInputException Number of penalties of team is negative
	 */
	public void setNumPenalties(int penalties) throws InvalidInputException {
		if (penalties < 0)
			throw new InvalidInputException();
		else 
			this.numPenalties = penalties;
	}
	
	

}
