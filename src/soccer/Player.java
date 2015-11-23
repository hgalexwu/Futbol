package soccer;

import java.util.ArrayList;

import exception.*;

/**
 * Player instance acting as a container for information regarding the player. Information assigned by the database Controller
 */
public class Player {


	/**
	 * Player constructor
	 * @param id Player ID length can't be over 7 digits
	 * @param name name of the player. Max 20 digits
	 * @param number number of the player. [0,100[
	 * @throws NameLengthException Length of name too long
	 * @throws NumberLengthException Number is not between [0,100[
	 * @throws IdLengthException id is either negative or it's length is above 7 digits
	 */
	public Player(int id, String name, int number) throws NameLengthException, NumberLengthException, IdLengthException {

		// check if ID is over 7 digits or negative.
		if (id < 1 || id > 9999999)
			throw new IdLengthException();
		else
			this.id = id;
		// Check for length of name which can't be over 20 characters
		if (name.length() > 20)
			throw new NameLengthException();
		else
			this.name = name;
		// Check for player number which can't be below 0 or above 99
		if (number < 0 || number > 99) 
			throw new NumberLengthException();
		else
			this.number = number;
		// Initialize class attributes
		numGoals = 0;
		numInfractions = 0;
		listShots = new ArrayList<Shot>();
		listInfractions = new ArrayList<Infraction>();
	}

	/**
	 * Player ID
	 */
	private int id;

	/**
	 * Player name
	 */
	private String name;

	/**
	 * Player number [0,100[
	 */
	private int number;

	/**
	 * Nb of Goals
	 */
	private int numGoals;

	/**
	 * Nb of infractions
	 */
	private int numInfractions;

	/**
	 * List of shots
	 */
	ArrayList<Shot>  listShots;

	/**
	 * List of infraction
	 */
	ArrayList<Infraction> listInfractions;

	/**
	 * Getter method for player ID
	 * @return ID of player
	 */
	public int getID() {
		return this.id;
	}

	/**
	 * Getter method for player name
	 * @return name of player
	 */
	public String getName() {
		return this.name;
	}

	/**
	 * Getter method for player nb
	 * @return player's number
	 */
	public int getNumber() {
		return this.number;
	}

	/**
	 * Getter method for nb of goals
	 * @return nb of goals scored by player
	 */
	public int getNumGoals() {
		return this.numGoals;
	}

	/**
	 * Getter method for nb of infractions
	 * @return nb of infractions by player
	 */
	public int getNumInfractions() {
		return this.numInfractions;
	}

	/**
	 * Getter method for list of shots
	 * @return list of shots by player
	 */
	public ArrayList<Shot> getListOfShots() {
		return this.listShots;
	}

	/**
	 * Getter method for list of infractions
	 * @return list of infractions by player
	 */
	public ArrayList<Infraction> getListOfInfractions() {
		return this.listInfractions;
	}

	/**
	 * Setter method to set name of player
	 * @param name player's new name
	 * @throws NameLengthException Length of name too long
	 */
	public void setName(String name) throws NameLengthException {

		// Check for length of name which can't be over 20 characters
		if (name.length() > 20)
			throw new NameLengthException();
		else
			this.name = name;
	}

	/**
	 * Setter method to set player's number
	 * @param number player's new number
	 * @throws NumberLengthException Number is not between [0,100[
	 */
	public void setNumber(int number) throws NumberLengthException {
		// Check for player number which can't be below 0 or above 99
		if (number < 0 || number > 99) 
			throw new NumberLengthException();
		else
			this.number = number;
	}

	/**
	 * Adding a shot to player
	 * @param isGoal True -> shot resulted in goal. False -> shot didn't result in goal.
	 */
	public void addShot(boolean isGoal) {
		listShots.add(new Shot(isGoal));
		if (isGoal) {
			numGoals ++;
		}
	}

	/**
	 * Adding an infraction to a player
	 * @param card True -> red card. False -> yellow card,
	 */
	public void addInfraction(boolean card) {
		listInfractions.add(new Infraction(card));
		numInfractions ++;
	}


}
