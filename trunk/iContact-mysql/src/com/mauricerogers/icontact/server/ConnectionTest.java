package com.mauricerogers.icontact.server;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.SQLException;
import java.util.logging.Level;
import java.util.logging.Logger;

public class ConnectionTest
{
	static final Logger logger = Logger.getLogger(ConnectionTest.class.getName());
	private static Connection dbConnection = null;

	/**
	 * This method creates the connection to the database.
	 * 
	 * @return a connection object verifying that the database is live.
	 */
	public static void createConnection()
	{

		String dbDriver = "com.mysql.jdbc.Driver";
		String dbLocation = "jdbc:mysql://localhost:3306/iLearn";
		String dbUser = "root";
		String dbPass = "1cbp@55wrd";
		// Try to connect to the database
		try
		{
			// Create database connection objects.
			Class.forName(dbDriver); // set the database driver
			dbConnection = DriverManager.getConnection(dbLocation, dbUser, dbPass);
			String message = "Successfully connected to the database.";
			logger.log(Level.INFO, message);
		}
		catch (ClassNotFoundException cnfEx)
		{
			String message = "ERROR: The driver specified could not be found.";
			logger.log(Level.SEVERE, message, cnfEx);
		}
		catch (SQLException sqlEx)
		{
			String message = "ERROR: Could not connect to the database.";
			logger.log(Level.SEVERE, message, sqlEx);
		}
		catch (Exception e)
		{
			String message = "ERROR: Could not connect to the database.";
			logger.log(Level.SEVERE, message, e);
		}
	}

	/**
	 * @return the dbConnection
	 */
	public static Connection getConnection()
	{
		return dbConnection;
	}

}
