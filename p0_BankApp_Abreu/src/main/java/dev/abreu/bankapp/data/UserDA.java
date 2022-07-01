package dev.abreu.bankapp.data;

import dev.abreu.bankapp.models.User;

public interface UserDA extends DataAccess<User> {
	public User findByUsername(String username);

}
