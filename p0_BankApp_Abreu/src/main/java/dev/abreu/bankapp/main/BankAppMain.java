package dev.abreu.bankapp.main;


import java.util.Scanner;

import dev.abreu.bankapp.exceptions.UsernameTakenException;
import dev.abreu.bankapp.models.Account;
import dev.abreu.bankapp.models.User;

/*As a user, I can:
 - register a new user account with the system (must be secured with a password)
 - login with my existing credentials
 - create at least one account
 - deposit funds into an account (use doubles, not ints)
 - withdraw funds from an account (no overdrafting!)
 - view the balance of my account(s) 
  (all balance displays must be in proper currency format)*/

public class BankAppMain {

	// This is the Driver Class that connects everything
	
	public static User user = new User();

	private static Scanner scan = new Scanner(System.in);
	// private static UserService userService;

	public static void main(String[] args) {
		boolean usingBankApp = true;
		//boolean userHasAccount = true;
		
		//RegisterUser user = new RegisterUser();
		
		System.out.println("------------------------------------------");
		System.out.println("Welcome to Devin Abreu's Bank Application!");
		System.out.println("------------------------------------------");
		
		User user = null;
		
		// Main Login Screen
		while (usingBankApp) {
			if (user == null) { // if user doesn't exist
				System.out.println("What would you like to do\n"
									+ "1. Sign In\n" 
									+ "2. Register (New User)\n"
									+ "3. Exit");
				
				String input = scan.nextLine();
				
				switch(input) {
				case "1":
					userLogin();
					break;
				case "2":
					registerNewUser();
					break;
				default:
					usingBankApp = false;
					System.out.println("Thank you for visiting Devin Abreu's Bank Application");
				}
			}
			
			// Main Menu Screen (after signing in)
			if(user != null) {
				System.out.println("Welcome Back, " + user.getUserFirstName());
				System.out.println("1. View Account Details\n" 
								 + "2. Deposit Funds\n" 
								 + "3. Withdraw Funds\n"
								 + "4. Transfer Funds\n"
								 + "5. Open New Account\n" // think about wording for this option
								 + "6. Sign Out");
				
				String input = scan.nextLine();
				
				switch(input) {
				case "1":
					//TODO accountDetails();
					break;
				case "2":
					//TODO deposit();
					break;
				case "3":
					//TODO withdraw();
					break;
				case "4":
					//TODO transferFunds();
				case "5":
					openNewAccount(); //TODO
					break;
				default:
					System.out.println("Signing Out...");
			}
		}		
	}
	scan.close();
  } //end of public main

	
	private static User userLogin() { // connect to database and verify username and password
		boolean signingIn = true;
		
		while(signingIn) {
			System.out.println("Enter Username: ");
			String username = scan.nextLine();
			
			user.setUsername(username);
			
			System.out.println("Enter Password: ");
			String password = scan.nextLine();
			
			user.setPassword(password);
			
			user.retrieveLoginInfo(user); // verify login info from database (return null if no user)
			// take userService approach if you want
			
			if(user == null) {
				System.out.println("Invalid Credentials!");
				signingIn = false;
			}
			else {
				return user;
			}
		
		}
		return null;
	}
	
	// Registering new user and one new account
	private static void registerNewUser() { // insert new user into database with one new account
		boolean registeringUser = true;
		
		while(registeringUser) {
			System.out.println("Please enter your information below:");

			System.out.println("Enter First Name: ");
			String userFirstName = scan.nextLine();
			
			System.out.println("Enter Last Name: ");
			String userLastName = scan.nextLine();
			
			System.out.println("Enter Username: ");
			String username = scan.nextLine();
			
			System.out.println("Enter Password: ");
			String password = scan.nextLine();
			
			String accountType = "";
			System.out.println("What type of account are you opening?\n"
					            + "1. Checking Account (Default)\n"
					            + "2. Savings Account");
			String type = scan.nextLine();
			
				switch(type) {
				case "1":
					accountType = "Checking";
					break;
				case "2":
					accountType = "Savings";
					break;
				default:
					accountType = "Checking"; // if 1 or 2 is not chosen a checking is provided
				}
			
			System.out.println("Type \"y\" to confirm"
							 + "Type \"n\" to try again"
							 + "Press any other key to return to main menu");
			String confirm = scan.nextLine().toLowerCase();
			
			switch(confirm) {
			case "y":
				// the account type being passed here is the users initial account 
				User user = new User(userFirstName, userLastName, username, password, accountType);
				
				try {
					user.registerUser(user); // need to link this to database
					registeringUser = false;
					System.out.println("You have successfully been registered!");
				} catch (UsernameTakenException e) {
					System.out.println("That Username already exists! Please Try Again");
				}
				break;
			case "n":
				System.out.println("Please enter your information again:");
				break;
			default:
				System.out.println("Returning to Main Menu...");
				registeringUser = false;
			}
		}
		
	}
	
	// Opening new account after alredy being registered as a user
	private static void openNewAccount() { // insert new account for exisiting user
		boolean openingAccount = true;
		boolean validFormat = true;
		
		while(openingAccount) {
			String accountType = "";
			System.out.println("What type of account are you opening?\n"
					            + "1. Checking Account (Default)\n"
					            + "2. Savings Account");
			String type = scan.nextLine();
			
				switch(type) {
				case "1":
					accountType = "Checking";
					break;
				case "2":
					accountType = "Savings";
					break;
				default:
					accountType = "Checking"; // if 1 or 2 is not chosen a checking is provided
				}
				
				System.out.println("Please enter your initial deposit amount: (format X.XX)");
				while(validFormat) {
					if(scan.hasNextDouble()) {
						double initialDeposit = scan.nextDouble();
						Account newAccount = new Account(accountType, initialDeposit);
						validFormat = false;
					}
					else {
						System.out.println("Please enter an amount in the following format: X.XX");
					}
				}
				//Account newAccount = new Account(accountType, initialDeposit);
		}
		
	}
	
	
	
	
	
	
}
