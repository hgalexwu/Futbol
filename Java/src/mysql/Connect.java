package mysql;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
import java.util.logging.Level;
import java.util.logging.Logger;

import soccer.*;

import database.DatabaseManager;

public class Connect {
	
	//Connection variables
	private Connection con = null;
	private Statement st = null;
	private ResultSet rs = null;
	
	//MySQL host, user, password
	protected final String url = "jdbc:mysql://104.236.220.130:3306/futbol";
	protected final String user = "root";
	protected final String password = "user";


	/**
	 * Constructor method
	 * 
	 */
	public Connect() {
		connect();
	}
	
	/**
	 * Connects to the mySQL database and parses all data.
	 */
	public void connect() {

		// Start Driver
		try {
			Class.forName("com.mysql.jdbc.Driver");
		} catch (ClassNotFoundException e) {
			e.printStackTrace();
		}

		// Attempt to connect
		try {
			con = DriverManager.getConnection(url, user, password);
			st = con.createStatement();
			rs = st.executeQuery("SELECT VERSION()");

			if (rs.next()) {
				System.out.println(rs.getString(1));
				System.out.println("Successfully connected to futbol database.");
				
				populateTeam();
				populatePlayer();
				populateMatch();
				populateInfraction();
				populateShot();
			}

		} catch (SQLException ex) {
			Logger lgr = Logger.getLogger(Connect.class.getName());
			lgr.log(Level.SEVERE, ex.getMessage(), ex);

		}
	}
	
	/**
	 * Populate local database with shots from mySQL database
	 */
	public void populateShot(){
		PreparedStatement pst = null;
		ResultSet result = null;
		
		try {
			pst = con.prepareStatement("SELECT * FROM Shot");
			result = pst.executeQuery();
			
			while (result.next()) {
				boolean isGoal = result.getBoolean(1);
				int playerID = result.getInt(2);
			
				Shot shot = new Shot(isGoal);
				
				for(Player player : DatabaseManager.listPlayers){
					if(playerID == player.getID()){
						player.addToListOfShots(shot);
						break;
					}
				}
				
				DatabaseManager.addShot(shot);
			}
			
		}catch(Exception e){
			e.printStackTrace();
		}
	}
	
	/**
	 * Populate local database with infractions from mySQL database
	 */
	public void populateInfraction(){
		PreparedStatement pst = null;
		ResultSet result = null;
		
		try {
			pst = con.prepareStatement("SELECT * FROM Infraction");
			result = pst.executeQuery();
			
			while (result.next()) {
				String type = result.getString(1);
				int playerID = result.getInt(2);
				
				boolean isRed = false;
				
				if(type.equals("Yellow")){
					isRed = false;
				} else{
					isRed = true;
				}
				
				Infraction infraction = new Infraction(isRed);
				
				for(Player player : DatabaseManager.listPlayers){
					if(playerID == player.getID()){
						player.addToListOfInfraction(infraction);
						break;
					}
				}
				
				DatabaseManager.addInfraction(infraction);
			}
			
		}catch(Exception e){
			e.printStackTrace();
		}
	}
	
	/**
	 * Populates local database with matches from mySQL database
	 */
	public void populateMatch(){
		PreparedStatement pst = null;
		ResultSet result = null;
		
		try {
			pst = con.prepareStatement("SELECT * FROM Matches");
			result = pst.executeQuery();
			while (result.next()) {
				int id = result.getInt(1);
				int awayTeamID = result.getInt(2);
				int homeTeamID = result.getInt(3);
				int winningTeamID = result.getInt(4);
				int losingTeamID = result.getInt(5);
				boolean isDraw = result.getBoolean(6);
				
				//Find the team objects
				int found = 0;
				Team awayTeam = null, homeTeam = null;
				boolean homeWinner = false;
				for(Team team : DatabaseManager.listTeams){
					if(found == 2){
						break;
					}
					
					if(team.getID() == awayTeamID){
						awayTeam = team;
						found++;
						
						//Did away win?
						if(team.getID() == winningTeamID){
							homeWinner = false;
						}
					}
					else if(team.getID() == homeTeamID){
						homeTeam = team;
						found++;
						
						//Did home win?
						if(team.getID() == winningTeamID){
							homeWinner = true;
						}
					}
					
				}
				
				Match match = new Match(id);
				match.setAwayTeam(awayTeam);
				match.setHomeTeam(homeTeam);
				
				if(isDraw){
					match.setWinningTeam(null);
					match.setLosingTeam(null);
				}
				else if(homeWinner){
					match.setWinningTeam(homeTeam);
					match.setLosingTeam(awayTeam);
				} else {
					match.setLosingTeam(homeTeam);
					match.setWinningTeam(awayTeam);
				}
		
				
				DatabaseManager.addMatch(match);
			}
			
		}catch(Exception e){
			e.printStackTrace();
		}
	}
	/**
	 * Populates local database with teams from mySQL database
	 */
	public void populateTeam(){
		PreparedStatement pst = null;
		ResultSet result = null;
		
		try {
			pst = con.prepareStatement("SELECT * FROM Team");
			result = pst.executeQuery();
			
			while (result.next()) {
				int id = result.getInt(1);
				String name = result.getString(2);
				int numPoints = result.getInt(3);
				int numGoals = result.getInt(4);
				int numPenalties = result.getInt(5);
				
				Team team = new Team(id);
				team.setName(name);
				team.setNumGoals(numGoals);
				team.setNumPoints(numPoints);
				team.setNumPenalties(numPenalties);
				
				System.out.println(team.getName());
				
				DatabaseManager.addTeam(team);
				
				
			}
			
		}catch(Exception e){
			e.printStackTrace();
		}
	}
	
