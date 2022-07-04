package dev.abreu.bankapp.models;

import dev.abreu.bankapp.data.AccountDAO;
import dev.abreu.bankapp.data.AccountSQL;

public class AccountService {
	private AccountDAO accDao = new AccountSQL();
	
	public Account createNewAccount(Account acc) {
		acc = accDao.create(acc);
		
		if(acc == null) {
			System.out.println("Account can't be created");
		}
		return acc;
	}
	
	public Account accountDetails(Account acc, User user) {
		accDao.getDetails(acc, user);
		
		return acc;
	}
	
	public Account deposit(Account acc, double amount) { // no negative deposits
		
		if(amount >= 0) {
			double deposit = acc.getBalance() + amount;
			acc.setBalance(deposit);
			accDao.updateBalance(acc);
			System.out.println("Deposit Successful!");
		}
		else {
			System.out.println("Invalid deposit amount, please try again");
		}
		
		return acc;
	}

	public Account withdraw(Account acc, double amount) { // no overdrafts
		
		if(acc.getBalance() >= amount) {
			double withdraw = acc.getBalance() - amount;
			acc.setBalance(withdraw);
			accDao.updateBalance(acc);
			System.out.println("Withdraw Successful!");
		}
		else {
			System.out.println("Insufficient funds are available");
		}

		return acc;
	}
	

}
