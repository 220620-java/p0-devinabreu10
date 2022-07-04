package dev.abreu.bankapp.models;


public class User {
	private int id;
	private String userFirstName;
	private String userLastName;
	private String username;
	private String password;
	//private Account account;
	
	public User() {
		//super();
		this.id = 0;
		this.userFirstName = "";
		this.userLastName = "";
		this.username = "";
		this.password = "";
		//this.account = new Account();
	}
	
	public User(String firstName, String lastName, String username, String password, Account account) {
		//super();
		this.id = 0;
		this.userFirstName = firstName;
		this.userLastName = lastName;
		this.username = username;
		this.password = password;
		//this.account = new Account();
	}
	
//	public User retrieveLoginInfo(User user) {
//		// going to return the user who's username and password matches
//		// or return null if no user exists
//		//TODO retrieve login info from SQL
//		return null;
//	}
//	
//	public User registerUser(User user) throws UsernameTakenException {
//		// return the newly registered user
//		// or return null otherwise
//		//TODO add new user to database
//		return null;
//	}
	

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

	
	
	
}
