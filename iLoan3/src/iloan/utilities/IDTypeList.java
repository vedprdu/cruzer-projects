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
public class IDTypeList
{

    static final Logger logger = Logger.getLogger(IDTypeList.class.getName());

    /**
     * This method adds the given String to the list of ID Types.
     *
     * @param title
     * @return -1 if nothing was done
     * @return 0 if the process completed successfully
     * @return 1 if an unknown error occurred during the process
     * @return 2 if the database already contains the String
     */
    public static int addIDType(String title)
    {
        int result = -1;
        try
        {
            String sql = "INSERT INTO `lstIDTypes` (`IDType`) VALUES (?);";
            PreparedStatement prep = Environment.getConnection().prepareStatement(sql);
            prep.setString(1, title);
            prep.execute();
            prep.close();
            result = 0;
        }
        catch (com.mysql.jdbc.exceptions.jdbc4.MySQLIntegrityConstraintViolationException e)
        {
            result = 2;
            String message = "An error occurred while adding an ID Type.\n"
                    + "The entry already esists in the database.";
            logger.log(Level.SEVERE, message, e);
        }
        catch (Exception e)
        {
            result = 1;
            String message = "An error occurred while adding an ID Type.";
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
    public static boolean enableIDType(String idTypeID)
    {
        boolean successfull = false;
        try
        {
            String sql = "UPDATE `lstIDTypes` SET `status`='Active' WHERE `idTypeID`= ?;";
            PreparedStatement prep = Environment.getConnection().prepareStatement(sql);
            prep.setString(1, idTypeID);
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
    public static boolean disableIDType(String idTypeID)
    {
        boolean successfull = false;
        try
        {
            String sql = "UPDATE `lstIDTypes` SET `status`='Inactive' WHERE `idTypeID`= ?;";
            PreparedStatement prep = Environment.getConnection().prepareStatement(sql);
            prep.setString(1, idTypeID);
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
     * This method returns a list of active ID Types.
     *
     * @return An array list of the active ID Types.
     */
    public static ArrayList<String> getIDTypeList()
    {
        ArrayList<String> idTypeList = new ArrayList<String>();
        try
        {
            String sql = "SELECT  `IDType` FROM `lstIDTypes` WHERE `status` = 'Active' ORDER BY `IDType`;";
            PreparedStatement prep = Environment.getConnection().prepareStatement(sql);
            ResultSet rs = prep.executeQuery();
            while (rs.next())
            {
                idTypeList.add(rs.getString("IDType"));
            }
            rs.close();
            prep.close();
        }
        catch (Exception e)
        {
            String message = "An error occurred while trying to get the list of ID Types.";
            logger.log(Level.SEVERE, message);
        }
        return idTypeList;
    }

    /**
     * This method returns a table model containing a list of ALL ID Types
     * both active and inactive.
     *
     * @return a table model containing a list of ALL ID Types both active
     * and inactive.
     */
    public static DefaultTableModel getIDTypeTable()
    {
        ArrayList<Integer> ids = new ArrayList<Integer>();
        ArrayList<String> idTypes = new ArrayList<String>();
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
            String sql = "SELECT `idTypeID`, `IDType`, `status` FROM `lstIDTypes` ORDER BY `IDType` ASC;";
            PreparedStatement prep = Environment.getConnection().prepareStatement(sql);
            ResultSet rs = prep.executeQuery();
            while (rs.next())
            {
                ids.add(rs.getInt("idTypeID"));
                idTypes.add(rs.getString("IDType"));
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
        model.addColumn("ID Type", idTypes.toArray());
        model.addColumn("Status", statusses.toArray());
        return model;
    }
}
