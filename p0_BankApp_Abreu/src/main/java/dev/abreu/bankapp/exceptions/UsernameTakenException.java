package dev.abreu.bankapp.exceptions;

// This is a checked exception that should be handled through "try-catch" or "throws"
public class UsernameTakenException extends Exception {

	/**
	 * throws expection if user tries to register with a
	 * username that already belongs in the system
	 * 
	 */
	private static final long serialVersionUID = 507789384452420958L;

}
