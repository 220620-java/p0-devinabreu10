package dev.abreu.bankapp.models;

import dev.abreu.bankapp.ds.ArrayList;
import dev.abreu.bankapp.ds.List;

public class User {
	
	/* Fields */
	
	private int id;
	private String userFirstName;
	private String userLastName;
	private String username;
	private String password;
	private Account account;
	List<Account> accounts = new ArrayList<>();
	
	
	/* Constructors */
	
	/**
	 * No arguments constructor that instantiates
	 * an empty User
	 * 
	 */
	public User() {
		this.id = 0;
		this.userFirstName = "";
		this.userLastName = "";
		this.username = "";
		this.password = "";
		this.setAccount(account);
	}
	
	/**
	 * Parametrized constructor used when a new user
	 * is being registered in the system
	 * 
	 * @param firstName User's first name
	 * @param lastName User's last name
	 * @param username username associated with User
	 * @param password password associated with User
	 * @param account initial account associated with User
	 */
	public User(String firstName, String lastName, String username, String password, Account account) {
		this.id = 0;
		this.userFirstName = firstName;
		this.userLastName = lastName;
		this.username = username;
		this.password = password;
		this.setAccount(account);
	}
	
	/**
	 * Used for User's who already have an account
	 * and are attempting to sign in
	 * 
	 * @param username User's username for sign in
	 * @param password User's password for sign in
	 */
	public User(String username, String password) {
		this.username = username;
		this.password = password;
	}
	
	
	/* Public Methods (getters and setters) */

	public int getId() {
		return id;
	}

	public void setId(int id) {
		this.id = id;
	}

	public String getUserFirstName() {
		return userFirstName;
	}

	public void setUserFirstName(String userFirstName) {
		this.userFirstName = userFirstName;
	}

	public String getUserLastName() {
		return userLastName;
	}

	public void setUserLastName(String userLastName) {
		this.userLastName = userLastName;
	}

	public String getUsername() {
		return username;
	}

	public void setUsername(String username) {
		this.username = username;
	}

	public String getPassword() {
		return password;
	}

	public void setPassword(String password) {
		this.password = password;
	}

	public Account getAccount() {
		return account;
	}

	public void setAccount(Account account) {
		this.account = account;
	}

	
	
	
	
}
