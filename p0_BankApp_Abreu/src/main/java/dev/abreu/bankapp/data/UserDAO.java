package dev.abreu.bankapp.data;

import dev.abreu.bankapp.models.User;

public interface UserDAO extends DataAccessObject<User> {
	public User findByUsername(String username);

}
