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

@ExtendWith(MockitoExtension.class)
public class AccountServiceTest {
	// object being tested
	@InjectMocks
	private AccountService accountService = new AccountService();
		
	@Mock
	private UserDAO userDao;
	@Mock
	private AccountDAO accountDao;
		
	@Test
	public void successfullyOpenNewAccount() {
		// setup
		Account mockAccount = new Account();
		Mockito.when(accountDao.create(mockAccount)).thenReturn(mockAccount);	
			
		// call method
		Account returnedAccount = accountService.createNewAccount(mockAccount);	
			
		// assertion
		assertNotNull(returnedAccount);
			
	}
	
	@Test
	public void successfullyGetAccountDetails() {
		// setup (inputs, mocks, etc.)
		// mock example: Mockito.when(userDao.findByUsername(username)).thenReturn(mockUser);
		User mockUser = new User();
		Account mockAccount = new Account();
		
		Mockito.when(accountDao.getDetails(mockAccount, mockUser)).thenReturn(mockAccount);
			
		// call method that we're testing
		Account returnedAccount = accountService.accountDetails(mockAccount, mockUser);		
			
	    // assertion (checking for expected behavior)
		assertNotNull(returnedAccount);
				
	}
	
	@Test
	public void getAccountDetailsUnsuccessful() {
		// setup (inputs, mocks, etc.)
		// mock example: Mockito.when(userDao.findByUsername(username)).thenReturn(mockUser);
		User mockUser = new User();
		Account mockAccount = null;
		
		Mockito.when(accountDao.getDetails(mockAccount, mockUser)).thenReturn(mockAccount);
			
		// call method that we're testing
		Account returnedAccount = accountService.accountDetails(mockAccount, mockUser);		
			
	    // assertion (checking for expected behavior)
		assertNull(returnedAccount);
				
	}
	
	@Test
	public void successfulDeposit() {
		// testing to see if deposit() method is functioning properly
		Account mockAccount = new Account("Checking", 5.00);
		double amount = 5.00;
		double postDeposit = mockAccount.getBalance() + amount;
		
		//Mockito.when(accountDao.updateBalance(mockAccount)).thenReturn(mockAccount);
			
		// call method that we're testing
		Account returnedAccount = accountService.deposit(mockAccount, amount);		
			
	    // assertion (checking for expected behavior)
		assertEquals(postDeposit, returnedAccount.getBalance());		
	}
	
	@Test
	public void invalidDeposit() {
		// test to see what happens if user tries to deposit negative amount
		Account mockAccount = new Account("Checking", 5.00);
		double depositAmount = -1.00;
		double postDeposit = mockAccount.getBalance() + depositAmount;
		
		//Mockito.when(accountDao.updateBalance(mockAccount)).thenReturn(mockAccount);
			
		// call method that we're testing
		Account returnedAccount = accountService.deposit(mockAccount, depositAmount);		
			
	    // assertion (checking for expected behavior)
		assertNotEquals(postDeposit, returnedAccount.getBalance());		
	}
	
	@Test
	public void successfulWithdraw() {
		// testing to see if withdraw() method is functioning properly 
		Account mockAccount = new Account("Checking", 10.00);
		double amount = 5.00;
		double postWithdraw = mockAccount.getBalance() - amount;		
			
		// call method that we're testing
		Account returnedAccount = accountService.withdraw(mockAccount, amount);			
			
	    // assertion (checking for expected behavior)
		assertEquals(postWithdraw, returnedAccount.getBalance());		
	}
	
	@Test
	public void invalidWithdraw() {
		// test what happens if user tries to overdraft
		Account mockAccount = new Account("Checking", 10.00);
		double amount = 15.00;
		double postWithdraw = mockAccount.getBalance() - amount;		
			
		// call method that we're testing
		Account returnedAccount = accountService.withdraw(mockAccount, amount);			
			
	    // assertion (checking for expected behavior)
		assertNotEquals(postWithdraw, returnedAccount.getBalance());		
	}
	

}