	/**
	 * Populates local database with players from mySQL database
	 */
	public void populatePlayer() {

		PreparedStatement pst = null;
		ResultSet result = null;

		try {
			pst = con.prepareStatement("SELECT * FROM Player");
			result = pst.executeQuery();

			while (result.next()) {
				int id = result.getInt(1);
				String name = result.getString(2);
				int number = result.getInt(3);
				int numGoals = result.getInt(4);
				int numInfractions = result.getInt(5);
				int teamID = result.getInt(6);
				
				Player player = new Player(id, name, number);
				player.setNumGoals(numGoals);
				player.setNumInfractions(numInfractions);
				player.setTeamID(teamID);
				
				//Add to player global arraylist 
				DatabaseManager.addPlayer(player, teamID);
			}
		} catch (Exception e) {
			e.printStackTrace();
		}
	}

	/**
	 * Updates the mySQL database in terms of goals for player and respective team
	 * @param playerID
	 * @param teamID
	 * @param newPlayerGoals
	 * @param newTeamGoals
	 */
	public void addGoal(int playerID, int teamID, int newPlayerGoals, int newTeamGoals){
		PreparedStatement pst = null;
		int result;
		newPlayerGoals++;
		newTeamGoals++;
		try {
			pst = con.prepareStatement("UPDATE Player SET numGoals = " + newPlayerGoals + " WHERE id = " + playerID);
			result = pst.executeUpdate();
			System.out.println("Updated player successfully.");
			
			pst = con.prepareStatement("UPDATE Team SET numGoals = " + newTeamGoals + " WHERE id = " + teamID);
			result = pst.executeUpdate();
			System.out.println("Updated team successfully.");
			
			
		} catch (SQLException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
	}
	
	/**
	 * Creates a new infraction and adds it in to the mySQL database.
	 * @param playerID
	 * @param teamID
	 * @param type
	 */
	public void addInfraction(int playerID, int teamID, boolean isRed, int newPlayerPenalties, int newTeamPenalties){
		PreparedStatement pst = null;
		int result;
		String type = "";
		
		if(isRed){
			type = "'Red'";
		} else{
			type = "'Yellow'";
		}
		
		newPlayerPenalties++;
		newTeamPenalties++;
		
		try {
			pst = con.prepareStatement("INSERT INTO Infraction VALUES ( " + type + "," + playerID +")");
			result = pst.executeUpdate();
			System.out.println("Added infraction successfully.");
			
			pst = con.prepareStatement("UPDATE Player SET numGoals = " + newPlayerPenalties + " WHERE id = " + playerID);
			result = pst.executeUpdate();
			System.out.println("Updated player successfully.");
			
			pst = con.prepareStatement("UPDATE Team SET numGoals = " + newTeamPenalties + " WHERE id = " + teamID);
			result = pst.executeUpdate();
			System.out.println("Updated team successfully.");
			
			
		} catch (SQLException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
	}
	
	/**
	 * Adds a shot to the mySQL database
	 * @param isGoal
	 * @param playerID
	 */
	public void addShot(boolean isGoal, int playerID){
		PreparedStatement pst = null;
		int result;
	
		try {
			pst = con.prepareStatement("INSERT INTO Shot VALUES ( " + isGoal + "," + playerID +")");
			result = pst.executeUpdate();
			System.out.println("Added shot successfully.");
			
			
		} catch (SQLException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
	}
	
	/**
	 * Creates a new match and adds it in to the mySQL database. Also updates the respective team in terms of points.
	 * @param winnerID
	 * @param loserID
	 */
	public void addMatch(int homeID, int awayID, int homePoints, int awayPoints, int victory){
		PreparedStatement pst = null;
		int result;
		
		//If victory == 0 -> Home team won
		//   victory == 1 -> Away team won
		//   victory == 2 -> Tie
		if(victory == 0){
			homePoints = homePoints + 3;
			
			try {
				String values = awayID + "," + homeID + "," + homeID + "," + awayID + ",false)";
				pst = con.prepareStatement("INSERT INTO Matches(awayTeamID,homeTeamID,winningID,losingID,isDraw) VALUES (" + values);
				result = pst.executeUpdate();
			} catch (SQLException e) {
				// TODO Auto-generated catch block
				e.printStackTrace();
			}
			
			
		} else if(victory==1){
			awayPoints = awayPoints + 3;
			
			try {
				String values = awayID + "," + homeID + "," + awayID + "," + homeID + ",false)";
				pst = con.prepareStatement("INSERT INTO Matches(awayTeamID,homeTeamID,winningID,losingID,isDraw) VALUES (" + values);
				result = pst.executeUpdate();
			} catch (SQLException e) {
				// TODO Auto-generated catch block
				e.printStackTrace();
			}
			
		} else{
			homePoints = homePoints + 1;
			awayPoints = awayPoints + 1;
			
			try {
				String values = awayID + "," + homeID + ",true)";
				pst = con.prepareStatement("INSERT INTO Matches(awayTeamID,homeTeamID,isDraw) VALUES (" + values);
				result = pst.executeUpdate();
			} catch (SQLException e) {
				// TODO Auto-generated catch block
				e.printStackTrace();
			}
		}
		
		try {
			pst = con.prepareStatement("UPDATE Team SET numPoints = " + homePoints + " WHERE id = " + homeID);
			result = pst.executeUpdate();
			System.out.println("Updated player successfully.");
			
			pst = con.prepareStatement("UPDATE Team SET numPoints = " + awayPoints + " WHERE id = " + awayID);
			result = pst.executeUpdate();
			
			
			
			System.out.println("Updated team successfully.");
			
			
		} catch (SQLException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
	}
	
}


