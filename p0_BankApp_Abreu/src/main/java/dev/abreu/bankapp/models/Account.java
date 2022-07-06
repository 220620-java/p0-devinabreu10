package dev.abreu.bankapp.models;

import java.util.Objects;

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

	@Override
	public int hashCode() {
		return Objects.hash(accountID, accountType, balance, initialDeposit, userID);
	}

	@Override
	public boolean equals(Object obj) {
		if (this == obj)
			return true;
		if (obj == null)
			return false;
		if (getClass() != obj.getClass())
			return false;
		Account other = (Account) obj;
		return accountID == other.accountID && Objects.equals(accountType, other.accountType)
				&& Double.doubleToLongBits(balance) == Double.doubleToLongBits(other.balance)
				&& Double.doubleToLongBits(initialDeposit) == Double.doubleToLongBits(other.initialDeposit)
				&& userID == other.userID;
	}

	@Override
	public String toString() {
		return "Account [accountType=" + accountType + ", userID=" + userID + ", accountID=" + accountID
				+ ", initialDeposit=" + initialDeposit + ", balance=" + balance + "]";
	}

}
