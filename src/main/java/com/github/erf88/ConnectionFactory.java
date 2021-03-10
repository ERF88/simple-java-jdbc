package com.github.erf88;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.SQLException;

public class ConnectionFactory {

	private final String DRIVER_CLASS = "com.mysql.cj.jdbc.Driver";
	private final String URL = "jdbc:mysql://127.0.0.1:3306/test";
	private final String USER = "root";
	private final String PASSWORD = "root";	

	private Connection createConnection() {
		
		Connection connection = null;

		try {
			Class.forName(DRIVER_CLASS);
			connection = DriverManager.getConnection(URL, USER, PASSWORD);
		} catch (SQLException | ClassNotFoundException e) {
			e.getStackTrace();
		}

		return connection;
	}
	
	public Connection getConnection() {
		return createConnection();
	}

}
