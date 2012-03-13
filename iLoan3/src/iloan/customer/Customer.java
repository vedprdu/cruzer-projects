package iloan.customer;

import iloan.kernel.Environment;
import iloan.kernel.Utilities;
import java.io.File;
import java.io.FileInputStream;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.util.ArrayList;
import java.util.Date;
import java.util.HashMap;
import java.util.logging.Level;
import java.util.logging.Logger;
import javax.swing.table.DefaultTableModel;

/**
 *
 * @author mrogers
 */
public class Customer
{

    static final Logger logger = Logger.getLogger(Customer.class.getName());

    /**
     * This method will add a customer to the system.
     *
     * @param customer - a Hash map containing the values to add.
     * @return True if the process completed successfully.
     * @return False if the process failed.
     */
    public static boolean addCustomer(HashMap<String, Object> customer)
    {
        boolean successful = false;
        try
        {
            File custPhoto = (File) customer.get("image");
            FileInputStream fis = new FileInputStream(custPhoto);
            String sql = " INSERT INTO `customer` "
                    + " (`custIDType`, `custIDNum`, `custSalutation`, `custFirstName`, "
                    + " `custLastName`, `custOtherName`, `custDOB`, `custGender`, "
                    + " `custAddress`, `custPhone1`, `custPhone2`, `custEmail1`, "
                    + " `custOccupation`, `custWorkplace`, `custWorkPhone`, `custWorkEmail`, "
                    + " `custWorkAddress`, `custPicture`, `custNotes`, `custRating`, "
                    + " `custCreated`, `custModified`) "
                    + " VALUES "
                    + "(?, ?, ?, ?,?, ?, ?, ?, ?, ?, ?, ?, ?, ?, ?, ?, ?, ?, ?, ?, ?, ?);";
            PreparedStatement prep = Environment.getConnection().prepareStatement(sql);
            prep.setString(1, (String) customer.get("idType"));
            prep.setString(2, (String) customer.get("idNum"));
            prep.setString(3, (String) customer.get("salutation"));
            prep.setString(4, (String) customer.get("firstName"));
            prep.setString(5, (String) customer.get("lastName"));
            prep.setString(6, (String) customer.get("otherName"));
            prep.setString(7, (String) customer.get("dob"));
            prep.setString(8, (String) customer.get("gender"));
            prep.setString(9, (String) customer.get("homeAddress"));
            prep.setString(10, (String) customer.get("landline"));
            prep.setString(11, (String) customer.get("cellPhone"));
            prep.setString(12, (String) customer.get("homeEmail"));
            prep.setString(13, (String) customer.get("occupation"));
            prep.setString(14, (String) customer.get("workplace"));
            prep.setString(15, (String) customer.get("workTelephone"));
            prep.setString(16, (String) customer.get("workEmail"));
            prep.setString(17, (String) customer.get("workAddress"));
            prep.setBlob(18, fis, custPhoto.length());
            prep.setString(19, (String) customer.get("notes"));
            prep.setString(20, "Good");//Set Default customer Rating.
            prep.setTimestamp(21, new java.sql.Timestamp(new Date().getTime()));//
            prep.setTimestamp(22, new java.sql.Timestamp(new Date().getTime()));
            prep.execute();
            prep.close();
            successful = true;
        }
        catch (Exception e)
        {
            String message = "An error occurred while adding the customer to the system.";
            logger.log(Level.SEVERE, message, e);
        }
        return successful;
    }

    /**
     * This method will update a customer's information using the given
     * parameters.
     *
     * @param customer
     * @return
     */
    public static boolean editCustomer(HashMap<String, Object> customer)
    {
        boolean successful = false;
        try
        {
            File custPhoto = (File) customer.get("image");
            FileInputStream fis = new FileInputStream(custPhoto);
            String sql = " UPDATE `customer` SET "
                    + " `custIDType` = ? , `custIDNum` = ? , `custSalutation` = ? , `custFirstName` = ? , "
                    + " `custLastName` = ? , `custOtherName` = ? , `custDOB` = ? , `custGender` = ? , "
                    + " `custAddress` = ? , `custPhone1` = ? , `custPhone2` = ? , `custEmail1` = ? , "
                    + " `custOccupation` = ? , `custWorkplace` = ? , `custWorkPhone` = ? , `custWorkEmail` = ? , "
                    + " `custWorkAddress` = ? , `custPicture` = ? , `custNotes` = ? , `custRating` = ? , "
                    + " `custModified` = ?  "
                    + " WHERE `custID` =  ? ;";
            PreparedStatement prep = Environment.getConnection().prepareStatement(sql);
            prep.setString(1, (String) customer.get("idType"));
            prep.setString(2, (String) customer.get("idNum"));
            prep.setString(3, (String) customer.get("salutation"));
            prep.setString(4, (String) customer.get("firstName"));
            prep.setString(5, (String) customer.get("lastName"));
            prep.setString(6, (String) customer.get("otherName"));
            prep.setString(7, (String) customer.get("dob"));
            prep.setString(8, (String) customer.get("gender"));
            prep.setString(9, (String) customer.get("homeAddress"));
            prep.setString(10, (String) customer.get("landline"));
            prep.setString(11, (String) customer.get("cellPhone"));
            prep.setString(12, (String) customer.get("homeEmail"));
            prep.setString(13, (String) customer.get("occupation"));
            prep.setString(14, (String) customer.get("workplace"));
            prep.setString(15, (String) customer.get("workTelephone"));
            prep.setString(16, (String) customer.get("workEmail"));
            prep.setString(17, (String) customer.get("workAddress"));
            prep.setBlob(18, fis, custPhoto.length());
            prep.setString(19, (String) customer.get("notes"));
            prep.setString(20, (String) customer.get("rating"));
            prep.setTimestamp(21, new java.sql.Timestamp(new Date().getTime()));
            prep.setString(22, (String) customer.get("id"));
            prep.executeUpdate();
            prep.close();
            successful = true;
        }
        catch (Exception e)
        {
            String message = "An error occurred while updating the customer's info.";
            logger.log(Level.SEVERE, message, e);
        }
        return successful;
    }

