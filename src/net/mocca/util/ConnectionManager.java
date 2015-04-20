package net.mocca.util;

import java.sql.Connection;
import java.sql.DriverManager;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

public class ConnectionManager {

	private static final Logger logger = LoggerFactory
			.getLogger(ConnectionManager.class);

	public static Connection getConnection() {
		String url = "jdbc:mysql://localhost:3306/mocca";
		String id = "root";
		String password = "";
		try {
			Class.forName("com.mysql.jdbc.Driver");
			return DriverManager.getConnection(url, id, password);
		} catch (Exception e) {
			logger.error(e.getMessage());
		}
		return null;
	}
}
