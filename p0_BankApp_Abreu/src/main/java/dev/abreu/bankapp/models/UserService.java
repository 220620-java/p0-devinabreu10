package dev.abreu.bankapp.models;
import dev.abreu.bankapp.data.UserDAO;
import dev.abreu.bankapp.data.UserSQL;
import dev.abreu.bankapp.exceptions.UsernameTakenException;

public class UserService {
	private UserDAO userDao = new UserSQL();
	
	public User registerUser(User user) throws UsernameTakenException{
		user = userDao.create(user);
		
		if(user == null) {
			throw new UsernameTakenException();
		}
		return user;
	}
	
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
