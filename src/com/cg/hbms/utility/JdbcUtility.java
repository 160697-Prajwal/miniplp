package com.cg.hbms.utility;

import java.io.File;
import java.io.FileInputStream;
import java.io.FileNotFoundException;
import java.io.IOException;
import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.SQLException;
import java.util.Properties;

import com.cg.hbms.exceptions.HMSExceptionList;
import com.cg.hbms.exceptions.HMSExceptions;

public class JdbcUtility {
	static Connection connection = null;

	public static Connection getConnection() throws HMSExceptions {
		Properties properties = new Properties();

		try {
			properties.load(new FileInputStream(new File(
					"resources/jdbc.properties")));

			String driver = properties.getProperty("db.driver");
			String url = properties.getProperty("db.url");
			String username = properties.getProperty("db.username");
			String password = properties.getProperty("db.password");

			Class.forName(driver);
			connection = DriverManager.getConnection(url, username, password);
		} catch (FileNotFoundException e) {
			throw new HMSExceptions(HMSExceptionList.Error1);
		} catch (IOException e) {
			throw new HMSExceptions(HMSExceptionList.Error2);
		} catch (ClassNotFoundException e) {
			throw new HMSExceptions(HMSExceptionList.Error3);
		} catch (SQLException e) {
			throw new HMSExceptions(HMSExceptionList.Error4);
		}
		return connection;

	}

	static void closeConnection() throws HMSExceptions {
		try {
			connection.close();
		} catch (SQLException e) {
			throw new HMSExceptions(HMSExceptionList.Error5);
		}

	}
}