package dev.abreu.bankapp.models;

import dev.abreu.bankapp.data.AccountDAO;
import dev.abreu.bankapp.data.AccountSQL;
import dev.abreu.bankapp.ds.List;

public class AccountService {
	// allows for connecting Account objects to database
	private AccountDAO accDao = new AccountSQL();
	
	/**
	 * Used to open a new account for a User which can open
	 * multiple accounts
	 * 
	 * @param account the account added to the database 
	 * @return Account returns newly opened account
	 */
	public Account createNewAccount(Account account) {
		account = accDao.create(account);
		
		if(account == null) {
			System.out.println("Account can't be created");
		}
		return account;
	}
	
	/**
	 * User is able to retrieve details from accountincluding 
	 * account type and current balance
	 * 
	 * @param account specific account from User to be accessed
	 * @param user User whose account is being accessed
	 * @return Account returns account with retrieved account details 
	 */
	public Account accountDetails(Account account, User user) {
		accDao.getDetails(account, user);
		
		return account;
	}
	
	/**
	 * Makes deposit of funds into user's account which
	 * is then updated in the database. Cannot deposit negative balance 
	 * 
	 * @param account the account being deposited into
	 * @param amount the amount of money being deposited
	 * @return Account returns account after the deposit
	 */
	public Account deposit(Account account, double amount) { // no negative deposits
		
		if(amount >= 0) {
			double deposit = account.getBalance() + amount;
			account.setBalance(deposit);
			accDao.updateBalance(account);
			System.out.println("Deposit Successful!");
		}
		else {
			System.out.println("Invalid deposit amount, please try again");
		}
		
		return account;
	}
	
	/**
	 * Withdraws funds from user's account which is then
	 * updated in the database. No overdrafts are allowed
	 * 
	 * @param account the account being withdrawn from
	 * @param amount the amount of money being withdrawn
	 * @return Account returns account after the withdraw
	 */
	public Account withdraw(Account account, double amount) { // no overdrafts
		
		if(account.getBalance() >= amount) {
			double withdraw = account.getBalance() - amount;
			account.setBalance(withdraw);
			accDao.updateBalance(account);
			System.out.println("Withdraw Successful!");
		}
		else {
			System.out.println("Insufficient funds are available");
		}

		return account;
	}
	
	/**
	 * returns a list of accounts for specific user
	 * 
	 * @param user specifies which user's accounts are being listed
	 * @return list of all accounts
	 */
	public List<Account> getAccountList(User user) {
		return accDao.findAllAccounts(user);
	}
	

}
