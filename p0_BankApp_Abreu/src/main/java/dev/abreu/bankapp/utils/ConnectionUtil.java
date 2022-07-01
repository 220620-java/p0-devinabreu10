package dev.abreu.bankapp.utils;

import java.io.IOException;
import java.io.InputStream;
import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.SQLException;
import java.util.Properties; // saving DB credentials in properties file

public class ConnectionUtil {
	// singleton: private constructor, public static synchronized getter method
	private static ConnectionUtil connUtil;
	private Properties props;
	
	private ConnectionUtil() {
		props = new Properties();
		
		InputStream propsFile = ConnectionUtil.class.getClassLoader()
								.getResourceAsStream("database.properties");
		try {
			props.load(propsFile);
		} catch (IOException e) {
			e.printStackTrace();
		}
	}
	
	public static synchronized ConnectionUtil getConnectionUtil() {
		if (connUtil == null) {
			connUtil = new ConnectionUtil();
		}
		return connUtil;
	}
	
	// factory: creates Connection objects and returns them
	public Connection getConnection() {
		Connection conn = null;
		// using properties file
		String dbUrl = props.getProperty("url");
		String dbUser = props.getProperty("user");
		String dbPass = props.getProperty("pass");
		
		try {
			Class.forName("org.postgresql.Driver");
			conn = DriverManager.getConnection( //DriverManager.getConnection(URL, username, password)
					dbUrl,
					dbUser,
					dbPass);
		} catch (SQLException | ClassNotFoundException e) {
			e.printStackTrace();
		}
		return conn;
	}

}

