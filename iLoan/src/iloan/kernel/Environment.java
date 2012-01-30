/************************************************************************/
/* iLoan - Quick Loan Management System                                 */
/* ===========================                                          */
/*                                                                      */
/* Copyright (c) 2011 by Maurice Rogers                                 */
/* http://www.mauricerogers.com                                         */
/************************************************************************/
package iloan.kernel;

import iloan.ILoanApp;
import iloan.security.EncryptionHandler;
import java.io.FileInputStream;
import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.text.ParseException;
import java.util.Date;
import java.util.Properties;
import java.util.logging.Level;
import java.util.logging.Logger;
import javax.swing.JOptionPane;
import org.jdesktop.application.Application;
import org.jdesktop.application.ResourceMap;
import org.jdesktop.application.Task;

/**
 *
 * @author mrogers
 */
public class Environment
{

    /**
     * @return the dbConnection
     */
    public static Connection getDbConnection()
    {
        return dbConnection;
    }
    private static String companyName = "";
    private static String telephone1 = "";
    private static String telephone2 = "";
    private static String address = "";
    private static String branchCode = "";
    private static String registrationCode = "";
    private static String timeCode = "";
    private static String dbVersion = "";
    static ResourceMap resourceMap = ILoanApp.getApplication().getContext().getResourceMap();
    static Properties properties = new Properties();
    private static Connection dbConnection;

    public Environment()
    {
        ILoanApp.getApplication().getContext().getTaskService().execute(initializeEnvironment());
    }

    private Task initializeEnvironment()
    {
        return new initializeEnvironmentTask(ILoanApp.getInstance());
    }

    /**
     * @return the timeCode
     */
    public String getTimeCode()
    {
        return timeCode;
    }

    private class initializeEnvironmentTask extends org.jdesktop.application.Task<Object, Void>
    {

        public initializeEnvironmentTask(Application application)
        {
            super(application);
        }

        @Override
        protected Object doInBackground() throws Exception
        {
            setMessage("About to connect to database.");

            try
            {
                setMessage("Connecting to database.");
                createConnection();
                getCompanyInfo();
                checkAppInfo();
                setMessage("Successfully connected to database.");
            }
            catch (Exception e)
            {
                String message = "An error occurred while connecting to the database.";
                Logger.getLogger(Environment.class.getName()).log(Level.SEVERE, message, e);
            }
            return null;
        }
    }

    public static void getProperties()
    {
        try
        {
            properties.load(new FileInputStream("iLoan.properties"));
        }
        catch (Exception e)
        {
            JOptionPane.showMessageDialog(ILoanApp.getApplication().getMainFrame(),
                    "An error occurred."
                    + "\nCould not read the application's settings."
                    + "\n\nKindly consult the system administrator.",
                    "iLoan", JOptionPane.ERROR_MESSAGE);
            System.err.println("The properties file could not be read.");
            System.exit(1);
        }
    }

    /**
     * This method creates the connection to the database.
     * @return a connection object verifying that the database is live.
     */
    public static Connection createConnection()
    {
        getProperties();

        String dbDriver = EncryptionHandler.decrypt(properties.getProperty("dbDriver"));
        String dbLocation = EncryptionHandler.decrypt(properties.getProperty("dbLocation"));
        String dbUser = EncryptionHandler.decrypt(properties.getProperty("dbUser"));
        String dbPass = EncryptionHandler.decrypt(properties.getProperty("dbPass"));

        boolean connectSucessful = true;
        //Try to connect to the database
        try
        {
            //Create database connection objects.
            Class.forName(dbDriver); //set the database driver
            dbConnection = DriverManager.getConnection(dbLocation, dbUser, dbPass);
            String message = "Successfully connected to the database.";
            Logger.getLogger(Environment.class.getName()).log(Level.INFO, message);
        }
        catch (ClassNotFoundException cnfEx)
        {
            connectSucessful = false;
            String message = "ERROR: The driver specified could not be found.";
            Logger.getLogger(Environment.class.getName()).log(Level.SEVERE, message, cnfEx);
        }
        catch (SQLException sqlEx)
        {
            connectSucessful = false;
            String message = "ERROR: Could not connect to the database.";
            Logger.getLogger(Environment.class.getName()).log(Level.SEVERE, message, sqlEx);
        }

        //Test if we were able to connect to the database.
        if (connectSucessful == true)
        {
            return getDbConnection();
        }
        else
        {
            String message = "An error occured while connecting to the database.";
            JOptionPane.showMessageDialog(null, message, "iLoan", JOptionPane.ERROR_MESSAGE);
            System.exit(1);
        }
        return getDbConnection();
    }

