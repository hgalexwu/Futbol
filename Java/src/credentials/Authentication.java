package com.fourpointzeroteam.nathan.fantasyfutbol.Futbol.credentials;

import java.util.Hashtable;

import com.fourpointzeroteam.nathan.fantasyfutbol.Futbol.scorekeeperView.ScorekeeperView;
import com.fourpointzeroteam.nathan.fantasyfutbol.Futbol.exception.*;

/**
 * Authentication class that allows access to the ScorekeeperView if the username and password are correct
 */
public class Authentication {
	
	/**
	 * Hashtable instance. Keys (String) -> Username. Values (String) -> Password.
	 */
	private static Hashtable<String, String> credentials = new Hashtable<String, String>() {{ put("nlarue", "fut001"); put("bkiml", "fut010");put("test", "test");put("alewu", "fut100"); put("1", "1");}};
	
	private static ScorekeeperView scorekeeperUI;
	
	/**
	 * Check if username matches password and allow access to scorekeeper view
	 * @param username Username of scorekeeper. Max 20 digits.
	 * @param password Password of scorekeeper. Max 20 digits.
	 * @throws UsernameLengthException Username is over 20 digits
	 * @throws PasswordLengthException Password is over 20 digits
	 * @throws EmtpyHashtableException Hashtable is empty. No scorekeepers in database
	 * @throws InvalidUsernameException Username doesn't exist in database
	 * @throws InvalidInputException Empty string or null string entered 
	 */
	public static boolean authenticate(String username, String password) throws EmptyHashtableException, InvalidUsernameException, InvalidInputException, UsernameLengthException, PasswordLengthException{
		
		// Boolean regarding if username and password matches
		boolean isMatch = false;
		
		// Check for valid input
		if (username.equals("") || password.equals("") || username == null || password == null) {
			throw new InvalidInputException();
		}
		// Check for empty Hashtable
		else if (credentials.isEmpty()) {
			throw new EmptyHashtableException();
		}
		// Check if username is over 20 digits
		else if (username.length() > 20) {
			throw new UsernameLengthException();
		}
		// Check if password is over 20 digits
		else if (password.length() > 20) {
			throw new PasswordLengthException();
		}
		// Check for valid username
		else if (!credentials.containsKey(username)) {
			throw new InvalidUsernameException();
		}
		else {
			isMatch = (credentials.get(username).equals(password));
			if (isMatch)
				scorekeeperUI = new ScorekeeperView();
		}
		
		return isMatch;
	}
	

}
