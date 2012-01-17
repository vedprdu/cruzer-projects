/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */
package icontact.kernel;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.SQLException;
import java.util.logging.Level;
import java.util.logging.Logger;

/**
 *
 * @author mrogers
 */
public class Environment
{

    static final Logger logger = Logger.getLogger(Environment.class.getName());
    private static Connection dbConnection;

    /**
     * This method creates the connection to the database.
     * @return a connection object verifying that the database is live.
     */
    public static void createConnection()
    {

        String dbDriver = "com.mysql.jdbc.Driver";
        String dbLocation = "jdbc:mysql://localhost/icontact";
        String dbUser = "root";
        String dbPass = "Debbiedo1";
        //Try to connect to the database
        try
        {
            //Create database connection objects.
            Class.forName(dbDriver); //set the database driver
            dbConnection = DriverManager.getConnection(dbLocation, dbUser, dbPass);
            String message = "Successfully connected to the database.";
            logger.log(Level.INFO, message);
        }
        catch (ClassNotFoundException cnfEx)
        {
            String message = "ERROR: The driver specified could not be found.";
            logger.log(Level.SEVERE, message, cnfEx);
            message = "An error occurred while connecting to the database.\n"
                    + "Kindly check with your system administrator.";
            Utilities.showErrorMessage(message);
        }
        catch (SQLException sqlEx)
        {
            String message = "ERROR: Could not connect to the database.";
            logger.log(Level.SEVERE, message, sqlEx);
            message = "An error occurred while connecting to the database.\n"
                    + "Kindly check your connection and consult with your system administrator.";
            Utilities.showErrorMessage(message);

        }
        catch (Exception e)
        {
            String message = "ERROR: Could not connect to the database.";
            logger.log(Level.SEVERE, message, e);
            message = "An error occurred while connecting to the database.\n"
                    + "Kindly check with your system administrator.";
            Utilities.showErrorMessage(message);

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