    public static void getCompanyInfo()
    {
        try
        {
            String sql = "SELECT `companyID`, `companyName`, `Telephone 1`, `Telephone 2`, `Address`, `branchCode`, `registrationCode`, `timeCode`, `dbVersion` FROM `iLoan`.`data_Company`;";
            PreparedStatement prep = getConnection().prepareStatement(sql);
            ResultSet rs = prep.executeQuery();

            rs.first();
            companyName = rs.getString("companyName");
            telephone1 = rs.getString("Telephone 1");
            telephone2 = rs.getString("Telephone 2");
            address = rs.getString("Address");
            branchCode = rs.getString("branchCode");
            registrationCode = rs.getString("registrationCode");
            timeCode = rs.getString("timeCode");
            dbVersion = rs.getString("dbVersion");
            rs.close();
            prep.close();
        }
        catch (Exception e)
        {
            String message = "An error occurred while reading the company information.";
            Logger.getLogger(Environment.class.getName()).log(Level.SEVERE, message, e);
        }
    }

    public static void checkAppInfo()
    {
        //Check DB Version
        int minimumDBVersion = Integer.valueOf(properties.getProperty("dbVersion"));
        int foundDBVersion = Integer.valueOf(dbVersion);
        if (foundDBVersion < minimumDBVersion)
        {
            String message = "The database was meant to be used with another version of this application.\n"
                    + "To protect your data, the program will now exit.";
            Logger.getLogger(Environment.class.getName()).log(Level.SEVERE, message);
            Utilities.showErrorMessage(null, message);
            ILoanApp.getApplication().quit(null);
        }

        //Check time code

        try
        {
            Date currentDate = Utilities.YMD_Formatter.parse(Utilities.YMD_Formatter.format(new Date()));
            Date expiryDate = Utilities.YMD_Formatter.parse(EncryptionHandler.decrypt(timeCode));

            //TODO add code to calculate date difference


            if (expiryDate.before(currentDate))
            {
                String message = "The license has expired.\n"
                        + "Kindly contact your provider to get a new license.";
                Logger.getLogger(Environment.class.getName()).log(Level.SEVERE, message);
                Utilities.showErrorMessage(null, message);
                ILoanApp.getApplication().quit(null);
            }
        }
        catch (ParseException ex)
        {
            Logger.getLogger(Environment.class.getName()).log(Level.SEVERE, null, ex);
        }
    }

    /**
     * @return the companyName
     */
    public String getCompanyName()
    {
        return companyName;
    }

    /**
     * @return the telephone1
     */
    public String getTelephone1()
    {
        return telephone1;
    }

    /**
     * @return the telephone2
     */
    public String getTelephone2()
    {
        return telephone2;
    }

    /**
     * @return the address
     */
    public String getAddress()
    {
        return address;
    }

    /**
     * @return the branchCode
     */
    public String getBranchCode()
    {
        return branchCode;
    }

    /**
     * @return the registrationCode
     */
    public String getRegistrationCode()
    {
        return registrationCode;
    }

    /**
     * @return the dbVersion
     */
    public String getDbVersion()
    {
        return dbVersion;
    }

    /**
     * @return the connection
     */
    public static Connection getConnection()
    {
        return getDbConnection();
    }
}
