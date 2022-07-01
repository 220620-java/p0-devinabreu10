package dev.abreu.bankapp.data;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.SQLException;

import dev.abreu.bankapp.ds.List;
import dev.abreu.bankapp.models.User;
import dev.abreu.bankapp.utils.ConnectionUtil;

public class UserSQL implements UserDA {
	private ConnectionUtil connUtil = ConnectionUtil.getConnectionUtil();
	
	@Override
	public User create(User t) {
		// TODO Auto-generated method stub
		return null;
	}

	@Override
	public List<User> findAll() {
		// TODO Auto-generated method stub
		return null;
	}

	@Override
	public void update(User t) {
		// try-with-resources automatically closes connection
		try (Connection conn = connUtil.getConnection()){
			conn.setAutoCommit(false);
			// set up the SQL statement to be executed
			String sql = "update user "
							+ "set name=?, "
							+ "account_type=?,"
							+ "";
			
			PreparedStatement stmt = conn.prepareStatement(sql);
			stmt.setString(1, t.getUsername()); // these statements correspond to ?s
			stmt.setString(2, t.getAccountType());
					
			int rowsAffected = stmt.executeUpdate();
			if (rowsAffected <= 1) {
				conn.commit();
			} else {
				conn.rollback();
			}
	
		} catch (SQLException e) {
			e.printStackTrace();
		}
		
	}

	@Override
	public void delete(User t) {
		// TODO Auto-generated method stub
		
	}

	@Override
	public User findByUsername(String username) {
		// TODO Auto-generated method stub
		return null;
	}

}
