package dev.abreu.bankapp.models;

import dev.abreu.bankapp.data.UserDAO;
import dev.abreu.bankapp.data.UserSQL;
import dev.abreu.bankapp.exceptions.UsernameTakenException;

public class UserService {
	// allows for connecting User objects to database
	private UserDAO userDao = new UserSQL();
	
	/**
	 * Registers new user into the system if they do
	 * not already own an account
	 * 
	 * @param user new User being added to the database
	 * @return User returns successfully registered user or throws exception
	 * @throws UsernameTakenException
	 */
	public User registerUser(User user) throws UsernameTakenException{
		user = userDao.create(user);
		
		if(user == null) {
			throw new UsernameTakenException();
		}
		return user;
	}
	
	/**
	 * User that has been registered into the system can
	 * access their account using username and password
	 * 
	 * @param username user's username that has been registered
	 * @param password user's password that has been registered
	 * @return returns user if login is successful, otherwise null
	 */
	public User signIn(String username, String password) {
		User user = userDao.findByUsername(username);
		
		if(user != null && (password != null && password.equals(user.getPassword()))) {
			return user;
		}
		else {
			return null;
		}
	}


}
