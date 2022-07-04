package dev.abreu.bankapp.models;

import java.util.Scanner;

import dev.abreu.bankapp.ds.ArrayList;
import dev.abreu.bankapp.ds.List;

public class Account {
	// Add the necessary variable here:
	
	private String accountType; // Checkings or Savings
	private int userID = -1;
	private int accountID = -1;
	private double initialDeposit;
	private double balance; // need two variables for checkings/savings??
	private List<Account> transactions;

	private static Scanner scan = new Scanner(System.in);

	// Create the constructor here:
	public Account(int userid) {
		setUserID(userid);
	}
	
	public Account(String accType, double deposit) {
		this.accountType = accType;
		this.setInitialDeposit(deposit);
		this.balance = deposit;
		this.setTransactions(new ArrayList<>());
	}
	public Account() {
		this.accountType = "";
		this.setInitialDeposit(0.00);
		this.balance = 0.00;
		this.setTransactions(new ArrayList<>());
	}
	

// Add methods of class below:

	
	
//	public void deposit() { // no negative deposits
//		double amount;
//		System.out.println("Enter amount to deposit: ");
//		amount = scan.nextDouble();
//		if (amount >= 0) {
//			balance += amount;
//			System.out.println("New balance is: " + balance);
//		} else {
//			System.out.println("Invalid deposit, try again");
//		}
//	}
//
//	private void withdraw() { // no negative withdraws
//		double amount;
//		System.out.println("Enter amount to withdraw: ");
//		amount = scan.nextDouble();
//		if (balance >= amount) {
//			balance -= amount;
//			System.out.println("New balance is: " + balance);
//		} else {
//			System.out.println("Insufficient funds are available");
//		}
//	}

	private void transferFunds() {
		// TODO
		System.out.println("Enter amount to transfer: ");
		double amount = scan.nextDouble();
		
		System.out.println("Select the account to transfer to:\n" + "1. Checkings\n" 
							+ "2. Savings" + "3. Go back to main menu");
		String input = scan.nextLine();
		
		switch(input) {
		case "1": // transfer to checkings
			//checkingsBalance += amount;
			//savingsBalance = -= amount;
			break;
		case "2": // transfer to savings
			//checkingsBalance -= amount;
			//savingsBalance = += amount;
			break;
		default:
			System.out.println("Going back to main menu");
		
		}
	}

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

	public List<Account> getTransactions() {
		return transactions;
	}

	public void setTransactions(List<Account> transactions) {
		this.transactions = transactions;
	}

	
}
