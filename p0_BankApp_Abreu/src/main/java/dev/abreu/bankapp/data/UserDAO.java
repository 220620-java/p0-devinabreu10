package dev.abreu.bankapp.data;

import dev.abreu.bankapp.models.User;

public interface UserDAO extends DataAccessObject<User> {
	
	/**
	 * Retrieves user associated with provided username if valid
	 * 
	 * @param username being searched
	 * @return User associated with username
	 */
	public User findByUsername(String username);

}
