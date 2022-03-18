package ConnectionUtil;

import java.sql.Connection;

import java.sql.DriverManager;
import java.sql.SQLException;

public class ConnectionUtil {

	private static Connection cnxn;
	
	//return connection if existing/open, else creates connection to DB
	public static Connection getConnectionFromEnv() throws SQLException {
		
		String url = System.getenv("DB_URL");
		String username = System.getenv("DB_USER");
		String password = System.getenv("DB_PW");
		
		if (cnxn == null || cnxn.isClosed()) {
			cnxn = DriverManager.getConnection(url, username, password);
		}
		return cnxn;
	}
}
