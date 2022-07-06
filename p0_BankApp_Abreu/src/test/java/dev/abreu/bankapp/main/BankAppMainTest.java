package dev.abreu.bankapp.main;


import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.TestInstance;
import org.junit.jupiter.api.TestInstance.Lifecycle;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.junit.jupiter.MockitoExtension;

import dev.abreu.bankapp.data.AccountDAO;
import dev.abreu.bankapp.data.UserDAO;
import dev.abreu.bankapp.models.AccountService;

@ExtendWith(MockitoExtension.class)
@TestInstance(Lifecycle.PER_CLASS)
public class BankAppMainTest {
	// object being tested
	@InjectMocks
	private AccountService accountService = new AccountService();
		
	@Mock
	private UserDAO userDao;
	@Mock
	private AccountDAO accountDao;
		
// Unit testing setup
	@Test
	public void testName() {
		// setup (inputs, mocks, etc.)
		// mock example: Mockito.when(userDao.findByUsername(username)).thenReturn(mockUser);
		
		
		// call method that we're testing
		
			
			
	    // assertion (checking for expected behavior)
				
	}
	
	

}
