package iloan.utilities;

import iloan.kernel.Environment;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.util.ArrayList;
import java.util.logging.Level;
import java.util.logging.Logger;
import javax.swing.table.DefaultTableModel;

/**
 *
 * @author mrogers
 */
public class SalutationList
{

    static final Logger logger = Logger.getLogger(SalutationList.class.getName());

    /**
     * This method adds the given String to the list of Salutations.
     *
     * @param title
     * @return -1 if nothing was done
     * @return 0 if the process completed successfully
     * @return 1 if an unknown error occurred during the process
     * @return 2 if the database already contains the String
     */
    public static int addSalutation(String title)
    {
        int result = -1;
        try
        {
            String sql = "INSERT INTO `lstSalutations` (`salutation`) VALUES (?);";
            PreparedStatement prep = Environment.getConnection().prepareStatement(sql);
            prep.setString(1, title);
            prep.execute();
            prep.close();
            result = 0;
        }
        catch (com.mysql.jdbc.exceptions.jdbc4.MySQLIntegrityConstraintViolationException e)
        {
            result = 2;
            String message = "An error occurred while adding a salutation.\n"
                    + "The entry already esists in the database.";
            logger.log(Level.SEVERE, message, e);
        }
        catch (Exception e)
        {
            result = 1;
            String message = "An error occurred while adding a salutation.";
            logger.log(Level.SEVERE, message, e);
        }
        return result;
    }

    /**
     * This method sets the status of the given salutation to Active
     *
     * @param salutation ID
     * @return True if the process completed successfully.
     */
    public static boolean enableSalutation(String salID)
    {
        boolean successfull = false;
        try
        {
            String sql = "UPDATE `lstSalutations` SET `status` = 'Active' WHERE `salID` = ?;";
            PreparedStatement prep = Environment.getConnection().prepareStatement(sql);
            prep.setString(1, salID);
            prep.executeUpdate();
            prep.close();
            successfull = true;
        }
        catch (Exception e)
        {
            String message = "An error occurred while trying to enable a salutation.";
            logger.log(Level.SEVERE, message, e);
        }
        return successfull;
    }

    /**
     * This method sets the status of the given salutation to Inactive.
     *
     * @param salutation ID
     * @return True if the process completed successfully.
     */
    public static boolean disableSalutation(String salID)
    {
        boolean successfull = false;
        try
        {
            String sql = "UPDATE `lstSalutations` SET `status` = 'Inactive' WHERE `salID` = ?;";
            PreparedStatement prep = Environment.getConnection().prepareStatement(sql);
            prep.setString(1, salID);
            prep.executeUpdate();
            prep.close();
            successfull = true;
        }
        catch (Exception e)
        {
            String message = "An error occurred while trying to disable a salutation.";
            logger.log(Level.SEVERE, message, e);
        }
        return successfull;
    }

    /**
     * This method returns a list of active salutations.
     *
     * @return An array list of the active salutations.
     */
    public static ArrayList<String> getSalutationList()
    {
        ArrayList<String> salutationList = new ArrayList<String>();
        try
        {
            String sql = "SELECT `salutation` FROM `lstSalutations` WHERE `status` = 'Active' ORDER BY `salutation`;";
            PreparedStatement prep = Environment.getConnection().prepareStatement(sql);
            ResultSet rs = prep.executeQuery();
            while (rs.next())
            {
                salutationList.add(rs.getString("salutation"));
            }
            rs.close();
            prep.close();
        }
        catch (Exception e)
        {
            String message = "An error occurred while trying to get the list of salutations.";
            logger.log(Level.SEVERE, message);
        }
        return salutationList;
    }

    /**
     * This method returns a table model containing a list of ALL salutations
     * both active and inactive.
     *
     * @return a table model containing a list of ALL salutations both active
     * and inactive.
     */
    public static DefaultTableModel getSalutationTable()
    {
        ArrayList<Integer> ids = new ArrayList<Integer>();
        ArrayList<String> salutations = new ArrayList<String>();
        ArrayList<String> statusses = new ArrayList<String>();

        final Class<?>[] columnClasses =
        {
            Integer.class, String.class, String.class
        };

        DefaultTableModel model = new DefaultTableModel()
        {

            @Override
            public Class<?> getColumnClass(int columnIndex)
            {
                return columnClasses[columnIndex];
            }

            @Override
            public boolean isCellEditable(int rowIndex, int mColIndex)
            {
                return false;
            }
        };

        try
        {
            String sql = "SELECT `salID`, `salutation`, `status` FROM `lstSalutations` ORDER BY `salutation` ASC;";
            PreparedStatement prep = Environment.getConnection().prepareStatement(sql);
            ResultSet rs = prep.executeQuery();
            while (rs.next())
            {
                ids.add(rs.getInt("salID"));
                salutations.add(rs.getString("salutation"));
                statusses.add(rs.getString("status"));
            }
            rs.close();
            prep.close();
        }
        catch (Exception e)
        {
            String message = "An error occurred while retreiving the list of assessment types.";
            logger.log(Level.SEVERE, message, e);
        }
        model.addColumn("ID", ids.toArray());
        model.addColumn("Salutation", salutations.toArray());
        model.addColumn("Status", statusses.toArray());
        return model;
    }
}
