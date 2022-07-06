package dev.abreu.bankapp.data;

import dev.abreu.bankapp.ds.List;
import dev.abreu.bankapp.models.Account;
import dev.abreu.bankapp.models.User;

public interface AccountDAO extends DataAccessObject<Account> {
	
	/**
	 * updates account balance in database
	 * 
	 * @param account whose balance is being updated
	 */
	public void updateBalance(Account account);
	
	/**
	 * Retrieve account details from database including
	 * account id, user id and current balance
	 * 
	 * @param account whose info is being retrieved
	 * @param user associated with said account
	 * @return Account
	 */
	public Account getDetails(Account account, User user);
	
	/**
	 * retrieve a list of all the accounts under a specific user
	 * 
	 * @param user 
	 * @return returns list of all accounts for the user
	 */
	public List<Account> findAllAccounts(User user);
	

}
