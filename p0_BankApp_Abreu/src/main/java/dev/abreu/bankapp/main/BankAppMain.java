package dev.abreu.bankapp.main;

/**
 * Project 0 for Revature
 * 
 * @author Devin Abreu
 */

import java.util.Scanner;
import java.text.NumberFormat;
import java.util.Currency;
import java.util.Locale;

import dev.abreu.bankapp.ds.List;
import dev.abreu.bankapp.exceptions.UsernameTakenException;
import dev.abreu.bankapp.models.Account;
import dev.abreu.bankapp.models.AccountService;
import dev.abreu.bankapp.models.User;
import dev.abreu.bankapp.models.UserService;

/*As a user, I can:
 - (DONE) register a new user account with the system (must be secured with a password)
 - (DONE) login with my existing credentials
 - (DONE) create at least one account
 - (DONE) deposit funds into an account (use doubles, not ints)
 - (DONE) withdraw funds from an account (no overdrafting!)
 - (DONE) view the balance of my account(s) 
  (all balance displays must be in proper currency format)*/

public class BankAppMain { // This is the Driver Class that connects everything
	// Create instances that will be used throughout BankAppMain
	public static User user = new User();
	public static Account account = new Account();
	
	// Allows for formatting of account balance output
	private static Locale usa = new Locale("en", "US");
	Currency dollars = Currency.getInstance(usa);
	private static NumberFormat dollarFormat = NumberFormat.getCurrencyInstance(usa);
	
	private static Scanner scan = new Scanner(System.in);
	private static UserService userService = new UserService();
	private static AccountService accService = new AccountService();

	public static void main(String[] args) {
		boolean usingBankApp = true;
		
		System.out.println("------------------------------------------");
		System.out.println("Welcome to Devin Abreu's Bank Application!");
		System.out.println("------------------------------------------\n");
		
		User user = null;
		
		// Login Screen
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
				System.out.println("\nWelcome Back, " + user.getUserFirstName());
				System.out.println("1. View Account Details\n" 
								 + "2. Deposit Funds\n" 
								 + "3. Withdraw Funds\n"
								 + "4. View All Accounts\n"
								 + "5. Open New Account\n"
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
					viewAccounts(user);
					break;
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
  } // end of public main
	
	
	/**
	 * Retrieves user with specified username if both
	 * username and password match in the system
	 * 
	 * @return User that successfully signed in
	 */
	private static User userLogin() {
		boolean signingIn = true;
		
		while(signingIn) {
			System.out.println("\nEnter Username: ");
			String username = scan.nextLine();
		
			System.out.println("Enter Password: ");
			String password = scan.nextLine();
			
			// verify login info from database (return null if no user)
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

	/**
	 * Registers new user and opens account for user
	 */
	private static void registerNewUser() {
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
					userService.registerUser(user);
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
	
	/**
	 * Opening new account after already being registered as a user
	 * 
	 * @param user opening new account
	 * @return null as account creation is handled by AccountService class
	 */
	private static User openNewAccount(User user) {
		
		boolean openingAccount = true;
		
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
				scan.nextLine(); // refresh buffer
				
				Account newAccount = new Account(accountType, initDeposit);
				newAccount.setUserID(user.getId());
				accService.createNewAccount(newAccount);
				
				System.out.println("Successfully created new " + accountType + " account");
		
				openingAccount = false;
				}
		
		return null;
		}
	
	/**
	 * Retrieve account and user IDs along with
	 * account type and current balance
	 * 
	 * @param account being accessed
	 * @param user who's account is being accessed
	 * @return Account
	 */
	private static Account accountDetails(Account account, User user) {
		accService.accountDetails(account, user);
		System.out.println("\n----Account Details----");
		System.out.println("Account ID: " + account.getAccountID());
		System.out.println("User ID: " + user.getId());
		System.out.println("Account Type: " + account.getAccountType());
		System.out.println("Account Balance: " + dollarFormat.format(account.getBalance()));
		return account;
	}
	
	/**
	 * Deposit funds into user's bank account
	 * 
	 * @param account being deposited into
	 * @param user who controls account
	 * @return Account
	 */
	private static Account deposit(Account account, User user) {
		accService.accountDetails(account, user);
		System.out.println("Enter amount to deposit: ");
		double depositAmount = scan.nextDouble();
		scan.nextLine(); // need to refresh the buffer
		accService.deposit(account, depositAmount);
		System.out.println("\nNew Account Balance is: " + dollarFormat.format(account.getBalance()));
		return account;
	}
	
	/**
	 * Withdraw funds from user's bank account
	 * 
	 * @param account being withdrawn from
	 * @param user who controls account
	 * @return Account
	 */
	private static Account withdraw(Account account, User user) {
		accService.accountDetails(account, user);
		System.out.println("Enter amount to withdraw: ");
		double withdrawAmount = scan.nextDouble();
		scan.nextLine(); // need to refresh the buffer 
		accService.withdraw(account, withdrawAmount);
		System.out.println("\nNew Account Balance is: " + dollarFormat.format(account.getBalance()));
		return account;
	}
	
	private static User viewAccounts(User user) {

		List<Account> accounts = accService.getAccountList(user);
		System.out.println("List of available Accounts:\n");
		for(int i = 0; i < accounts.size(); i++) {
			System.out.println("Account Type: " + accounts.get(i).getAccountType());
			System.out.println("Balance: " + dollarFormat.format(accounts.get(i).getBalance()));
			System.out.println("Account ID: " + accounts.get(i).getAccountID());
			System.out.println();
		}
		
		
		
		return user;

	}
	
	
} // end of BankAppMain
	
	
	
	
	
