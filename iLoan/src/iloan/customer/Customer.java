/************************************************************************/
/* iLoan - Quick Loan Management System                                 */
/* ===========================                                          */
/*                                                                      */
/* Copyright (c) 2011 by Maurice Rogers                                 */
/* http://www.mauricerogers.com                                         */
/************************************************************************/
package iloan.customer;

import iloan.ILoanApp;
import iloan.kernel.Environment;
import java.io.File;
import java.io.FileInputStream;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.util.ArrayList;
import java.util.logging.Level;
import java.util.logging.Logger;
import javax.swing.DefaultComboBoxModel;
import javax.swing.JComboBox;
import org.jdesktop.application.Application;
import org.jdesktop.application.Task;

/**
 *
 * @author mrogers
 */
public class Customer
{

    public static boolean addCustomer(String customerID, String idType, String idNumber, String salutation, String firstName, String lastName, String otherName, String homeAddress, String homeTelephone, String homeMobile, String homeEmail, String workplace, String workAddress, String workTelephone, String workMobile, String workEmail, String dob, String gender, String registrationDate, String picture)
    {
        boolean successful = false;

        try
        {
            //Get the picture ready
            File imgfile = new File(picture);
            FileInputStream fin = new FileInputStream(imgfile);

            //Prepare the insert statement
            String sql = "INSERT INTO `data_Customer` (`customerID`, `idType`, `idNumber`, `salutation`, `firstName`, `lastName`, `otherName`, `homeAddress`, `homeTelephone`, `homeMobile`, `homeEmail`, `workplace`, `workAddress`, `workTelephone`, `workMobile`, `workEmail`, `dob`, `gender`, `registrationDate`, `picture`) VALUES (?, ?, ?, ?, ?, ?, ?, ?, ?, ?, ?, ?, ?, ?, ?, ?, ?, ?, ?, ?);";
            PreparedStatement prep = Environment.getConnection().prepareStatement(sql);
            prep.setString(1, customerID);
            prep.setString(2, idType);
            prep.setString(3, idNumber);
            prep.setString(4, salutation);
            prep.setString(5, firstName);
            prep.setString(6, lastName);
            prep.setString(7, otherName);
            prep.setString(8, homeAddress);
            prep.setString(9, homeTelephone);
            prep.setString(10, homeMobile);
            prep.setString(11, homeEmail);
            prep.setString(12, workplace);
            prep.setString(13, workAddress);
            prep.setString(14, workTelephone);
            prep.setString(15, workMobile);
            prep.setString(16, workEmail);
            prep.setString(17, dob);
            prep.setString(18, gender);
            prep.setString(19, registrationDate);
            prep.setBinaryStream(20, fin, (int) imgfile.length());
            prep.execute();
            prep.close();

            successful = true;
        }
        catch (Exception e)
        {
            String message = "An error occurred while tring to add a customer to the database.";
            Logger.getLogger(Customer.class.getName()).log(Level.SEVERE, message, e);
            successful = false;
        }
        return successful;
    }

    public static void loadidTypes(JComboBox types)
    {
        ILoanApp.getApplication().getContext().getTaskService().execute(loadIDTypesTask(types));
    }

    private static Task loadIDTypesTask(JComboBox types)
    {
        return new loadIDTypes(ILoanApp.getInstance(), types);
    }

    private static class loadIDTypes extends org.jdesktop.application.Task<Object, Void>
    {

        ArrayList<Object> idTypes = new ArrayList<Object>();
        JComboBox cmbIDType = null;

        public loadIDTypes(Application app, JComboBox types)
        {
            super(app);
            cmbIDType = types;
        }

        @Override
        protected Object doInBackground() throws Exception
        {
            setMessage("About to load ID types.");


            setMessage("Loading ID types.");

            try
            {
                String sql = "select `list_IDTypes`.`type` from `list_IDTypes` `list_IDTypes` order by `list_IDTypes`.`type`";
                PreparedStatement prep = Environment.getConnection().prepareStatement(sql);
                ResultSet rs = prep.executeQuery();

                while (rs.next())
                {
                    idTypes.add(rs.getString("type"));
                }
                rs.close();
                prep.close();
            }
            catch (Exception e)
            {
                String message = "An error occurred while tring to load the item list.";
                Logger.getLogger(Customer.class.getName()).log(Level.SEVERE, message, e);
            }

            return null;
        }

        @Override
        protected void succeeded(Object result)
        {
            cmbIDType.removeAllItems();
            cmbIDType.setModel(new DefaultComboBoxModel(idTypes.toArray()));
            cmbIDType.setSelectedItem("Social Security Card");
            setMessage("Successfully loaded ID types.");
        }
    }

    public static void loadOccupationList(JComboBox occupations)
    {
        ILoanApp.getApplication().getContext().getTaskService().execute(loadOccupationListTask(occupations));
    }

    private static Task loadOccupationListTask(JComboBox occupations)
    {
        return new loadOccupationList(ILoanApp.getApplication(), occupations);
    }

    private static class loadOccupationList extends Task<Object, Void>
    {

        ArrayList<Object> occupationList = new ArrayList<Object>();
        JComboBox cmbOccupations = null;

        public loadOccupationList(Application app, JComboBox occupations)
        {
            super(app);
            cmbOccupations = occupations;
        }

        @Override
        protected Object doInBackground() throws Exception
        {
            setMessage("Loading Occupation List.");
            try
            {
                String sql = "SELECT * FROM list_Occupations ORDER BY list_Occupations.occupation";
                PreparedStatement prep = Environment.getConnection().prepareStatement(sql);
                ResultSet rs = prep.executeQuery();

                while (rs.next())
                {
                    occupationList.add(rs.getString("occupation"));
                }
                rs.close();
                prep.close();

            }
            catch (Exception e)
            {
                String message = "An error occurred while tring to load the occupation list.";
                Logger.getLogger(Customer.class.getName()).log(Level.SEVERE, message, e);
            }
            return null;
        }

        @Override
        protected void succeeded(Object result)
        {
            cmbOccupations.removeAllItems();
            cmbOccupations.setModel(new DefaultComboBoxModel(occupationList.toArray()));
            setMessage("Successfully loaded occupation list.");
        }
    }
}
