package dev.abreu.bankapp.models;

import static org.junit.jupiter.api.Assertions.*;

import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.TestInstance;
import org.junit.jupiter.api.TestInstance.Lifecycle;
import org.junit.jupiter.api.extension.ExtendWith;

import org.mockito.junit.jupiter.MockitoExtension;


@ExtendWith(MockitoExtension.class)
@TestInstance(Lifecycle.PER_CLASS)
public class UserTest {
	// object being tested
		
	@Test
	public void userInstantiation() {
		// test if the user is instantinated correctly
		Account mockAccount = new Account();
		User user = new User (
			"test_first",
			"test_last",
			"test_user",
			"test_pass",
			mockAccount
			);
		assertEquals(0, user.getId());
		assertEquals("test_first", user.getUserFirstName());
		assertEquals("test_last", user.getUserLastName());
		assertEquals("test_user", user.getUsername());
		assertEquals("test_pass", user.getPassword());
		assertEquals(mockAccount, user.getAccount());
	}
	
	
				
	
	@Test
	public void testName() {
		// setup (inputs, mocks, etc.)
		// mock example: Mockito.when(userDao.findByUsername(username)).thenReturn(mockUser);
		
		
		// call method that we're testing
		
			
			
	    // assertion (checking for expected behavior)
				
	}
	

}
