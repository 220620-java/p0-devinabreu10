package dev.abreu.bankapp.models;

import java.util.Scanner;

public class Account {
	// Add the necessary variable here:
	
	private String accountType; // Checkings or Savings
	private double initialDeposit;
	private double balance; // need two variables for checkings/savings??

	private static Scanner scan = new Scanner(System.in);

	// Make this class a JavaBean by implementing Serializable, setting everything
	// to
	// private and using getters/setters, inlcuding a public no-argument constructor

	// Create the constructor here:
	public Account(String accType, double deposit) {
		this.accountType = accType;
		this.initialDeposit = deposit;
		this.balance = deposit;
	}
	public Account() {
		this.accountType = "";
		this.initialDeposit = 0.00;
		this.balance = 0.00;
	}
	

// Add methods of class below:
	private void deposit() { // no negative deposits
		double amount;
		System.out.println("Enter amount to deposit: ");
		amount = scan.nextDouble();
		if (amount >= 0) {
			balance += amount;
			System.out.println("New balance is: " + balance);
		} else {
			System.out.println("Invalid deposit, try again");
		}
	}

	private void withdraw() { // no negative withdraws
		double amount;
		System.out.println("Enter amount to withdraw: ");
		amount = scan.nextDouble();
		if (balance >= amount) {
			balance -= amount;
			System.out.println("New balance is: " + balance);
		} else {
			System.out.println("Insufficient funds are available");
		}
	}

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

	private void accountDetails() {
		System.out.println("Account Type: " + accountType);
		System.out.println("Balance: " + balance);
	}

}
