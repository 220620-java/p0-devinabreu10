package dev.abreu.bankapp.data;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;

import dev.abreu.bankapp.ds.List;
import dev.abreu.bankapp.models.Account;
import dev.abreu.bankapp.models.User;
import dev.abreu.bankapp.utils.ConnectionUtil;

public class AccountSQL implements AccountDAO {
	private ConnectionUtil connUtil = ConnectionUtil.getConnectionUtil();

	@Override
	public Account create(Account account) {
		
		try (Connection conn = connUtil.getConnection()){
			conn.setAutoCommit(false); // setting auto commit to false
			
			String sql = "INSERT into accounts "
							+ "(account_id, account_type, account_balance, user_id)"
							+ "values (default, ?, ?, ?)";
			// when inserting we want to retrieve the ID that was generated
			// so we need to specify which column(s) are autogenerated
			String[] keys = {"account_id"};
			
			PreparedStatement stmt = conn.prepareStatement(sql, keys);
			stmt.setString(1, account.getAccountType());
			stmt.setDouble(2, account.getBalance());
			stmt.setInt(3, account.getUserID());
			
			int rowsAffected = stmt.executeUpdate();
			ResultSet resultSet = stmt.getGeneratedKeys();
			
			if(resultSet.next() && rowsAffected == 1) {
				account.setAccountID(resultSet.getInt("account_id"));
				conn.commit();
			}
			else {
				conn.rollback();
				return null;
			}

		} catch (SQLException e) {
			e.printStackTrace();
		}

		return account;
	}
	
	public void updateBalance(Account account) {
			try (Connection conn = connUtil.getConnection()){
				conn.setAutoCommit(false); // setting auto commit to false
				
				String sql = "UPDATE accounts "
								+ "set account_balance = ? "
								+ "where account_id = ?";
				
				PreparedStatement stmt = conn.prepareStatement(sql);
				stmt.setDouble(1, account.getBalance());
				stmt.setInt(2, account.getAccountID());
				
				int rowsAffected = stmt.executeUpdate();
				
				if(rowsAffected <= 1) {
					conn.commit();
				}
				else {
					conn.rollback();
				}

			} catch (SQLException e) {
				e.printStackTrace();
			}
			
		}
	
	public Account getDetails(Account acc, User user) {
		try (Connection conn = connUtil.getConnection()){
			conn.setAutoCommit(false); // setting auto commit to false
			
			String sql = "SELECT * FROM accounts WHERE user_id = ?";
			
			PreparedStatement stmt = conn.prepareStatement(sql);
			stmt.setInt(1, user.getId());
			
			ResultSet resultSet = stmt.executeQuery();
			
			if(resultSet.next()) {
				int accountID = resultSet.getInt("account_id");
				String accountType = resultSet.getString("account_type");
				double accBalance = resultSet.getDouble("account_balance");
				
				acc.setAccountID(accountID);
				acc.setAccountType(accountType);
				acc.setBalance(accBalance);
			}
			
		} catch (SQLException e) {
			e.printStackTrace();
		}
		
		return acc;
	}

	@Override
	public List<Account> findAll() {
		// TODO Auto-generated method stub
		return null;
	}

	@Override
	public void update(Account acc) {
		
	}

	@Override
	public void delete(Account t) {
		// TODO Auto-generated method stub
		
	}

	@Override
	public Account findByUserID(int userID) {
	
		
		return null;
	}	
	
	

}
