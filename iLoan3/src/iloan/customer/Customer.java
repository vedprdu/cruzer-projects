/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */
package iloan.customer;

import iloan.kernel.Environment;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.util.ArrayList;
import java.util.logging.Level;
import java.util.logging.Logger;

/**
 *
 * @author mrogers
 */
public class Customer
{

    static final Logger logger = Logger.getLogger(Customer.class.getName());

    public static ArrayList<String> getSalutationList()
    {
        ArrayList<String> salutationList = new ArrayList<String>();
        try
        {
            String sql = "SELECT `salutation` FROM `lstSalutations` ORDER BY `salutation`;";
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
}
