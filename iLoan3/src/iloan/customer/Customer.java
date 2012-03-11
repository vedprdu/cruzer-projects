package iloan.customer;

import iloan.kernel.Environment;
import java.io.File;
import java.io.FileInputStream;
import java.sql.PreparedStatement;
import java.util.Date;
import java.util.HashMap;
import java.util.logging.Level;
import java.util.logging.Logger;

/**
 *
 * @author mrogers
 */
public class Customer
{
    
    static final Logger logger = Logger.getLogger(Customer.class.getName());
    
    public static boolean addCustomer(HashMap<String, Object> params)
    {
        boolean successful = false;
        try
        {
            File custPhoto = (File) params.get("image");
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
            prep.setString(1, (String) params.get("idType"));
            prep.setString(2, (String) params.get("idNum"));
            prep.setString(3, (String) params.get("salutation"));
            prep.setString(4, (String) params.get("firstName"));
            prep.setString(5, (String) params.get("lastName"));
            prep.setString(6, (String) params.get("otherName"));
            prep.setString(7, (String) params.get("dob"));
            prep.setString(8, (String) params.get("gender"));
            prep.setString(9, (String) params.get("homeAddress"));
            prep.setString(10, (String) params.get("landline"));
            prep.setString(11, (String) params.get("cellPhone"));
            prep.setString(12, (String) params.get("homeEmail"));
            prep.setString(13, (String) params.get("occupation"));
            prep.setString(14, (String) params.get("workplace"));
            prep.setString(15, (String) params.get("workTelephone"));
            prep.setString(16, (String) params.get("workEmail"));
            prep.setString(17, (String) params.get("workAddress"));
            prep.setBlob(18, fis, custPhoto.length());
            prep.setString(19, (String) params.get("notes"));
            prep.setString(20, "Good");//Set Default customer Rating.   
            prep.setTimestamp(21, new java.sql.Timestamp(new Date().getTime()));//
            prep.setTimestamp(22, new java.sql.Timestamp(new Date().getTime()));
            prep.execute();
            prep.close();
            successful = true;
        }
        catch (Exception e)
        {
            String message = "An error occurred while adding the user to the system.";
            logger.log(Level.SEVERE, message, e);
        }
        return successful;
    }
}
