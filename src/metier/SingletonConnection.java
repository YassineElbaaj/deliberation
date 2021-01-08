package metier;

import java.sql.Connection;
import java.sql.DriverManager;

public class SingletonConnection {
	private static Connection ret = null;

	public static Connection getConnectionBd() {

		try {

			Class.forName("com.mysql.jdbc.Driver");

			String mysqlConnUrl = "jdbc:mysql://localhost:3306/db";

			String mysqlUserName = "root";

			String mysqlPassword = "";

			ret = DriverManager.getConnection(mysqlConnUrl, mysqlUserName, mysqlPassword);

		

		} catch (Exception ex) {
			ex.printStackTrace();
		}
		return ret;

	}

	public static Connection getConnectionLogin() {

		try {

			Class.forName("com.mysql.jdbc.Driver");

			String mysqlConnUrl = "jdbc:mysql://localhost:3306/db";

			String mysqlUserName = "root";

			String mysqlPassword = "";

			ret = DriverManager.getConnection(mysqlConnUrl, mysqlUserName, mysqlPassword);

		} catch (Exception ex) {
			ex.printStackTrace();
		}
		return ret;

	}
}
