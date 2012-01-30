/************************************************************************/
/* iLoan - Quick Loan Management System                                 */
/* ===========================                                          */
/*                                                                      */
/* Copyright (c) 2011 by Maurice Rogers                                 */
/* http://www.mauricerogers.com                                         */
/************************************************************************/
package iloan.kernel;

import java.awt.Component;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.text.DateFormat;
import java.text.DecimalFormat;
import java.text.SimpleDateFormat;
import javax.swing.JComboBox;
import javax.swing.JOptionPane;
import org.apache.log4j.Level;
import org.apache.log4j.Logger;

/**
 *
 * @author mrogers
 */
public class Utilities
{

    public static DateFormat YMD_Formatter = new SimpleDateFormat("yyyy-MM-dd");
    public static DateFormat MDY_Formatter = new SimpleDateFormat("MMM d, yyyy"); //Create an object to format the dates

    /**
     * This method loads the result set contents into the provided combo box.
     * @param query
     * @param cmbBox
     */
    public static void loadComboValues(String query, JComboBox cmbBox)
    {
        cmbBox.removeAllItems();
        try
        {
            PreparedStatement prep = Environment.getConnection().prepareStatement(query);
            ResultSet rs = prep.executeQuery();

            while (rs.next())
            {
                cmbBox.addItem(rs.getString(1));
            }
            rs.close();
            prep.close();
        }
        catch (Exception e)
        {
            String message = "An error occurred while loading the combo box values.";
            Logger.getLogger(Utilities.class.getName()).log(Level.ERROR, message);
        }
    }

    /**
     *
     * @param content
     * @return Returns the parameter surrounded by single quotes.
     */
    public static String quotate(String content)
    {
        return "'" + content + "'";
    }

    /**
     *
     * @param content
     * @return Returns the parameter surrounded by percent signs.
     */
    public static String percent(String content)
    {

        return "%" + content + "%";
    }

    /**
     * This functions logs the specified action to the database.
     * @param message - The message to be logged.
     */
    public static void logAction(String message)
    {
        String query = "INSERT INTO log (message) VALUES (?,?)";
        try
        {
            PreparedStatement prep = Environment.getConnection().prepareStatement(query);
            prep.setString(1, message);
            prep.execute();
            prep.close();
        }
        catch (SQLException ex)
        {
            String errMessage = "An error occurred while logging the info.";
            Logger.getLogger(Utilities.class.getName()).log(Level.ERROR, errMessage);
        }
    }

    public static String formatAsMoney(double value)
    {
        DecimalFormat df = new DecimalFormat("$#,##0.00");
        String total = df.format(value);
        return total;
    }

    public static int showConfirmDialog(Component rootpane, String message)
    {
        return JOptionPane.showConfirmDialog(rootpane, message, "iLoan", JOptionPane.YES_NO_OPTION);
    }

    public static void showInfoMessage(Component rootpane, String message)
    {
        JOptionPane.showMessageDialog(rootpane, message, "iLoan", JOptionPane.INFORMATION_MESSAGE);
    }

    public static void showErrorMessage(Component rootpane, String message)
    {
        JOptionPane.showMessageDialog(rootpane, message, "iLoan", JOptionPane.ERROR_MESSAGE);
    }
}
