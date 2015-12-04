package com.fourpointzeroteam.nathan.fantasyfutbol.Futbol.database;

import java.util.ArrayList;
import com.fourpointzeroteam.nathan.fantasyfutbol.Futbol.exception.EmptyListException;
import com.fourpointzeroteam.nathan.fantasyfutbol.Futbol.exception.MatchNotFoundException;
import com.fourpointzeroteam.nathan.fantasyfutbol.Futbol.exception.PlayerNotFoundException;
import com.fourpointzeroteam.nathan.fantasyfutbol.Futbol.exception.TeamNotFoundException;
import com.fourpointzeroteam.nathan.fantasyfutbol.Futbol.soccer.*;
import com.fourpointzeroteam.nathan.fantasyfutbol.Futbol.mysql.Connect;


/**
 * General container for the Match, Team and Player databases. Contains getter methods in order to access objects from database.
 */
public class DatabaseManager {

	/**
	 * List of the teams in the database
	 */
	public static ArrayList<Team>	listTeams = new ArrayList<Team>();

	/**
	 * List of the players in the database
	 */
	public static ArrayList<Player> listPlayers = new ArrayList<Player>();

	/**
	 * List of the matches in the database
	 */
	public static ArrayList<Match> listMatchs = new ArrayList<Match>();

	/**
	 * List of infractions in the database
	 */
	public static ArrayList<Infraction> listInfractions = new ArrayList<Infraction>();

	/**
	 * List of Shots in the database
	 */
	public static ArrayList<Shot> listShots = new ArrayList<Shot>();


	/**
	 * mySQL connection object
	 */
	private static Connect con;

	/**
	 * Starts up DatabaseManager, connects to mySQL database, then starts DatabaseController
	 */
	public static void start(){
		Connect con = new Connect();
		DatabaseController.start(con);
	}


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

	/**
	 * Adds a player into the database
	 * @param player
	 * @param teamID
	 */
	public static void addPlayer(Player player, int teamID){
		//Add player
		listPlayers.add(player);

		//Add player to team
		for(int i = 0; i < listTeams.size(); i++){
			if(listTeams.get(i).getID() == teamID){
				listTeams.get(i).addPlayerToTeam(player);
				break;
			}
		}
	}

	/**
	 * Adds a team into the database
	 * @param team
	 */
	public static void addTeam(Team team){
		//Add team
		listTeams.add(team);
	}

	/**
	 * Adds a match into the database
	 * @param match
	 */
	public static void addMatch(Match match){
		//Add a match
		listMatchs.add(match);
	}

	/**
	 * Adds an infraction into the database
	 * @param match
	 */
	public static void addInfraction(Infraction infraction){
		//Add a match
		listInfractions.add(infraction);
	}

	/**
	 * Adds a shot into the database
	 * @param match
	 */
	public static void addShot(Shot shot){
		//Add a match
		listShots.add(shot);
	}






}
