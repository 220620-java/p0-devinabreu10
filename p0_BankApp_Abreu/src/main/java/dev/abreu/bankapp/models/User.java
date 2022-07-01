package dev.abreu.bankapp.models;

import java.util.Objects;

import dev.abreu.bankapp.exceptions.UsernameTakenException;

public class User {
	private int id;
	private String userFirstName;
	private String userLastName;
	private String username;
	private String password;
	private String accountType;
	
	public User() {
		//super();
		this.id = 0;
		this.userFirstName = "";
		this.userLastName = "";
		this.username = "";
		this.password = "";
		this.accountType = "";
	}
	
	public User(String firstName, String lastName, String username, String password, String accType) {
		//super();
		this.id = 0;
		this.userFirstName = firstName;
		this.userLastName = lastName;
		this.username = username;
		this.password = password;
		this.accountType = accType;
	}
	
	public User retrieveLoginInfo(User user) {
		// going to return the user who's username and password matches
		// or return null if no user exists
		//TODO retrieve login info from SQL
		return null;
	}
	
	public User registerUser(User user) throws UsernameTakenException {
		// return the newly registered user
		// or return null otherwise
		//TODO add new user to database
		return null;
	}
	

	public int getId() {
		return id;
	}

	public void setId(int id) {
		this.id = id;
	}

	public String getUserFirstName() {
		return userFirstName;
	}

	public void setUserFirstName(String userFirstName) {
		this.userFirstName = userFirstName;
	}

	public String getUserLastName() {
		return userLastName;
	}

	public void setUserLastName(String userLastName) {
		this.userLastName = userLastName;
	}

	public String getUsername() {
		return username;
	}

	public void setUsername(String username) {
		this.username = username;
	}

	public String getPassword() {
		return password;
	}

	public void setPassword(String password) {
		this.password = password;
	}

	public String getAccountType() {
		return accountType;
	}

	public void setAccountType(String accountType) {
		this.accountType = accountType;
	}

	@Override
	public int hashCode() {
		return Objects.hash(accountType, id, password, userFirstName, userLastName, username);
	}

	@Override
	public boolean equals(Object obj) {
		if (this == obj)
			return true;
		if (obj == null)
			return false;
		if (getClass() != obj.getClass())
			return false;
		User other = (User) obj;
		return Objects.equals(accountType, other.accountType) && id == other.id
				&& Objects.equals(password, other.password) && Objects.equals(userFirstName, other.userFirstName)
				&& Objects.equals(userLastName, other.userLastName) && Objects.equals(username, other.username);
	}

	@Override
	public String toString() {
		return "User [id=" + id + ", userFirstName=" + userFirstName + ", userLastName=" + userLastName + ", username="
				+ username + ", password=" + password + ", accountType=" + accountType + "]";
	}
	
	
}
