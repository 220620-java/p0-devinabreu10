package dev.abreu.bankapp.data;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;

import dev.abreu.bankapp.models.User;
import dev.abreu.bankapp.utils.ConnectionUtil;

public class UserSQL implements UserDAO {
	private ConnectionUtil connUtil = ConnectionUtil.getConnectionUtil();
	
	@Override
	public User create(User user) {
		try (Connection conn = connUtil.getConnection()){
			conn.setAutoCommit(false); // setting auto commit to false
			
			String sql = "INSERT into users "
							+ "(user_id, first_name, last_name, username, passwrd)"
							+ "values (default, ?, ?, ?, ?)";
			// when inserting we want to retrieve the ID that was generated
			// so we need to specify which column(s) are autogenerated
			String[] keys = {"user_id"};
			
			PreparedStatement stmt = conn.prepareStatement(sql, keys);
			stmt.setString(1, user.getUserFirstName());
			stmt.setString(2, user.getUserLastName());
			stmt.setString(3, user.getUsername());
			stmt.setString(4, user.getPassword());
			
			int rowsAffected = stmt.executeUpdate();
			ResultSet resultSet = stmt.getGeneratedKeys();
			
			if(resultSet.next() && rowsAffected == 1) {
				user.setId(resultSet.getInt("user_id"));
				conn.commit();
			}
			else {
				conn.rollback();
				return null;
			}

		} catch (SQLException e) {
			e.printStackTrace();
		}

		return user;
	}



	@Override
	public void update(User user) {
		// try-with-resources automatically closes connection
		try (Connection conn = connUtil.getConnection()){
			conn.setAutoCommit(false);
			// set up the SQL statement to be executed
			String sql = "update users "
							+ "set username = ?, "
							+ "passwrd = ?,"
							+ "where user_id = ?";
			
			PreparedStatement stmt = conn.prepareStatement(sql);
			stmt.setString(1, user.getUsername()); // these statements correspond to ?'s
			stmt.setString(2, user.getPassword());
			stmt.setInt(3, user.getId());
					
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
	public void delete(User user) {
		try (Connection conn = connUtil.getConnection()){
			conn.setAutoCommit(false);
			// set up the SQL statement to be executed
			String sql = "delete from users where user_id = ?";
			
			PreparedStatement stmt = conn.prepareStatement(sql);
			stmt.setInt(1, user.getId());
					
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
	public User findByUsername(String username) {
		User user = new User();
		
		try (Connection conn = connUtil.getConnection()){
			//conn.setAutoCommit(false); // setting auto commit to false
			
			String sql = "SELECT * FROM users WHERE username = ?";
			
			PreparedStatement stmt = conn.prepareStatement(sql);
			stmt.setString(1, username);
			
			ResultSet resultSet = stmt.executeQuery();
			
			if(resultSet.next()) {
				user.setId(resultSet.getInt("user_id"));
				user.setUserFirstName(resultSet.getString("first_name"));
				user.setUserLastName(resultSet.getString("last_name"));
				user.setUsername(resultSet.getString("first_name"));
				user.setPassword(resultSet.getString("passwrd"));
				//conn.commit();
			}
			conn.close();
			
		} catch (SQLException e) {
			e.printStackTrace();
		}

		return user;
	}
}
