package database;

import java.util.ArrayList;

import exception.EmptyListException;
import exception.MatchNotFoundException;
import exception.PlayerNotFoundException;
import exception.TeamNotFoundException;
import soccer.*;

/**
 * General container for the Match, Team and Player databases. Contains getter methods in order to access objects from database.
 */
public class DatabaseManager {
	
	/**
	 * List of the teams in the database
	 */
	private static ArrayList<Team>	listTeams;
	
	/**
	 * List of the players in the database
	 */
	private static ArrayList<Player> listPlayers;
	
	/**
	 * List of the matches in the database
	 */
	private static ArrayList<Match> listMatchs;
	
	/**
	 * Getter method returns match that matches matchID
	 * @param matchID matchID of match
	 * @return match  match with matching MatchID
	 * @throws MatchNotFoundException  Match not found in database
	 */
	public static Match getMatch(int matchID) throws MatchNotFoundException {
		for (int i=0; i < listMatchs.size(); i++) {
			if (listMatchs.get(i).getID() == matchID) {
				return listMatchs.get(i);
			}
		}
		throw new MatchNotFoundException();
	}
	
	/**
	 * Getter method that returns a list of the matchs
	 * @return list of matchs
	 * @throws EmptyListException Exception caused by a database with an empty list of matchs
	 */
	public static ArrayList<Match> getMatchList() throws EmptyListException {
		if (listMatchs == null)
			throw new EmptyListException();
		else
			return listMatchs;	
	}
	
	/**
	 * Getter method returns team that matches teamID
	 * @param teamID teamID of team 
	 * @return team with matching TeamID
	 * @throws TeamNotFoundException Team not found in database
	 */
	public static Team getTeam(int teamID) throws TeamNotFoundException {
		for (int i=0; i < listTeams.size(); i++) {
			if (listTeams.get(i).getID() == teamID) {
				return listTeams.get(i);
			}
		}
		throw new TeamNotFoundException();
	}
	
	/**
	 * Getter method that returns a list of the teams
	 * @return list of teams
	 * @throws EmptyListException Exception caused by a database with an empty list of teams
	 */
	public static ArrayList<Team> getTeamList() throws EmptyListException {
		if (listTeams == null)
			throw new EmptyListException();
		else
			return listTeams;	
	}
	
	/**
	 * Getter method returns player that matches playerID
	 * @param playerID playerID of player
	 * @return player with matching PlayerID
	 * @throws PlayerNotFoundException Player not found in database
	 */
	public static Player getPlayer(int playerID) throws PlayerNotFoundException {
		for (int i=0; i < listPlayers.size(); i++) {
			if (listPlayers.get(i).getID() == playerID) {
				return listPlayers.get(i);
			}
		}
		throw new PlayerNotFoundException();
	}
	
	/**
	 * Getter method that returns a list of the players
	 * @return list of Players
	 * @throws EmptyListException Exception caused by a database with an empty list of players
	 */
	public static ArrayList<Player> getPlayerList() throws EmptyListException {
		if (listPlayers == null)
			throw new EmptyListException();
		else
			return listPlayers;	
	}
	
	
	
	
	
	
}
