package dev.abreu.bankapp.data;

import dev.abreu.bankapp.models.Account;
import dev.abreu.bankapp.models.User;

public interface AccountDAO extends DataAccessObject<Account> {
	public Account findByUserID(int userID);
	public void updateBalance(Account account);
	public Account getDetails(Account acc, User user);
	//public Account deposit(Account acc, double amount);
	//public Account withdraw(Account acc, double amount);
	

}
