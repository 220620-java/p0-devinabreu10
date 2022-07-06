package dev.abreu.bankapp.models;

public class Account {

	/* Fields */
	
	private String accountType; // Checkings or Savings
	private int userID = -1;
	private int accountID = -1;
	private double initialDeposit;
	private double balance;

	/* Constructors */
	
	/**
	 * Allows for user id to be tied to that
	 * user's accounts
	 * 
	 * @param userid from user is set
	 */
	public Account(int userid) {
		setUserID(userid);
	}
	
	/**
	 * Parametrized constructor 
	 * 
	 * @param accountType 'Checkings' or 'Savings' account
	 * @param deposit Deposit made when account is first opened
	 */
	public Account(String accountType, double deposit) {
		this.accountType = accountType;
		this.initialDeposit = deposit;
		this.balance = initialDeposit;
	}
	
	public Account(int accountID, String accountType, double deposit) {
		this.accountID = accountID;
		this.accountType = accountType;
		this.initialDeposit = deposit;
		this.balance = initialDeposit;
	}
	
	/**
	 * No arguments constructor that instantiates
	 * an empty account
	 * 
	 */
	public Account() {
		this.accountType = "";
		this.initialDeposit = 0.00;
		this.balance = 0.00;
		//this.setTransactions(new ArrayList<>());
	}
	
	/* Public Methods (getters and setters) */

// Add methods of class below:

	public String getAccountType() {
		return accountType;
	}
	public void setAccountType(String accountType) {
		this.accountType = accountType;
	}
	public int getUserID() {
		return userID;
	}
	public void setUserID(int userID) {
		this.userID = userID;
	}
	public int getAccountID() {
		return accountID;
	}
	public void setAccountID(int accountID) {
		this.accountID = accountID;
	}
	public double getInitialDeposit() {
		return initialDeposit;
	}
	public void setInitialDeposit(double initialDeposit) {
		this.initialDeposit = initialDeposit;
	}
	public double getBalance() {
		return balance;
	}
	public void setBalance(double balance) {
		this.balance = balance;
	}

	
}
