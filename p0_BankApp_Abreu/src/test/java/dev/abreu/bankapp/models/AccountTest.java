package dev.abreu.bankapp.models;

import static org.junit.jupiter.api.Assertions.assertEquals;

import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.TestInstance;
import org.junit.jupiter.api.TestInstance.Lifecycle;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.junit.jupiter.MockitoExtension;

import dev.abreu.bankapp.data.AccountDAO;
import dev.abreu.bankapp.data.UserDAO;

@ExtendWith(MockitoExtension.class)
@TestInstance(Lifecycle.PER_CLASS)
public class AccountTest {
	// object being tested
	@InjectMocks
	private AccountService accountService = new AccountService();
		
	@Mock
	private UserDAO userDao;
	@Mock
	private AccountDAO accountDao;

	@Test
	public void accountInstantiation() {
		// test if the account is instantinated correctly
		Account acc = new Account (
			"Checking",
			25.00
			);
		assertEquals(-1, acc.getAccountID());
		assertEquals("Checking", acc.getAccountType());
		assertEquals(25.00, acc.getInitialDeposit());
		assertEquals(25.00, acc.getBalance());
	}
	
	
	
	@Test
	public void testName() {
		// setup (inputs, mocks, etc.)
		// mock example: Mockito.when(userDao.findByUsername(username)).thenReturn(mockUser);
		
		
		// call method that we're testing
		
			
			
	    // assertion (checking for expected behavior)
				
	}
	
	

}
