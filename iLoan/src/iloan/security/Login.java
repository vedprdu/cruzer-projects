/************************************************************************/
/* iLoan - Quick Loan Management System                                 */
/* ===========================                                          */
/*                                                                      */
/* Copyright (c) 2011 by Maurice Rogers                                 */
/* http://www.mauricerogers.com                                         */
/************************************************************************/
package iloan.security;

import iloan.kernel.Environment;
import java.sql.PreparedStatement;
import java.sql.ResultSet;

/**
 *
 * @author mrogers
 */
public class Login
{

    public static int loginCounter = 0;

    public static boolean tryLogin(String userName, String password)
    {
        boolean successful = false;
        String storedpassword = "";

        try
        {
            String sql = "SELECT * FROM data_User WHERE data_User.status = 'Active' AND data_User.userName = ?;";
            PreparedStatement prep = Environment.getConnection().prepareStatement(sql);
            prep.setString(1, userName);
            ResultSet rs = prep.executeQuery();
            rs.first();
            storedpassword = rs.getString("password");
            rs.close();
            prep.close();

            if (storedpassword.equals(EncryptionHandler.encryptPassword(password)))
            {
                successful = true;
            }
        }
        catch (Exception e)
        {
            //String message = "An error occurred while trying to log in the user.";
            //Logger.getLogger(Login.class.getName()).log(Level.SEVERE, message, e);
        }
        loginCounter++;
        return successful;
    }

    public static void createUser(String firstName, String lastName, String userName, String password, String group)
    {
        try
        {
            String sql = "INSERT INTO `data_User` (`firstName`, `lastName`, `userName`, `password`, `group`, `status`) VALUES (?, ?, ?, ?, ?, ?);";
            PreparedStatement prep = Environment.getConnection().prepareStatement(sql);
            prep.setString(1, firstName);
            prep.setString(2, lastName);
            prep.setString(3, userName);
            prep.setString(4, EncryptionHandler.encryptPassword(password));
            prep.setString(5, group);
            prep.setString(6, "Active");

        }
        catch (Exception e)
        {
        }
    }
}
