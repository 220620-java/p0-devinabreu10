package dev.abreu.bankapp.models;

import static org.junit.jupiter.api.Assertions.*;


import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.Mockito;
import org.mockito.junit.jupiter.MockitoExtension;

import dev.abreu.bankapp.data.AccountDAO;
import dev.abreu.bankapp.data.UserDAO;
import dev.abreu.bankapp.exceptions.UsernameTakenException;

@ExtendWith(MockitoExtension.class)
public class UserServiceTest {
	// object being tested
	@InjectMocks
	private UserService userService = new UserService();
	
	@Mock
	private UserDAO userDao;
	@Mock
	private AccountDAO accountDAO;
	
	@Test
	public void successfullySignIn() {
		// setup
		String username = "username_test";
		String password = "password_test";
		
		User mockUser = new User(username, password);
		Mockito.when(userDao.findByUsername(username)).thenReturn(mockUser);
		
	    // call method
		User returnedUser = userService.signIn(username, password);
		
	    // assertion
		assertEquals(username, returnedUser.getUsername());
	}
	
	@Test
	public void signInUsernameDoesntExist() {
		// setup (inputs, mocks, etc.)
		String username = "wrong_test";
		String password = "password_test";
		
		Mockito.when(userDao.findByUsername(username)).thenReturn(null);
		
		// call method that we're testing
		User returnedUser = userService.signIn(username, password);
		
	    // assertion (checking for expected behavior)
		assertNull(returnedUser);
	}
	
	@Test
	public void signInWrongPassword() {
		// setup (inputs, mocks, etc.)
		String username = "username_test";
		String password = "wrong_test";
		
		User mockUser = new User(username, "wrong");
		Mockito.when(userDao.findByUsername(username)).thenReturn(mockUser);
		
		// call method that we're testing
		User returnedUser = userService.signIn(username, password);
		
	    // assertion (checking for expected behavior)
		assertNull(returnedUser);
	}
	
	
	@Test
	public void successfullyRegisterNewUser() throws UsernameTakenException {
		// setup
		User mockUser = new User();
		Mockito.when(userDao.create(mockUser)).thenReturn(mockUser);
		
		// call method
		User returnedUser = userService.registerUser(mockUser);
		
		// assertion
		assertNotNull(returnedUser);
	}
	
	@Test
	public void registeringUsernameTaken() {
		User mockUser = new User();
		Mockito.when(userDao.create(mockUser)).thenReturn(null);
		
		assertThrows(UsernameTakenException.class, () -> {
			userService.registerUser(mockUser);
		});
	}
	
	
}
