package database;

import java.util.Random;

import exception.EmptyListException;
import exception.InvalidInputException;
import exception.MatchNotFoundException;
import exception.PlayerNotFoundException;
import exception.TeamNotFoundException;
import soccer.*;

/**
 * Database controller processes user data input and updates data for Player, Team and Match in the database manager
 */
public class DatabaseController {

	/**
	 * Database manager instance stores data
	 */
	private static DatabaseManager databaseManager;

	/**
	 * Add a new match to the database
	 * @param awayTeam awayTeam
	 * @param homeTeam homeTeam
	 */
	public static void createNewMatch(Team awayTeam, Team homeTeam) {
		// Create a new match id that chooses random integer from 0 to 999 9999
		Random random = new Random();
		Match match = new Match(random.nextInt(10000000));
		// set away and home teams
		match.setAwayTeam(awayTeam);
		match.setHomeTeam(homeTeam);
		// Try catch loop in order to catch possible unintialized arraylists
		try {
			DatabaseManager.getMatchList().add(match);
		} catch (EmptyListException e) {
			System.out.println("Database manager's list not yet initialized");
		}
	}

	/**
	 * Set winning team of a match
	 * @param match Match
	 * @param wTeam winning Team
	 * @param lTeam losing Team
	 */
	public static void setWinningTeam(Match match, Team wTeam, Team lTeam) {
		// Try catch loop in order to catch possible matchs that do not exist in database
		try {
			Match tempMatch = DatabaseManager.getMatch(match.getID());
			tempMatch.setLosingTeam(lTeam);
			tempMatch.setWinningTeam(wTeam);
		} catch (MatchNotFoundException e) {
			System.out.println("Match not found in database");
		}
		
	}

	/**
	 * Add a shot to a player in the database
	 * @param player Player who you are adding a shot to
	 * @param isGoal True if shot resulted in goal, false otherwise
	 */
	public static void addShot(Player player, boolean isGoal) {
		// Try catch loop in order to catch possible players that do not exist in database
		try {
			Player tempPlayer = DatabaseManager.getPlayer(player.getID());
			tempPlayer.addShot(isGoal);
		} catch (PlayerNotFoundException e) {
			System.out.println("Player not found in database");	
		}
	}

	/**
	 * Add an infraction to a player
	 * @param player Player who received an infraction
	 * @param penalty if true, penalty was a red card, yellow otherwise
	 */
	public static void addInfraction(Player player, boolean penalty) {
		// Try catch loop in order to catch possible players that do not exist in database
		try {
			Player tempPlayer = DatabaseManager.getPlayer(player.getID());
			tempPlayer.addInfraction(penalty);
		} catch (PlayerNotFoundException e) {
			System.out.println("Player not found in database");	
		}
	}

	/**
	 * Add points to a team
	 * @param team Team whose points you are adding
	 * @param points amount of points added
	 */
	public static void addPoints(Team team, int points) {
		// Try catch loop in order to catch possible teams that do not exist in database or to catch negative points
		try {
			Team tempTeam = DatabaseManager.getTeam(team.getID());
			tempTeam.addNumPoints(points);
		} catch (TeamNotFoundException e) {
			System.out.println("Team not found in database");
		} catch (InvalidInputException e) {
			System.out.println("Error: Can't add negative points to team");
		}
	}

	/**
	 * Add a player to a team in the database
	 * @param team Team of the new player
	 * @param player Player added
	 */
	public static void addPlayer(Team team, Player player) {
		// Try catch loop in order to catch teams/players that do not exist in database
		Team tempTeam;
		try {
			tempTeam = DatabaseManager.getTeam(team.getID());
			tempTeam.getPlayerList().add(DatabaseManager.getPlayer(player.getID()));

		} catch (TeamNotFoundException e) {
			System.out.println("Team not found in database");
		} catch (PlayerNotFoundException e) {
			System.out.println("Player not found in database");
		}
	}

	/**
	 * Remove a player from a team in the database
	 * @param team Team of the former player
	 * @param player Player removed
	 */
	public static void removePlayer(Team team, Player player) {
		// Try catch loop in order to catch teams/players that do not exist in database
		try {
			Team tempTeam = DatabaseManager.getTeam(team.getID());
			tempTeam.getPlayerList().remove(DatabaseManager.getPlayer(player.getID()));
		} catch (TeamNotFoundException e) {
			System.out.println("Team not found in database");

		} catch (PlayerNotFoundException e) {
			System.out.println("Player not found in database");
		}
	}



}