    /**
     * This method returns a table model containing the list of customers in the
     * system
     *
     * @return
     */
    public static DefaultTableModel getCustomerTable()
    {
        ArrayList<Integer> ids = new ArrayList<Integer>();
        ArrayList<String> firstNames = new ArrayList<String>();
        ArrayList<String> lastNames = new ArrayList<String>();
        ArrayList<Date> dateOfBirths = new ArrayList<Date>();
        ArrayList<String> ratings = new ArrayList<String>();
        final Class<?>[] columnClasses =
        {
            Integer.class, String.class, String.class, Date.class, String.class
        };
        DefaultTableModel model = new DefaultTableModel()
        {

            @Override
            public Class<?> getColumnClass(int columnIndex)
            {
                return columnClasses[columnIndex];
            }

            @Override
            public boolean isCellEditable(int row, int column)
            {
                return false;
            }
        };
        try
        {
            String sql = " SELECT `custID`, "
                    + " `custFirstName`, `custLastName`, `custDOB`, "
                    + " `custRating` "
                    + "FROM `customer` ;";
            PreparedStatement prep = Environment.getConnection().prepareStatement(sql);
            ResultSet rs = prep.executeQuery();
            while (rs.next())
            {
                ids.add(rs.getInt("custID"));
                firstNames.add(rs.getString("custFirstName"));
                lastNames.add(rs.getString("custLastName"));
                dateOfBirths.add(rs.getDate("custDOB"));
                ratings.add(rs.getString("custRating"));
            }
            rs.close();
            prep.close();
        }
        catch (Exception e)
        {
            String message = "An error occurred while retreiving the list of customers.";
            logger.log(Level.SEVERE, message, e);
        }
        model.addColumn("ID", ids.toArray());
        model.addColumn("First Name", firstNames.toArray());
        model.addColumn("Last Name", lastNames.toArray());
        model.addColumn("DOB", dateOfBirths.toArray());
        model.addColumn("Rating", ratings.toArray());
        return model;
    }

    /**
     * This methods retrieves the customer with the specified ID.
     *
     * @param id - the customer's system ID NOT the ID number.
     * @return A hash map containing the properties of the customer.
     */
    public static HashMap<String, Object> getByID(int id)
    {
        HashMap<String, Object> customer = new HashMap<String, Object>();
        try
        {
            String sql = " SELECT `custID`, `custIDType`, `custIDNum`, `custSalutation`, "
                    + " `custFirstName`, `custLastName`, `custOtherName`, `custDOB`, "
                    + " `custGender`, `custAddress`, `custPhone1`, `custPhone2`, "
                    + " `custEmail1`, `custOccupation`, `custWorkplace`, `custWorkPhone`, "
                    + " `custWorkEmail`, `custWorkAddress`, `custPicture`, `custNotes`, "
                    + " `custRating`, `custCreated`, `custModified`, `custStatus` "
                    + " FROM `customer` WHERE `custID` = ? ;";
            PreparedStatement prep = Environment.getConnection().prepareStatement(sql);
            prep.setInt(1, id);
            ResultSet rs = prep.executeQuery();
            while (rs.next())
            {
                customer.put("id", rs.getString("custID"));
                customer.put("idType", rs.getString("custIDType"));
                customer.put("idNum", rs.getString("custIDNum"));
                customer.put("salutation", rs.getString("custSalutation"));
                customer.put("firstName", rs.getString("custFirstName"));
                customer.put("lastName", rs.getString("custLastName"));
                customer.put("otherName", rs.getString("custOtherName"));
                customer.put("dob", rs.getDate("custDOB"));
                customer.put("gender", rs.getString("custGender"));
                customer.put("landline", rs.getString("custPhone1"));
                customer.put("cellPhone", rs.getString("custPhone2"));
                customer.put("homeEmail", rs.getString("custEmail1"));
                customer.put("homeAddress", rs.getString("custAddress"));
                customer.put("occupation", rs.getString("custOccupation"));
                customer.put("workplace", rs.getString("custWorkplace"));
                customer.put("workTelephone", rs.getString("custWorkPhone"));
                customer.put("workEmail", rs.getString("custWorkEmail"));
                customer.put("workAddress", rs.getString("custWorkAddress"));
                customer.put("notes", rs.getString("custNotes"));
                customer.put("image", rs.getBlob("custPicture"));
                customer.put("rating", rs.getString("custRating"));
                customer.put("created", Utilities.MDY_Formatter.format(rs.getDate("custCreated")));
                customer.put("modified", Utilities.MDY_Formatter.format(rs.getDate("custModified")));
            }
        }
        catch (Exception e)
        {
            String message = "An error occurred while retreiving the specified customer.";
            logger.log(Level.SEVERE, message, e);
        }
        return customer;
    }
}
