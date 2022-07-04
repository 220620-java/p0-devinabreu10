package dev.abreu.bankapp.main;


import java.util.Scanner;

import dev.abreu.bankapp.data.AccountDAO;
import dev.abreu.bankapp.data.AccountSQL;
import dev.abreu.bankapp.exceptions.UsernameTakenException;
import dev.abreu.bankapp.models.Account;
import dev.abreu.bankapp.models.AccountService;
import dev.abreu.bankapp.models.User;
import dev.abreu.bankapp.models.UserService;

/*As a user, I can:
 - (DONE) register a new user account with the system (must be secured with a password)
 - (DONE) login with my existing credentials
 - (DONE) create at least one account
 - deposit funds into an account (use doubles, not ints)
 - withdraw funds from an account (no overdrafting!)
 - (DONE) view the balance of my account(s) 
  (all balance displays must be in proper currency format)*/

public class BankAppMain {

	// This is the Driver Class that connects everything
	
	public static User user = new User();
	public static Account account = new Account();

	private static Scanner scan = new Scanner(System.in);
	private static UserService userService = new UserService();
	private static AccountService accService = new AccountService();

	public static void main(String[] args) {
		boolean usingBankApp = true;
		
		System.out.println("------------------------------------------");
		System.out.println("Welcome to Devin Abreu's Bank Application!");
		System.out.println("------------------------------------------");
		
		User user = null;
		
		// Main Login Screen
		while (usingBankApp) {
			if (user == null) { // if user doesn't exist
				System.out.println("What would you like to do:\n"
									+ "1. Sign In\n" 
									+ "2. Register (New User)\n"
									+ "3. Exit");
				
				String input = scan.nextLine();
				
				switch(input) {
				case "1":
					user = userLogin();
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
					accountDetails(account, user);
					break;
				case "2":
					deposit(account, user);
					break;
				case "3":
					withdraw(account, user);
					break;
				case "4":
					//TODO transferFunds();
				case "5":
					openNewAccount(user);
					break;
				default:
					System.out.println("Signing Out...");
					user = null;
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
		
			System.out.println("Enter Password: ");
			String password = scan.nextLine();
			
			//user.retrieveLoginInfo(user); // verify login info from database (return null if no user)
			User user = userService.signIn(username, password);
			
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
			
			System.out.println("Type \"y\" to confirm\n"
							 + "Type \"n\" to try again\n"
							 + "Press any other key to return to main menu");
			String confirm = scan.nextLine().toLowerCase();
			
			switch(confirm) {
			case "y":
				// the account type being passed here is the users initial account 
				Account acc = new Account(accountType, 0.00);
				User user = new User(userFirstName, userLastName, username, password, acc);
				
				try {
					userService.registerUser(user); // need to link this to database
					acc.setUserID(user.getId());
					accService.createNewAccount(acc);
					
					registeringUser = false;
					System.out.println("You have successfully been registered!");
				} catch (UsernameTakenException e) {
					System.out.println("That Username already exists! Please Try Again");
				}
				break;
			case "n":
				// System.out.println("Please enter your information again:");
				break;
			default:
				System.out.println("Returning to Main Menu...");
				registeringUser = false;
			}
		}
		
	}
	
	// Opening new account after alredy being registered as a user
	private static User openNewAccount(User user) { // insert new account for exisiting user
		
		boolean openingAccount = true;
		//boolean validFormat = true;
		
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
					System.out.println("Checking account is being created");
				}
				
				System.out.println("Please enter your initial deposit amount: (format X.XX)");
				double initDeposit = scan.nextDouble();
				
				Account newAccount = new Account(accountType, initDeposit);
				newAccount.setUserID(user.getId());
				accService.createNewAccount(newAccount);
				
				System.out.println("Successfully created new " + accountType + " account");
		
				openingAccount = false;
				}
		
		return null;
		}
	
	private static Account accountDetails(Account account, User user) {
		accService.accountDetails(account, user);
		System.out.println("\nAccount ID: " + account.getAccountID());
		System.out.println("User ID: " + user.getId());
		System.out.println("Account Type: " + account.getAccountType());
		System.out.println("Account Balance: $" + account.getBalance() + "\n");
		return account;
	}
	
	private static Account deposit(Account account, User user) {
		accService.accountDetails(account, user);
		System.out.println("Enter amount to deposit: ");
		double depositAmount = scan.nextDouble();
		accService.deposit(account, depositAmount);
		System.out.println("\nNew Account Balance is: " + account.getBalance());
		return account;
	}
	
	private static Account withdraw(Account account, User user) {
		accService.accountDetails(account, user);
		System.out.println("Enter amount to withdraw: ");
		double withdrawAmount = scan.nextDouble();
		accService.withdraw(account, withdrawAmount);
		System.out.println("\nNew Account Balance is: " + account.getBalance());
		return account;
	}
	
	
	
		
} // end of BankAppMain
	
	
	
	
	
